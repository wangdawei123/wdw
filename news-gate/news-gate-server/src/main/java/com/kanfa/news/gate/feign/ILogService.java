package com.kanfa.news.gate.feign;

import com.kanfa.news.api.vo.log.LogInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-07-01 15:16
 */
@FeignClient("news-admin")
public interface ILogService {
  @RequestMapping(value="/api/log/save",method = RequestMethod.POST)
  public void saveLog(LogInfo info);
}
