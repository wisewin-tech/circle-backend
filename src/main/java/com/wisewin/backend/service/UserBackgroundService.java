package com.wisewin.backend.service;

import com.wisewin.backend.dao.UserBackgroundDAO;
import com.wisewin.backend.entity.bo.UserBackgroundBO;
import com.wisewin.backend.entity.dto.UserBackBTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: Bin Wang
 * @date: Created in 15:09 2019/6/4
 */
@Service("userBackgroundService")
@Transactional
public class UserBackgroundService {

    @Resource
    private UserBackgroundDAO userBackgroundDAO;

    public UserBackBTO queryUserBack(Map<String,Object> map,Map<String, Object> counts ){
        List<UserBackgroundBO> list =  userBackgroundDAO.queryUserBack(map);
        Integer count  = userBackgroundDAO.queryCount(counts);
        UserBackBTO userBackBTO = new UserBackBTO();
        userBackBTO.setCount(count);
        userBackBTO.setList(list);
        return userBackBTO;
    }

    public void updateUserBackStatus( Integer id,  String state){
        userBackgroundDAO.updateUserBackStatus(id,state);
    }

    public void delteUserBack(Integer id){
        userBackgroundDAO.delteUserBack(id);
    }
}
