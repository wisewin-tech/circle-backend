package com.wisewin.backend.service;

import com.wisewin.backend.dao.UserDAO;
import com.wisewin.backend.entity.bo.StatisticalBO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
@Service
public class StatisticalService {

    @Resource
    UserDAO userDAO;
    public Map<String, List<StatisticalBO>> getStatisticalMap(Date date){
        //获取今天日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);//获取年份
        int month = calendar.get(Calendar.MONTH) + 1;//获取月份
        int day=calendar.get(Calendar.DATE);//获取日
        Map<String,List<StatisticalBO>> map=new HashMap<String, List<StatisticalBO>>();
        List<StatisticalBO> peopleStatistical=new ArrayList<StatisticalBO>();
        //查询总用户
        peopleStatistical.add(new StatisticalBO(userDAO.getUserAllCount(),"userCount"));
        //根据日期查询活跃数
        peopleStatistical.add(new StatisticalBO(userDAO.getUserRegisteredCount(year,month,day),"registeredCount"));
        //根据日期查询注册数
        peopleStatistical.add(new StatisticalBO(userDAO.getUserActiveCount(year,month,day),"activeCount"));
        map.put("peopleStatistical",peopleStatistical);
        //分别查询三个模式的匹配情况
        map.put("matchingStatistical",userDAO.getMatchingCount(year,month,day));
        return map;
    }
}
