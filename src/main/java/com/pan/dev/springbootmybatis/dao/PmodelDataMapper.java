package com.pan.dev.springbootmybatis.dao;

import com.pan.dev.springbootmybatis.entity.PmodelData;

import java.util.List;

public interface PmodelDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmodelData record);

    int insertSelective(PmodelData record);

    PmodelData selectByPrimaryKey(Long id);

    List<PmodelData> selectList();

    int updateByPrimaryKeySelective(PmodelData record);

    int updateByPrimaryKeyWithBLOBs(PmodelData record);

    int updateByPrimaryKey(PmodelData record);
}