package com.wisewin.backend.service;

import com.wisewin.backend.dao.FeedbackDAO;
import com.wisewin.backend.entity.bo.FeedbackBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by 王彬 on 2019/5/16.
 */
@Service("feedbackService")
@Transactional
public class FeedbackService {

    @Resource
    private FeedbackDAO feedbackDAO;

    public FeedbackBO queryFeedback(String id){
        return feedbackDAO.queryFeedback(id);
    }

    public void updateFeedback(FeedbackBO feedback){
        feedback.setUpdateTime(new Date());
        feedbackDAO.updateFeedback(feedback);
    }

    public void insertFeedback(FeedbackBO feedback){
        feedback.setCreateTime(new Date());
        feedback.setUpdateTime(new Date());
        feedbackDAO.insertFeedback(feedback);
    }
}
