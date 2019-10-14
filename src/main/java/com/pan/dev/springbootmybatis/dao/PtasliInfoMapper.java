package com.pan.dev.springbootmybatis.dao;

import com.pan.dev.springbootmybatis.entity.PtasliInfo;
import org.springframework.stereotype.Repository;

public interface PtasliInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PtasliInfo record);

    int insertSelective(PtasliInfo record);

    PtasliInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PtasliInfo record);

    int updateByPrimaryKey(PtasliInfo record);
}