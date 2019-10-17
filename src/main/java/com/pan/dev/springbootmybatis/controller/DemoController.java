package com.pan.dev.springbootmybatis.controller;

import com.pan.dev.springbootmybatis.entity.PmodelDataXml;
import com.pan.dev.springbootmybatis.service.PmodeDataBiz;
import com.pan.dev.springbootmybatis.service.PmodeDataXmlBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liang.pan
 * @Description: TODO
 * @date 2019/10/16 18:58
 */
@RestController
public class DemoController {

    @Autowired
    private PmodeDataBiz pmodeDataBiz;
    @Autowired
    private PmodeDataXmlBiz xmlBiz;

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public String login() {

        PmodelDataXml xml = xmlBiz.selectOne();
        return xml.getRawData();
    }
}
