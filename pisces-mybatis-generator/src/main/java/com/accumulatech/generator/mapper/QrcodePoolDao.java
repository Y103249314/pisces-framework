package com.accumulatech.generator.mapper;

import com.accumulatech.generator.entity.QrcodePoolDo;
import com.accumulatech.generator.entity.QrcodePoolDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QrcodePoolDao {
    long countByExample(QrcodePoolDoExample example);

    int deleteByExample(QrcodePoolDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(QrcodePoolDo record);

    int insertSelective(QrcodePoolDo record);

    List<QrcodePoolDo> selectByExample(QrcodePoolDoExample example);

    QrcodePoolDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") QrcodePoolDo record, @Param("example") QrcodePoolDoExample example);

    int updateByExample(@Param("record") QrcodePoolDo record, @Param("example") QrcodePoolDoExample example);

    int updateByPrimaryKeySelective(QrcodePoolDo record);

    int updateByPrimaryKey(QrcodePoolDo record);
}