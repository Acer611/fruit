package com.dragon.fruit.controller;

import com.dragon.fruit.common.utils.IPUtils;
import com.dragon.fruit.entity.po.fruit.ArticleInfo;
import com.dragon.fruit.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 果实访问处理的controller
 * Created by Gaofei on 2018/10/30
 */

@Api(tags = "用户管理")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;




    @ApiOperation(value = "查询文章信息根据文章的ID")
    @ResponseBody
    @GetMapping("/findArticle")
    public ArticleInfo findArticle(@ApiParam(value = "文章ID", required = true) @RequestParam(name = "titleId") String titleId,
            HttpServletRequest request){
        return  articleService.findArticle(titleId);
    }

    /**
     * 查询特定频道下的文章信息列表（上滑动作）
     * @param channelGuid 频道的ID
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "分页查询特定频道文章信息（上滑按时间倒序）")
    @ResponseBody
    @GetMapping("/findArticleInfo")
    public List<ArticleInfo> findArticleInfo(@ApiParam(value = "频道ID", required = true) @RequestParam(name = "channelGuid") String channelGuid,
                        @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "10")int pageSize,
    HttpServletRequest request){

        return  articleService.findArticeleByChannelID(channelGuid,pageNum,pageSize);
    }


    /**
     * 查询特定频道下的文章信息列表（下拉动作）
     * @param channelGuid 频道ID
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "获取特定频道下最新文章信息（上滑）")
    @ResponseBody
    @GetMapping("/findNewArticleInfo")
    public List<ArticleInfo> findNewArticleInfo(@ApiParam(value = "频道ID", required = true) @RequestParam(name = "channelGuid") String channelGuid,
                                             @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                             @RequestParam(name = "pageSize", required = false, defaultValue = "10")int pageSize,
                                                HttpServletRequest request){
        String IP = IPUtils.getIP(request);
        return  articleService.findNewArticeleByChannelID(channelGuid,IP,pageNum,pageSize);
    }


}
