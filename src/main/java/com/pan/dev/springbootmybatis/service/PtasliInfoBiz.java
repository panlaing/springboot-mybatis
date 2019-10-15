package com.pan.dev.springbootmybatis.service;

/**
 * @Author:panliang
 * @Description:
 * @params:$params$
 * @return: $returns$
 * @Date: $date$ $time$
 */

import com.pan.dev.springbootmybatis.dao.PtasliInfoMapper;
import com.pan.dev.springbootmybatis.entity.PtasliInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PtasliInfoBiz {

    @Resource
    private PtasliInfoMapper ptasliInfoMapper;

    public PtasliInfo sayHello(Long id){
        PtasliInfo ptasliInfo = ptasliInfoMapper.selectByPrimaryKey(id);
        return ptasliInfo;
    }

}
