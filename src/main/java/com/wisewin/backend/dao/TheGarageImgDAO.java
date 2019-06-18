package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.TheGarageImgBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 王彬 on 2019/5/20.
 */
public interface TheGarageImgDAO {

    /**
     * 查询车库图片信息
     * @return
     */
    List<TheGarageImgBO> queryTheGarageImg();

    /**
     * 删除车库图片信息
     * @param idArr
     * @return
     */
    Integer delTheGarageImg(@Param("idArr") Integer[] idArr);

    /**
     * 修改
     * @param theGarageImg
     */
    void updateTheGarageImg(TheGarageImgBO theGarageImg);

    /**
     * 添加
     * @param theGarageImg
     */
    void insetTheGarageImg(TheGarageImgBO theGarageImg);

}
