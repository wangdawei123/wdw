package com.kanfa.news.app.client.feign;


import java.lang.annotation.*;

/**
 * Created by ace on 2017/9/15.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EnableNewsAppClient {
}
