package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.KeyValueBO;

import java.util.List;

public interface KeyValueDAO {
    Integer updKeyValue(KeyValueBO keyValueBO);
    List<KeyValueBO> getKeyValueList();
    KeyValueBO getValueByKey(String key);
}
