package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.MatchingFriendEdBO;

import java.util.List;
import java.util.Map;

public interface MatchingFriendEdDAO {
    //根据模式类型 用户id 查询好友列表
    List<MatchingFriendEdBO> getFriendList(Map<String,Object> map);
    Integer getFriendListCount(Map<String,Object> map);
}
