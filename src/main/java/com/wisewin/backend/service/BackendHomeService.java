package com.wisewin.backend.service;

import com.wisewin.backend.dao.BackendHomeDAO;
import com.wisewin.backend.entity.bo.StatisticalRecords;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class BackendHomeService {
    @Resource
    BackendHomeDAO backendHomeDAO;
    public List<StatisticalRecords> getStatisticalRecords(){
        Calendar c = Calendar.getInstance();
        Integer year = c.get(Calendar.YEAR);//获取年
        Integer month = c.get(Calendar.MONTH) + 1;//获取月份，0表示1月份
        Integer day = c.get(Calendar.DAY_OF_MONTH);//获取当前天数

        //当年
        StatisticalRecords statisticalYearRecords=backendHomeDAO.getStatisticalRecordsSum(year,null,null);
        //当月
        StatisticalRecords statisticalMonthRecords=backendHomeDAO.getStatisticalRecordsSum(year,month,null);
        //当日
        StatisticalRecords statisticalDayRecords=backendHomeDAO.getStatisticalRecordsSum(year,month,day);
        //总
        StatisticalRecords statisticalAllRecords=backendHomeDAO.getStatisticalRecordsSum(null,null,null);
        List<StatisticalRecords> result=new ArrayList<StatisticalRecords>();
        result.add(statisticalDayRecords);
        result.add(statisticalMonthRecords);
        result.add(statisticalYearRecords);
        result.add(statisticalAllRecords);
        return result;

    }

}
