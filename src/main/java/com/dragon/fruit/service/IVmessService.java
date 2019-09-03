package com.dragon.fruit.service;

import java.util.Map;

/**
 * 线路操service
 */
public interface IVmessService {
    /**
     * 查找当前用户可用线路
     * @param userID
     * @return
     */
    Map<String,Object> listVmess(Long userID);

    /**
     * 保存用户当前选择的线路
     * @param userID
     * @param vmessID
     * @return
     */
    Map<String,Object> saveUserVmess(Long userID, Long vmessID);
}
