package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.FeedBackBO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface FeedbackDAO {
    /**
     * 通过状态查询意见反馈
     */
    List<FeedBackBO> getFeedbackList(@Param("status") String status, @Param("pageOffset")Integer pageOffset, @Param("pageSize")Integer pageSize);


    /**
     *查询总条数
     */
    Integer getFeedbackListCount(@Param("status") String status);

    /**
     * 修改意见反馈状态
     */
    boolean updFeedback(FeedBackBO feedBackBO);

}
