package com.kanfa.news.app.vo.admin.live.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kanfa.news.app.vo.admin.live.courtinfo.CourtContent;
import com.kanfa.news.app.vo.admin.live.courtinfo.CourtInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kanfa on 2018/3/28.
 */
public class JsonChangeUtil {

    public static ArrayList<CourtInfo> change(String courtInfo){

        JSONObject jsonObject = JSON.parseObject(courtInfo);
        String base = jsonObject.getString("基本信息");
        String base2 = jsonObject.getString("审判组织成员");
        String base3 = jsonObject.getString("当事人");

        JSONObject inf = JSON.parseObject(base);
        String startTime = inf.getString("开庭时间");
        String code = inf.getString("案号");
        CourtContent courtContent1 = new CourtContent();
        courtContent1.setTitle("开庭时间");
        courtContent1.setContent(startTime);
        CourtContent courtContent2 = new CourtContent();
        courtContent2.setTitle("案号");
        courtContent2.setContent(code);
        List<CourtContent> list = new ArrayList<>();
        list.add(courtContent1);
        list.add(courtContent2);
        CourtInfo cour = new CourtInfo();
        cour.setName("基本信息");
        cour.setInfo(list);

        JSONObject inf2 = JSON.parseObject(base2);
        String judge = inf2.getString("审判员");
        String organizer = inf2.getString("组织成员");
        CourtContent judge1 = new CourtContent();
        judge1.setTitle("审判员");
        judge1.setContent(judge);
        CourtContent judge2 = new CourtContent();
        judge2.setTitle("组织成员");
        judge2.setContent(organizer);
        List<CourtContent> judgeList = new ArrayList<>();
        judgeList.add(judge1);
        judgeList.add(judge2);
        CourtInfo judgeInfo = new CourtInfo();
        judgeInfo.setName("审判组织成员");
        judgeInfo.setInfo(judgeList);

        JSONObject inf3 = JSON.parseObject(base3);
        String office = inf3.getString("公诉机关");
        String defendant = inf3.getString("被告人");
        CourtContent office1 = new CourtContent();
        office1.setTitle("公诉机关");
        office1.setContent(office);
        CourtContent office2 = new CourtContent();
        office2.setTitle("被告人");
        office2.setContent(defendant);
        List<CourtContent> officeList = new ArrayList<>();
        officeList.add(office1);
        officeList.add(office2);
        CourtInfo officeInfo = new CourtInfo();
        officeInfo.setName("当事人");
        officeInfo.setInfo(officeList);


        ArrayList<CourtInfo> courtList = new ArrayList<>();
        courtList.add(cour);
        courtList.add(judgeInfo);
        courtList.add(officeInfo);

        return courtList;
    }
}
