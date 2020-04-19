package com.accumulatech.generator.mapper;

import com.accumulatech.generator.entity.NumberPoolDo;
import com.accumulatech.generator.entity.NumberPoolDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NumberPoolDao {
    long countByExample(NumberPoolDoExample example);

    int deleteByExample(NumberPoolDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(NumberPoolDo record);

    int insertSelective(NumberPoolDo record);

    List<NumberPoolDo> selectByExample(NumberPoolDoExample example);

    NumberPoolDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") NumberPoolDo record, @Param("example") NumberPoolDoExample example);

    int updateByExample(@Param("record") NumberPoolDo record, @Param("example") NumberPoolDoExample example);

    int updateByPrimaryKeySelective(NumberPoolDo record);

    int updateByPrimaryKey(NumberPoolDo record);
}