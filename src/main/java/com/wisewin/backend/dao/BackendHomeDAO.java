package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.StatisticalRecords;
import org.apache.ibatis.annotations.Param;

public interface BackendHomeDAO {

    //获取当前
    StatisticalRecords getStatisticalRecordsSum(@Param("year") Integer year,@Param("month") Integer month,@Param("day") Integer day);
}
