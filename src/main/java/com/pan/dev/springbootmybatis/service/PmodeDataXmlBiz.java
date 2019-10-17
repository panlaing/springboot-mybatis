package com.pan.dev.springbootmybatis.service;

import com.pan.dev.springbootmybatis.dao.PmodelDataXmlMapper;
import com.pan.dev.springbootmybatis.entity.PmodelDataXml;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liang.pan
 * @Description: TODO
 * @date 2019/10/17 11:45
 */
@Service
public class PmodeDataXmlBiz {

    @Resource
    private PmodelDataXmlMapper xmlMapper;

    public PmodelDataXml selectOne(){
        PmodelDataXml pmodelDataXml = xmlMapper.selectOne();
        return pmodelDataXml;
    }
}
