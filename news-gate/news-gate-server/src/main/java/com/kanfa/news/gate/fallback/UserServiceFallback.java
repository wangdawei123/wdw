package com.kanfa.news.gate.fallback;

import com.kanfa.news.api.vo.authority.PermissionInfo;
import com.kanfa.news.common.util.LogUtil;
import com.kanfa.news.gate.feign.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author ace
 * @create 2018/3/7.
 */
@Service
public class UserServiceFallback implements IUserService {
    @Override
    public List<PermissionInfo> getPermissionByUsername(@PathVariable("username") String username) {
        LogUtil.error("调用{}异常{}","getPermissionByUsername",username);
        return null;
    }

    @Override
    public List<PermissionInfo> getAllPermissionInfo() {
        LogUtil.error("调用{}异常","getPermissionByUsername");
        return null;
    }
}
