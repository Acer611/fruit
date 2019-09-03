package com.dragon.fruit.service.impl;

import com.dragon.fruit.dao.fruit.APPDao;
import com.dragon.fruit.dao.uuid.BannerDao;
import com.dragon.fruit.entity.po.uuid.Banner;
import com.dragon.fruit.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * banner service实现层
 */
@Service(value="bannerService")
public class BannerServiceImmpl  implements IBannerService {

    @Autowired
    BannerDao bannerMapper;

    @Override
    public Map<String, Object> listBanner() {
        Map map = new HashMap();

        List<Banner> bannerList = bannerMapper.list();
        for (Banner baner:bannerList) {
            baner = new Banner(baner);
        }
        map.put("banner",bannerList);
        map.put("code","200");
        map.put("message","success");
        return map;
    }
}
