package com.kanfa.news.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

/**
 * @author jezy
 * @date 2016/7/21
 */
public class ImageUtils {
    private static final Logger logger = LoggerFactory.getLogger(ImageUtils.class);

    /**
     * 将本地图片编码为base64
     *
     * @param file
     * @return
     */
    public static String encodeImageToBase64(File file) {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        logger.info("图片的路径为:" + file.getAbsolutePath());
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encode(data);
        logger.info("本地文件[{}]编码成base64字符串:[{}]", file.getAbsolutePath(), base64);
        return base64;
        //返回Base64编码过的字节数组字符串
    }

    /**
     * 将网络图片编码为base64
     *
     * @param url
     * @return
     */
    public static String encodeImageToBase64(URL url) {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        logger.info("图片的路径为:" + url.toString());
        //打开链接
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            //使用一个输入流从buffer里把数据读取出来
            while ((len = inStream.read(buffer)) != -1) {
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            //关闭输入流
            inStream.close();
            byte[] data = outStream.toByteArray();
            //对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            String base64 = encoder.encode(data);
            //logger.info("网络文件[{}]编码成base64字符串:[{}]", url.toString(), base64);
            return base64;
            //返回Base64编码过的字节数组字符串
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Base64字符串转 二进制流
     *
     * @param base64String Base64
     * @return base64String
     * @throws IOException 异常
     */
    public static byte[] getStringImage(String base64String) throws IOException {
        BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        return base64String != null ? decoder.decodeBuffer(base64String) : null;
    }

    /**
     * 从内存字节数组中读取图像
     *
     * @param imgBytes 未解码的图像数据
     * @return 返回 {@link BufferedImage}
     * @throws IOException 当读写错误或不识别的格式时抛出
     */
    public static final BufferedImage readMemoryImage(byte[] imgBytes) throws IOException {
        if (null == imgBytes || 0 == imgBytes.length)
            throw new NullPointerException("the argument 'imgBytes' must not be null or empty");
        // 将字节数组转为InputStream，再转为MemoryCacheImageInputStream
        ImageInputStream imageInputstream = new MemoryCacheImageInputStream(new ByteArrayInputStream(imgBytes));
        // 获取所有能识别数据流格式的ImageReader对象
        Iterator<ImageReader> it = ImageIO.getImageReaders(imageInputstream);
        // 迭代器遍历尝试用ImageReader对象进行解码
        while (it.hasNext()) {
            ImageReader imageReader = it.next();
            // 设置解码器的输入流
            imageReader.setInput(imageInputstream, true, true);
            // 图像文件格式后缀
            String suffix = imageReader.getFormatName().trim().toLowerCase();
            // 图像宽度
            int width = imageReader.getWidth(0);
            // 图像高度
            int height = imageReader.getHeight(0);
            //System.out.printf("format %s,%dx%d\n", suffix, width, height);
            try {
                // 解码成功返回BufferedImage对象
                // 0即为对第0张图像解码(gif格式会有多张图像),前面获取宽度高度的方法中的参数0也是同样的意思
                return imageReader.read(0, imageReader.getDefaultReadParam());
            } catch (Exception e) {
                imageReader.dispose();
                // 如果解码失败尝试用下一个ImageReader解码
            }
        }
        imageInputstream.close();
        // 没有能识别此数据的图像ImageReader对象，抛出异常
        throw new IOException("unsupported image format");
    }

    public static final BufferedImage readMemoryImage1(byte[] imgBytes) throws IOException {
        if (null == imgBytes || 0 == imgBytes.length) {
            throw new NullPointerException("the argument 'imgBytes' must not be null or empty");
        }
        // 将字节数组转为InputStream，再转为MemoryCacheImageInputStream
        ImageInputStream imageInputstream = new MemoryCacheImageInputStream(new ByteArrayInputStream(imgBytes));
        // 直接调用ImageIO.read方法解码
        BufferedImage bufImg = ImageIO.read(imageInputstream);
        if (null == bufImg) {
            // 没有能识别此数据的图像ImageReader对象，抛出异常
            throw new IOException("unsupported image format");
        }
        return bufImg;
    }
}