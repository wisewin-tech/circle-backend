package com.wisewin.backend.service;

import com.wisewin.backend.dao.MatchingFriendEdDAO;
import com.wisewin.backend.entity.bo.MatchingFriendEdBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MatchingFriendEdService {
    @Resource
    MatchingFriendEdDAO matchingFriendEdDAO;
    //根据模式类型 用户id 查询好友列表
    public List<MatchingFriendEdBO> getFriendList(Map<String,Object> map){
        return matchingFriendEdDAO.getFriendList(map);
    }
    //根据模式类型 用户id 查询好友列表数量
    public Integer getFriendListCount(Map<String,Object> map){
        return matchingFriendEdDAO.getFriendListCount(map);
    }

}
