package com.dragon.fruit.service.impl;

import com.dragon.fruit.dao.fruit.PriceDao;
import com.dragon.fruit.entity.po.user.PriceConfig;
import com.dragon.fruit.service.IPriceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service(value="priceConfig")
public class PriceConfigServiceImpl implements IPriceConfig {
    @Autowired
    private PriceDao priceMapper;

    /**
     * 列出所有的价格配置信息
     * @return
     */
    @Override
    public Map list() {
        Map<String,Object> map = new HashMap<>();
        List<PriceConfig> priceConfigList = priceMapper.list();
        map.put("code","200");
        map.put("message","success");
        map.put("data",priceConfigList);
        return map;
    }

    /**
     * 查询价格配置的详细信息
     * @param id
     * @return
     */
    @Override
    public Map detail(Long id) {
        Map<String,Object> map = new HashMap<>();
        PriceConfig priceConfig = priceMapper.detail(id);
        map.put("code","200");
        map.put("message","success");
        map.put("data",priceConfig);
        return map;
    }
}
