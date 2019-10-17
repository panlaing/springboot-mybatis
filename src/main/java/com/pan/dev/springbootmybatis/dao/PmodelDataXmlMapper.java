package com.pan.dev.springbootmybatis.dao;

import com.pan.dev.springbootmybatis.entity.PmodelDataXml;

public interface PmodelDataXmlMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmodelDataXml record);

    int insertSelective(PmodelDataXml record);

    PmodelDataXml selectByPrimaryKey(Long id);

    PmodelDataXml selectOne();

    int updateByPrimaryKeySelective(PmodelDataXml record);

    int updateByPrimaryKeyWithBLOBs(PmodelDataXml record);

    int updateByPrimaryKey(PmodelDataXml record);
}