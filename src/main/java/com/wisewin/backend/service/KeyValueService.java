package com.wisewin.backend.service;

import com.wisewin.backend.dao.KeyValueDAO;
import com.wisewin.backend.entity.bo.KeyValueBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class KeyValueService {

    @Resource
    KeyValueDAO keyValueDAO;

    public Integer updKeyValue(KeyValueBO keyValueBO){
        return keyValueDAO.updKeyValue(keyValueBO);
    }

    public List<KeyValueBO> getKeyValueList(){
        return keyValueDAO.getKeyValueList();
    }

    public KeyValueBO getValueByKey(String key){
        return keyValueDAO.getValueByKey(key);
    }
}
