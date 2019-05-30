package com.wisewin.backend.service;

import com.wisewin.backend.dao.UserDAO;
import com.wisewin.backend.entity.bo.UserBO;
import com.wisewin.backend.entity.param.UserParam;
import com.wisewin.backend.util.MD5Util;
import com.wisewin.backend.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userBoService")
@Transactional
public class UserService {

    @Resource
    private UserDAO userDAO;

    //查询所有用户信息
    public Map<String,Object> selectAll(Map<String,Object> map,UserParam userParam ) {
        //把user参数对象放入map中
        map.put("userParam",userParam);
        //System.out.println("map:"+map);
        //把封装好的条件放入map中,在limit找出对应的封装数据,通过查询返回user对象的集合
        List<UserBO> userBOS= userDAO.selectAll(map);
        System.out.println("List<UserBO> userBOS:"+userBOS);
        //查询一共有多少条用户信息
        Integer count= userDAO.selectCount(map);

        //用于存储数据的resultMap
        Map<String,Object>  resultMap=new HashMap<String, Object>();
        //把查询到的用户对象的集合放入map中,
        resultMap.put("date",userBOS);
        //把查询到的用户个数放入map中
        resultMap.put("count",count);
        return resultMap;
    }
    //查询用户总条数
    public Integer selectCount(Map<String,Object> map) {
        return userDAO.selectCount(map);
    }

    //修改用户信息
    public boolean updateUser(Integer id,UserParam userParam){
        //通过id得到user对象
        UserBO user= userDAO.queryUserById(id);
        //如果没有这个人,返回false
        if(user == null){

            return false;
        }
        //有这个人,修改其信息,密码加密处理
        String pass=userParam.getPassword();
        if (!StringUtils.isEmpty(pass)) {
            userParam.setPassword(MD5Util.digest(pass));
        }
        //修改
        userDAO.updateUser(userParam);
        return true;
    }
    //删除用户
    public boolean deleteUser(Integer id){
        //通过id得到user对象
        UserBO user= userDAO.queryUserById(id);
        //如果没有这个人,返回false
        if(user == null){

            return false;
        }
        userDAO.delUserById(id);
        return true;
    }
}
