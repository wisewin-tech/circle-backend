package com.wisewin.backend.service;

import com.wisewin.backend.dao.NewbitHelpDAO;
import com.wisewin.backend.entity.bo.NewbitHelpBO;
import com.wisewin.backend.entity.bo.NewbitHelpContextBO;
import com.wisewin.backend.entity.param.NewditHelpParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("newbitHelpService")
@Transactional
public class NewbitHelpService {
    @Resource
    private NewbitHelpDAO newbitHelpDAO;

    /**
     * 查询新手帮助信息
     * @return
     */
    public List<NewbitHelpBO> selectNewbitHelp(){
    return newbitHelpDAO.selectNewbitHelp();
}

    /**
     * 新增新手帮助信息
     * @return
     */
    public Integer insertNewbitHelp(NewditHelpParam newditHelpParam){
    return  newbitHelpDAO.insertNewbitHelp(newditHelpParam);
}

    /**
     * 删除新手帮助信息
     * @param id
     * @return
     */
    public boolean deleteNewbitHelp(Integer id){

        return newbitHelpDAO.deleteNewbitHelp(id);
}

    /**
     * 修改新手帮助信息
     * @param masterTitle 标题
     * @param id
     * @return
     */
    public boolean editNewditHelp(String masterTitle,Integer id){
        return newbitHelpDAO.editNewditHelp(masterTitle,id);
    }
}
