package com.kanfa.news.info.vo.admin.live.Utils;

import com.kanfa.news.info.vo.admin.live.LiveInfo;

import java.util.List;

/**
 * Created by kanfa on 2018/3/28.
 */
public class ChangeJumpTypeUtil {

    public static LiveInfo changeJumpType(LiveInfo info){
        if(info != null){
            if(info.getSourceUrl() != "" && info.getFlashObj() != ""){
                info.setJumpType(2);
                info.setSourceUrl(null);
            }else{
                info.setJumpType(1);
                info.setSourceUrl(null);
                info.setFlashObj(null);
            }
            info.setImageType(2);
            info.setId(info.getLiveId());
        }
        return info;
    }
}
