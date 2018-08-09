import com.kanfa.news.common.util.WinnerLookUtil;
import okhttp3.OkHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.omg.CORBA.NameValuePair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        List<org.apache.http.NameValuePair> nvps = new ArrayList<org.apache.http.NameValuePair>();
        nvps.add(new BasicNameValuePair("room_id", "1"));
//
//        String a = WinnerLookUtil.httpPost("http://localhost:8765/api/app/comment/getDiscussion",nvps);
//        nvps.add(new BasicNameValuePair("cate", "1"));
//
//        WinnerLookUtil.httpPost("http://3bxw8s.natappfree.cc/api/app/channel/getList",nvps);
//        System.out.println(a);

        System.out.println("ddd");

        SimpleDateFormat sdf = new SimpleDateFormat("y年M月d日");

        System.out.println(sdf.format(new Date()) + " 发布");
    }

}
