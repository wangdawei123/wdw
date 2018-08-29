package com.kanfa.news.admin.rpc.datacount;

import com.kanfa.news.admin.entity.Dictionary;
import com.kanfa.news.admin.feign.datacount.IDataCountServiceFeign;
import com.kanfa.news.admin.feign.datacount.IDictionaryServiceFeign;
import com.kanfa.news.common.util.excel.ExportExcelUtils;
import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/20 9:55
 */
@RestController
@RequestMapping("dictionary")
public class DictionaryRest {
    @Autowired
    private IDictionaryServiceFeign iDictionaryServiceFeign;
    /**
     * 数据统计列表
     * @param categoryId
     * @param channelId
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/getList")
    public List<Dictionary> getList(){
        return iDictionaryServiceFeign.getList();
    }

}
