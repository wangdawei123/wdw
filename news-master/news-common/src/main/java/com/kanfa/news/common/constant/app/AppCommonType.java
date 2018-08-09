package com.kanfa.news.common.constant.app;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kanfa on 2018/4/2.
 */
public class AppCommonType {
    public final static String fav = "{\n" +
                                    "    'name': '收藏分类',\n" +
                                    "    'default': 1,\n" +
                                    "    'value': [\n" +
                                    "        {\n" +
                                    "            'type': 2,\n" +
                                    "            'title': '文章'\n" +
                                    "        },\n" +
                                    "        {\n" +
                                    "            'type': 3,\n" +
                                    "            'title': '图集'\n" +
                                    "        },\n" +
                                    "        {\n" +
                                    "            'type': 4,\n" +
                                    "            'title': '视频'\n" +
                                    "        },\n" +
                                    "        {\n" +
                                    "            'type': 11,\n" +
                                    "            'title': 'VR'\n" +
                                    "        },\n" +
                                    "        {\n" +
                                    "            'type': 9,\n" +
                                    "            'title': '直播'\n" +
                                    "        }\n" +
                                    "    ]\n" +
                                    "}";


    public final static String tag_color = "{\n" +
                                    "    'name': '标签颜色',\n" +
                                    "    'default': 1,\n" +
                                    "    'value': {\n" +
                                    "        'content': {\n" +
                                    "            'font_color': '#4D97DD',\n" +
                                    "            'border_color': '#4D97DD'\n" +
                                    "        },\n" +
                                    "        'custom': {\n" +
                                    "            'font_color': '#ce2524',\n" +
                                    "            'border_color': '#ce2524'\n" +
                                    "        },\n" +
                                    "        'adv': {\n" +
                                    "            'font_color': '#4D97DD',\n" +
                                    "            'border_color': '#4D97DD'\n" +
                                    "        },\n" +
                                    "        'top': {\n" +
                                    "            'font_color': '#4D97DD',\n" +
                                    "            'border_color': '#4D97DD'\n" +
                                    "        },\n" +
                                    "        'score': {\n" +
                                    "            'font_color': '#4D97DD',\n" +
                                    "            'border_color': '#4D97DD'\n" +
                                    "        },\n" +
                                    "        'hot': {\n" +
                                    "            'font_color': '#cc2424',\n" +
                                    "            'border_color': '#fff3f3'\n" +
                                    "        },\n" +
                                    "        'near': {\n" +
                                    "            'font_color': '#4c96dd',\n" +
                                    "            'border_color': '#f1fbff'\n" +
                                    "        },\n" +
                                    "        'original': {\n" +
                                    "            'font_color': '#CD2525',\n" +
                                    "            'border_color': '#CD2525'\n" +
                                    "        }\n" +
                                    "    }\n" +
                                    "}";

    public final static String type = "{\n" +
                                    "    'name': '数据类型',\n" +
                                    "    'default': 1,\n" +
                                    "    'value': {\n" +
                                    "        '1': '专题',\n" +
                                    "        '2': '文章',\n" +
                                    "        '3': '图集',\n" +
                                    "        '4': '视频',\n" +
                                    "        '9': '直播',\n" +
                                    "        '11': 'VR',\n" +
                                    "        '12': '问答',\n" +
                                    "        '13': '活动',\n" +
                                    "        '21': '律师直播',\n" +
                                    "        '22': '直播',\n" +
                                    "        '23': '爆料',\n" +
                                    "        '25': '政法先锋'\n" +
                                    "    }\n" +
                                    "}";

    public final static Map<Integer ,String> getTypeMap(){
        Map<Integer ,String> map = new HashMap<>(5);
        map.put(1 , "专题");
        map.put(2 , "文章");
        map.put(3 , "图集");
        map.put(4 , "视频");
        map.put(9 , "直播");
        map.put(11 , "VR");
        map.put(12 , "问答");
        map.put(13 , "活动");
        map.put(21 , "律师直播");
        map.put(22 , "直播");
        map.put(23 , "爆料");
        map.put(25 , "政法先锋");
        return map;
    }

    public final static String broadcast_type = "{\n" +
                                    "    'name': '类型',\n" +
                                    "    'default': 1,\n" +
                                    "    'value': {\n" +
                                    "        '1': '预告',\n" +
                                    "        '2': '直播',\n" +
                                    "        '3': '回顾'\n" +
                                    "    }\n" +
                                    "}";


    public final static Map<Integer ,String> getBroadcastTypeMap(){
        Map<Integer ,String> map = new HashMap<>(5);
        map.put(1 , "预告");
        map.put(2 , "直播");
        map.put(3 , "回顾");
        return map;
    }


    public final static String card_type = "{\n" +
                                    "    'name': '类型',\n" +
                                    "    'default': 1,\n" +
                                    "    'value': {\n" +
                                    "        '1': 2,\n" +
                                    "        '2': '',\n" +
                                    "        '3': 4,\n" +
                                    "        '4': 5,\n" +
                                    "        '9': 5,\n" +
                                    "        '11': 5,\n" +
                                    "        '12': 1\n" +
                                    "    }\n" +
                                    "}";



}
