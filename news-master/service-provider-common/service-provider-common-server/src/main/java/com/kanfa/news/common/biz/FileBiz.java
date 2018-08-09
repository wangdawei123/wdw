package com.kanfa.news.common.biz;

import com.aliyun.oss.*;
import com.aliyun.oss.model.*;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 文件处理业务
 *
 * @author Jezy
 */
@Service
public class FileBiz {
    //http://devvideotest.oss-cn-beijing.aliyuncs.com
    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAI2sitJLUoat5J";
    private static String accessKeySecret = "lppXeF8e4RAteJOCoit2G7pd4PIvuq";

    private static OSS client = null;

    private static String bucketName = "devvideotest";
    private static String key = "";

    private static List<PartETag> partETags = Collections.synchronizedList(new ArrayList<PartETag>());
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    public ObjectRestResponse uploadVideo(MultipartFile file) {
        /*
         * Constructs a client instance with your account for accessing OSS
         */
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        conf.setIdleConnectionTime(1000);
        client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, conf);

        try {
            String originalFilename = file.getOriginalFilename();
            String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            Random random = new Random();
            String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
            key = "outactmvp/" + name;

            InputStream inputStream = file.getInputStream();
            String suffix = substring.substring(substring.lastIndexOf("."));
            byte[] bs = new byte[1024];
            int len;
            File sf=File.createTempFile("oss-img-tmp",suffix);
            OutputStream os = new FileOutputStream(sf);
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            os.flush();
            os.close();
            inputStream.close();

//            InputStream inputStream1 = new FileInputStream(sf);
//            File file1 = new File();
            /*
             * Claim a upload id firstly
             */
            String uploadId = claimUploadId();
            System.out.println("Claiming a new upload id " + uploadId + "\n");

            /*
             * Calculate how many parts to be divided
             */
            final long partSize = 5 * 1024 * 1024L;   // 5MB
            final File sampleFile = sf;
            long fileLength = sampleFile.length();
            int partCount = (int) (fileLength / partSize);
            if (fileLength % partSize != 0) {
                partCount++;
            }
            if (partCount > 10000) {
                throw new RuntimeException("Total parts count should not exceed 10000");
            } else {
                System.out.println("Total parts count " + partCount + "\n");
            }

            /*
             * Upload multiparts to your bucket
             */
            System.out.println("Begin to upload multiparts to OSS from a file\n");
//            System.out.println("thread status = [" + executorService.isShutdown() + "]");
//            System.out.println("thread status = [" + executorService.isTerminated()+ "]");
            for (int i = 0; i < partCount; i++) {
                long startPos = i * partSize;
                long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
                executorService.execute(new PartUploader(sampleFile, startPos, curPartSize, i + 1, uploadId));
            }

            /*
             * Waiting for all parts finished
             */
            executorService.shutdownNow();
            while (!executorService.isTerminated()) {
                try {
                    executorService.awaitTermination(10L, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            /*
             * Verify whether all parts are finished
             */
            if (partETags.size() != partCount) {
                throw new IllegalStateException("Upload multiparts fail due to some parts are not finished yet");
            } else {
                System.out.println("Succeed to complete multiparts into an object named " + key + "\n");
            }

            /*
             * View all parts uploaded recently
             */
            listAllParts(uploadId);

            /*
             * Complete to upload multiparts
             */
            completeMultipartUpload(uploadId);

            /*
             * Fetch the object that newly created at the step below.
             */
            System.out.println("Fetching an object");
            //client.getObject(new GetObjectRequest(bucketName, key), new File(localFilePath));

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            if (client != null) {
                client.shutdown();
            }
        }
        return new ObjectRestResponse();
    }

    private static class PartUploader implements Runnable {

        private File localFile;
        private long startPos;

        private long partSize;
        private int partNumber;
        private String uploadId;

        public PartUploader(File localFile, long startPos, long partSize, int partNumber, String uploadId) {
            this.localFile = localFile;
            this.startPos = startPos;
            this.partSize = partSize;
            this.partNumber = partNumber;
            this.uploadId = uploadId;
        }

        @Override
        public void run() {
            InputStream instream = null;
            try {
                instream = new FileInputStream(this.localFile);
                System.out.println(Thread.currentThread().getId()+"----------"+Thread.currentThread().getName());
                instream.skip(this.startPos);

                UploadPartRequest uploadPartRequest = new UploadPartRequest();
                uploadPartRequest.setBucketName(bucketName);
                uploadPartRequest.setKey(key);
                uploadPartRequest.setUploadId(this.uploadId);
                uploadPartRequest.setInputStream(instream);
                uploadPartRequest.setPartSize(this.partSize);
                uploadPartRequest.setPartNumber(this.partNumber);

                UploadPartResult uploadPartResult = client.uploadPart(uploadPartRequest);
                System.out.println("Part#" + this.partNumber + " done\n");
                synchronized (partETags) {
                    System.out.println("debug 1***************");
                    partETags.add(uploadPartResult.getPartETag());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (instream != null) {
                    try {
                        instream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static String claimUploadId() {
        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, key);
        InitiateMultipartUploadResult result = client.initiateMultipartUpload(request);
        return result.getUploadId();
    }

    private static void completeMultipartUpload(String uploadId) {
        // Make part numbers in ascending order
        Collections.sort(partETags, new Comparator<PartETag>() {

            @Override
            public int compare(PartETag p1, PartETag p2) {
                return p1.getPartNumber() - p2.getPartNumber();
            }
        });

        System.out.println("Completing to upload multiparts\n");
        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                new CompleteMultipartUploadRequest(bucketName, key, uploadId, partETags);
        client.completeMultipartUpload(completeMultipartUploadRequest);
    }

    private static void listAllParts(String uploadId) {
        System.out.println("Listing all parts......");
        ListPartsRequest listPartsRequest = new ListPartsRequest(bucketName, key, uploadId);
        PartListing partListing = client.listParts(listPartsRequest);

        int partCount = partListing.getParts().size();
        for (int i = 0; i < partCount; i++) {
            PartSummary partSummary = partListing.getParts().get(i);
            System.out.println("\tPart#" + partSummary.getPartNumber() + ", ETag=" + partSummary.getETag());
        }
        System.out.println();
    }
}
