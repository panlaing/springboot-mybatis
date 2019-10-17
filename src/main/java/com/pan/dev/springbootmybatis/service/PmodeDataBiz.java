package com.pan.dev.springbootmybatis.service;

import com.pan.dev.springbootmybatis.dao.PmodelDataMapper;
import com.pan.dev.springbootmybatis.entity.PmodelData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liang.pan
 * @Description: TODO
 * @date 2019/10/16 18:59
 */
@Service
public class PmodeDataBiz {

    @Resource
    private PmodelDataMapper pmodelDataMapper;

    public List<PmodelData> list(){
        List<PmodelData> data = pmodelDataMapper.selectList();
        return data;
    }
}
