package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.ActivityBlueSky;
import com.kanfa.news.app.entity.ActivityBlueSkyImage;
import com.kanfa.news.app.mapper.ActivityBlueSkyImageMapper;
import com.kanfa.news.app.mapper.ActivityBlueSkyMapper;
import com.kanfa.news.app.vo.admin.activity.ActivityBlueSkyAddInfo;
import com.kanfa.news.app.vo.admin.activity.ActivityBlueSkyPageInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-17 11:23:12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityBlueSkyBiz extends BaseBiz<ActivityBlueSkyMapper,ActivityBlueSky> {

    @Autowired
    private ActivityBlueSkyMapper activityBlueSkyMapper;
    @Autowired
    private ActivityBlueSkyImageMapper activityBlueSkyImageMapper;

    /**
     * 新增 青春微普法大赛的候选人
     * @param
     * @return
     */
    public void insertActivityBlueSky(ActivityBlueSkyAddInfo entity){
        //绑定activityBlueSky
        ActivityBlueSky activityBlueSky = new ActivityBlueSky();
        BeanUtils.copyProperties(entity,activityBlueSky);
        activityBlueSkyMapper.insertActivityBlueSky(activityBlueSky);
        entity.setId(activityBlueSky.getId());
        //绑定activityBlueSkyImage
        if (entity.getImageList()!=null){
            for (String image:entity.getImageList()) {
                ActivityBlueSkyImage activityBlueSkyImage = new ActivityBlueSkyImage();
                Integer integer = activityBlueSkyImageMapper.selectMaxSort(activityBlueSky.getId());
                if (integer==null){
                    activityBlueSkyImage.setSort(1);
                }else {
                    activityBlueSkyImage.setSort(integer+1);
                }
                activityBlueSkyImage.setImage(image);
                activityBlueSkyImage.setBlueSkyPeopleId(activityBlueSky.getId());
                activityBlueSkyImageMapper.insertSelective(activityBlueSkyImage);
            }
        }
    }

    /**
     * 得到 青春微普法大赛的候选人
     * @param
     * @return
     */
    public ActivityBlueSkyAddInfo getOneActivityBlueSky(Integer id){
        ActivityBlueSkyAddInfo activityBlueSkyAddInfo = new ActivityBlueSkyAddInfo();
        ActivityBlueSky activityBlueSky = activityBlueSkyMapper.selectOneActivityBlueSky(id);
        BeanUtils.copyProperties(activityBlueSky,activityBlueSkyAddInfo);
        //得到activityBlueSkyImage 中的image按sort排列
        List<String> imageList = activityBlueSkyImageMapper.getImageList(activityBlueSky.getId());
        activityBlueSkyAddInfo.setImageList(imageList);
        return activityBlueSkyAddInfo;
    }


    /**
     * 青春微普法大赛 候选人分页和搜索
     * @param
     * @return
     */
    public TableResultResponse<ActivityBlueSkyPageInfo> getActivityBlueSkyPage(ActivityBlueSkyPageInfo entity) {
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        entity.setIsDelete(1);
        List<ActivityBlueSkyPageInfo> list = activityBlueSkyMapper.getActivityBlueSkyPage(entity);
        return new TableResultResponse<ActivityBlueSkyPageInfo>(result.getTotal(),list);
    }




    public void bindactivityBlueSkyImage(ActivityBlueSkyAddInfo entity){
        if (entity.getImageList()!=null){
            for (String image:entity.getImageList()) {
                ActivityBlueSkyImage activityBlueSkyImage = new ActivityBlueSkyImage();
                Integer integer = activityBlueSkyImageMapper.selectMaxSort(entity.getId());
                if (integer==null){
                    activityBlueSkyImage.setSort(1);
                }else {
                    activityBlueSkyImage.setSort(integer+1);
                }
                activityBlueSkyImage.setImage(image);
                activityBlueSkyImage.setBlueSkyPeopleId(entity.getId());
                activityBlueSkyImageMapper.insertSelective(activityBlueSkyImage);
            }
        }
    }
}