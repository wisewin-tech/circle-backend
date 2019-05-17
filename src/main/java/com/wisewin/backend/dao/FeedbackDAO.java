package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.FeedbackBO;

/**
 * Created by 王彬 on 2019/5/16.
 */
public interface FeedbackDAO {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    FeedbackBO queryFeedback(String id);

    /**
     * 修改
     * @param feedback
     */
    void updateFeedback(FeedbackBO feedback);

    /**
     * 添加
     * @param feedback
     */
    void insertFeedback(FeedbackBO feedback);
}
