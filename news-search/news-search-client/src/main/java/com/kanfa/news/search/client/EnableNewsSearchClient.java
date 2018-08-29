package com.kanfa.news.search.client;

import com.kanfa.news.search.client.configuration.SearchConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Hello world!
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(SearchConfiguration.class)
@Documented
@Inherited
public @interface EnableNewsSearchClient {
}
