package com.kanfa.news;

import org.junit.Test;

/**
 * Created by Ace on 2017/5/21.
 */
//@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
//@SpringBootTest(classes = NewsApplication.class) // 指定我们SpringBoot工程的Application启动类
public class UserServiceTest {
    //@Autowired
    //private RedisDev redisDev;

    @Test
    public void testGetUser() {
        //redisDev.getData("news-admin:permission");
        int start = 0;
        int step = 20000;
        int total = 167983;
        String urlStr = "localhost:8709/search-databatchimport/kf_live/";
        while (start <total){
            System.out.println(urlStr+start+"/"+step);
                start += step;
                if(start + step>total){
                    System.out.println(urlStr+start+"/"+(total-start));
                    break;
                }
        }

    }
  /*  @Autowired
    private RedisDev redisDev;

    @Test
    public void testGetUser() {
        redisDev.getData("news-admin:permission");
    }*/
//    @Autowired
//    private RedisDev redisDev;
//
//    @Test
//    public void testGetUser() {
//        redisDev.getData("news-admin:permission");
//    }
}
