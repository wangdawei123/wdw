package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.PcPosBiz;
import com.kanfa.news.info.entity.PcPos;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pcPos")
public class PcPosController extends BaseController<PcPosBiz,PcPos> {

}