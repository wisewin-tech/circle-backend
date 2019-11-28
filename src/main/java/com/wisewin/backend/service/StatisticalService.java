package com.wisewin.backend.service;

import com.wisewin.backend.dao.UserDAO;
import com.wisewin.backend.entity.bo.StatisticalBO;
import com.wisewin.backend.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class StatisticalService {

    /**
     * @param date 时间
     * @param type 查询类型
     * 类型为年：查询每一年的统计
     * 类型为月：查询时间参数这一年中每个月的统计
     * 类型为日：查询时间参数这一月中每一天的统计
     */
    @Resource
    UserDAO userDAO;
    private static final Logger log = LoggerFactory.getLogger(StatisticalService.class);

    public Map<String, Object> getStatisticalMap(Date date, String type) {
        log.info("start getStatisticalMap..................................");
        log.info("date:{}\ttype:{}", date, type);
        //获取今天日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);//获取年份
        int month = calendar.get(Calendar.MONTH) + 1;//获取月份
        int day = calendar.get(Calendar.DATE);//获取日
        Map<String, Object> map = new HashMap<String, Object>();

        //一、分别查询三个模式的匹配情况
        List<StatisticalBO> matchingStatistical=userDAO.getMatchingCount(year, month, day,type);
        boolean dateBool=true;
        boolean friendBool=true;
        boolean carBool=true;
        for (StatisticalBO statisticalBO:matchingStatistical) {
            if(statisticalBO.getName().equals("date")){
                dateBool=false;
                map.put(statisticalBO.getName(),statisticalBO.getValue());
            }else if(statisticalBO.getName().equals("friend")){
                friendBool=false;
                map.put(statisticalBO.getName(),statisticalBO.getValue());
            }else if(statisticalBO.getName().equals("car")){
                carBool=false;
                map.put(statisticalBO.getName(),statisticalBO.getValue());
            }
        }
        if(dateBool){
            map.put("date",0L);
        }
        if(friendBool){
            map.put("friend",0L);
        }
        if(carBool){
            map.put("car",0L);
        }


        //二、查询注册数统计
        List<StatisticalBO> registeredCountList = userDAO.getUserRegisteredCount(year, month, day, type);
        //循环拼接日期,以及容错某个日期人数为0
        getStatisticalBOList(type,registeredCountList,year,month);
        map.put("registeredCount", registeredCountList);
        //三、查询活跃数
        List<StatisticalBO> activeCountList = userDAO.getUserActiveCount(year, month, day, type);
        //循环拼接日期,以及容错某个日期人数为0
        getStatisticalBOList(type,activeCountList,year,month);
        map.put("activeCountList", activeCountList);
        if("month".equals(type)){
            List<String> monthList = DateUtils.getMonthList();//月集合
            map.put("dayList",monthList);
        }else if("year".equals(type)){
            Set<String> set=new HashSet<String>();
            for (StatisticalBO st :registeredCountList) {
                set.add(st.getYear());
            }
            for (StatisticalBO st :activeCountList) {
                set.add(st.getYear());
            }
            map.put("dayList",set);
        }else{
            List<String> dayList = DateUtils.getMonthFullDay(year,month,1);//月当中日期集合
            map.put("dayList",dayList);
        }

        log.info("result:{}", map);
        log.info("end getStatisticalMap.............................................");
        return map;
    }

    //循环拼接日期,以及容错某个日期人数为0
    public void getStatisticalBOList(String type,List<StatisticalBO> StatisticalBOList,Integer year,Integer month){
        if (type.equals("year")) {
            for (StatisticalBO statisticalBO : StatisticalBOList) {
                String createTime = statisticalBO.getYear();
                statisticalBO.setDate(createTime);
            }
        } else if (type.equals("month")) {
            List<String> monthList = DateUtils.getMonthList();//月集合
            for (StatisticalBO statisticalBO : StatisticalBOList) {
                String createTime = statisticalBO.getYear() + "-" + statisticalBO.getMonth();
                statisticalBO.setDate(createTime);
                monthList.remove(statisticalBO.getMonth());
            }
            for (String mon : monthList) {
                String createTime = year + "-" + mon;
                StatisticalBO statisticalBO = new StatisticalBO();
                statisticalBO.setValue(0L);
                statisticalBO.setDate(createTime);
                StatisticalBOList.add(new Integer(mon) - 1, statisticalBO);
            }
        } else if (type.equals("day")) {
            List<String> dayList = DateUtils.getMonthFullDay(year,month,1);//月当中日期集合
            for (StatisticalBO statisticalBO : StatisticalBOList) {
                String createTime = statisticalBO.getYear() + "-" + statisticalBO.getMonth() + "-" + statisticalBO.getDay();
                statisticalBO.setDate(createTime);
                dayList.remove(statisticalBO.getDay());
            }
            for (String d : dayList) {
                String createTime = year + "-" + month + "-" + d;
                StatisticalBO statisticalBO = new StatisticalBO();
                statisticalBO.setValue(0L);
                statisticalBO.setDate(createTime);
                StatisticalBOList.add(new Integer(d) - 1, statisticalBO);
            }
        }
    }
}
