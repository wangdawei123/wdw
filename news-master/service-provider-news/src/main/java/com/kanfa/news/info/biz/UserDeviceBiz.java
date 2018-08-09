package com.kanfa.news.info.biz;

import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.UserDevice;
import com.kanfa.news.info.mapper.UserDeviceMapper;
import com.kanfa.news.common.biz.BaseBiz;

/**
 * 极光关系关联表
 *
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-07-02 14:18:30
 */
@Service
public class UserDeviceBiz extends BaseBiz<UserDeviceMapper,UserDevice> {
}