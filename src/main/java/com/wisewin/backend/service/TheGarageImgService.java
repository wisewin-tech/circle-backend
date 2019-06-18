package com.wisewin.backend.service;

import com.wisewin.backend.dao.TheGarageImgDAO;
import com.wisewin.backend.entity.bo.TheGarageImgBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("theGarageImgService")
@Transactional
public class TheGarageImgService {
    @Resource
    private TheGarageImgDAO theGarageImgDAO;

    /**
     * 查询车库图片信息
     * @return
     */
    public List<TheGarageImgBO> queryTheGarageImg(){
        return theGarageImgDAO.queryTheGarageImg();
    }

    /**
     * 删除车库图片信息
     * @param idArr
     * @return
     */
    public Integer delTheGarageImg(Integer[] idArr){
        return theGarageImgDAO.delTheGarageImg(idArr);
    }

}
