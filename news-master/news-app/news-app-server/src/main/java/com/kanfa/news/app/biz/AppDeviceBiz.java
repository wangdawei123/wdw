package com.kanfa.news.app.biz;

import com.kanfa.news.app.entity.AppDevice;
import com.kanfa.news.app.mapper.AppDeviceMapper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * app设备表
 *
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-04-04 17:59:08
 */
@Service
public class AppDeviceBiz extends BaseBiz<AppDeviceMapper,AppDevice> {

    @Autowired
    private AppDeviceMapper appDeviceMapper;

    /**
     * 保存设置信息
     */
    public void saveDevice(Integer platform ,String devid ,String idfa ,String user_id ,String version){
        AppDevice device_info = null;
        if(platform.equals(LiveCommonEnum.APP_DEVICE_ANDRIOID) && devid != null){
            AppDevice appDevice = new AppDevice();
            appDevice.setDeviceId(devid);
            device_info = appDeviceMapper.selectOne(appDevice);
        }else if(platform.equals(LiveCommonEnum.APP_DEVICE_IOS) && devid != null && idfa != null){
            AppDevice appDevice = new AppDevice();
            appDevice.setIdfa(idfa);
            device_info = appDeviceMapper.selectOne(appDevice);
        }else {
            return;
        }

        int i = 0;
        if(device_info == null){
            device_info = new AppDevice();
            device_info.setType(platform);
            device_info.setUid(Integer.parseInt(user_id));
            device_info.setIdfa(idfa);
            device_info.setDeviceId(devid);
            device_info.setVersion(version);
            device_info.setCreateDate((int)System.currentTimeMillis());
            i = appDeviceMapper.insertSelective(device_info);
        }else{
            if(user_id == null){
                return;
            }
            device_info.setType(platform);
            device_info.setUid(Integer.parseInt(user_id));
            device_info.setIdfa(idfa);
            device_info.setDeviceId(devid);
            device_info.setVersion(version);
            device_info.setCreateDate((int)System.currentTimeMillis());
            i = appDeviceMapper.updateByPrimaryKeySelective(device_info);
        }

        if(i < 1){
            // 要写入的文件路径
            //测试路径
            File file = new File("E:\\idfa_live.log");
            //上线环境
//          File file = new File("E:\\idfa_live.log");
            // 判断文件是否存在
            if (!file.exists()) {
                try {
                    // 如果文件不存在创建文件
                    file.createNewFile();
                    System.out.println("文件"+file.getName()+"不存在已为您创建!");
                } catch (IOException e) {
                    System.out.println("创建文件异常!");
                    e.printStackTrace();
                }
            } else {
                System.out.println("文件"+file.getName()+"已存在!");
            }

            // 遍历listStr集合
            FileOutputStream fos = null;
            PrintStream ps = null;
            try {
                // 文件输出流  追加
                fos = new FileOutputStream(file,true);
                ps = new PrintStream(fos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // +换行
            String string  = "\r\n" +"---------";
            // 执行写操作
            ps.print(string);
            // 关闭流
            ps.close();
            System.out.println("文件写入完毕-------!");
        }

    }
}