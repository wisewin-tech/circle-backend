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

    /**
     * 查询所有用户信息
     * @param map
     * @return
     */
    public List<UserBO> selectAll(Map<String, Object> map) {
        return userDAO.selectAll(map);
    }

    /**
     * 查询用户总条数
     * @param map
     * @return
     */
    public Integer selectCount(Map<String, Object> map) {
        return userDAO.selectCount(map);
    }

    /**
     * 修改用户信息
     * @param userParam
     * @return
     */
    public Integer updateUser(UserParam userParam){
        return userDAO.updateUser(userParam);
    }

}
