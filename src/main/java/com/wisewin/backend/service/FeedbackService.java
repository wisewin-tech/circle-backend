package com.wisewin.backend.service;

import com.wisewin.backend.dao.FeedbackDAO;
import com.wisewin.backend.entity.bo.FeedBackBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("FeedbackService")
@Transactional
public class FeedbackService {
    @Resource
    private FeedbackDAO feedbackDAO;

    /**
     * 查询意见反馈信息
     */
    public List<FeedBackBO> getFeedbackList(String status,Integer pageOffset,Integer pageSize){
        return feedbackDAO.getFeedbackList(status,pageOffset,pageSize);
    }


    /**
     * 查询总条数
     */
    public Integer getFeedbackListCount(String status){
        return feedbackDAO.getFeedbackListCount(status);
    }

    /**
     * 修改意见反馈
     */
    public boolean updFeedback(FeedBackBO feedBackBO){
        return feedbackDAO.updFeedback(feedBackBO);
    }

}
