package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.UserPictureBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserPictureDAO {
    //添加背景墙
    Integer addUserPicture(@Param("picList") List<UserPictureBO> picList);
    //根据模式删除背景墙
    Integer delUserPicture(Long modelId);
    //根据模式id查询背景墙列表
    List<UserPictureBO> getPictureByModelId(Long modelId);
    //根据审核状态查询背景墙列表
    List<UserPictureBO> getPictureByStatus(@Param("status") String status,@Param("sort") Long sort,@Param("model") String model, @Param("pageOffset") Integer pageOffset, @Param("pageSize") Integer pageSize);
    //根据审核状态查询背景墙列表数量
    Integer getPictureByStatusCount(@Param("status") String status,@Param("sort") Long sort,@Param("model") String model);
    //修改背景墙状态
    Integer updPictureById(UserPictureBO userPictureDAO);
}
