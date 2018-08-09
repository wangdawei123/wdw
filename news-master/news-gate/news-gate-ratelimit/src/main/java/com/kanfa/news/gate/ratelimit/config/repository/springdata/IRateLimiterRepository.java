package com.kanfa.news.gate.ratelimit.config.repository.springdata;

import com.kanfa.news.gate.ratelimit.config.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRateLimiterRepository extends CrudRepository<Rate, String> {

}
