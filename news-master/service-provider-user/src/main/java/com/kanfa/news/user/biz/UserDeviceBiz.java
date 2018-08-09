package com.kanfa.news.user.biz;

import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.user.entity.UserDevice;
import com.kanfa.news.user.mapper.UserDeviceMapper;
import org.springframework.stereotype.Service;

/**
 * 极光关系关联表
 *
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-04-04 17:29:41
 */
@Service
public class UserDeviceBiz extends BaseBiz<UserDeviceMapper,UserDevice> {
}