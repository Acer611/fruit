package com.dragon.fruit.controller;

import com.dragon.fruit.common.constant.UserConstant;
import com.dragon.fruit.common.utils.DateUtils;
import com.dragon.fruit.common.utils.IPUtils;
import com.dragon.fruit.entity.po.fruit.ArticleInfoEntity;
import com.dragon.fruit.entity.vo.response.ArticleListResponse;
import com.dragon.fruit.entity.vo.response.HomeResponse;
import com.dragon.fruit.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * 果实访问处理的controller
 * Created by Gaofei on 2018/10/30
 */

@Api(tags = "果实频道文章接口")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;


    /**
     * 首页
     * @param userGuid
     * @param request
     * @return
     */
    @ApiOperation(value = "首页")
    @ResponseBody
    @GetMapping("/home")
    public HomeResponse home(@ApiParam(value = "用户ID", required = true) @RequestParam(name = "userGuid") String userGuid,
                             HttpServletRequest request){

        String IP = IPUtils.getIP(request);
        System.out.println(" IP地址为： "+ IP);
        HomeResponse homeResponse = articleService.getHomeInfo(userGuid,IP);

        return homeResponse;
    }



    /**
     * 查询特定频道下的文章信息列表（上滑动作）
     * @param channelGuid 频道的ID
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "分页查询特定频道文章信息（上滑动作）")
    @ResponseBody
    @GetMapping("/findArticleUpglide")
    public ArticleListResponse findArticleListByChannelID(@ApiParam(value = "频道ID", required = true) @RequestParam(name = "channelGuid") String channelGuid,
                                                          @ApiParam(value = "上面十条最后一篇文章的创建时间，若为第一次点击频道则传null 格式为yyy-MM-dd HH:mm:ss", required = true) @RequestParam(name = "createTime") String createTime,
                                                          @ApiParam(value = "用户ID", required = true) @RequestParam(name = "userGuid") String userGuid,
                                                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                                          @RequestParam(name = "pageSize", required = false, defaultValue = "10")int pageSize,
                                                          HttpServletRequest request){
        String IP = IPUtils.getIP(request);
        Date createDate = new Date();
        if(null != createTime && !createTime.equalsIgnoreCase("null")){
            createDate = DateUtils.stringToDate(createTime);
        }
        return  articleService.findArticeleByChannelID(channelGuid,createDate,IP,userGuid,pageNum,pageSize);
    }



    /**
     * 查询特定频道下的文章信息列表（下拉动作）
     * @param channelGuid 频道ID
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "获取特定频道下最新文章信息（下拉动作）")
    @ResponseBody
    @GetMapping("/findNewArticleList")
    public ArticleListResponse findNewArticleInfo(@ApiParam(value = "频道ID", required = true) @RequestParam(name = "channelGuid") String channelGuid,
                                                  @ApiParam(value = "用户ID", required = true) @RequestParam(name = "userGuid") String userGuid,
                                                  @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                                  @RequestParam(name = "pageSize", required = false, defaultValue = "10")int pageSize,
                                                  HttpServletRequest request){
        String IP = IPUtils.getIP(request);
        return  articleService.findNewArticeleByChannelID(channelGuid,IP, userGuid,pageNum,pageSize);
    }



    @ApiOperation(value = "查询文章信息根据文章的ID")
    @ResponseBody
    @GetMapping("/QueryArticle")
    public ArticleInfoEntity findArticle(@ApiParam(value = "文章ID", required = true) @RequestParam(name = "titleId") String titleId,
                                         @ApiParam(value = "用户ID", required = true) @RequestParam(name = "userGuid") String userGuid,
                                         @ApiParam(value = "频道ID", required = true) @RequestParam(name = "channelId") String channelId,
                                         HttpServletRequest request){
        String IP = IPUtils.getIP(request);

        if(null==userGuid||userGuid.equalsIgnoreCase("null")){
            userGuid= UserConstant.DEFAULT_USER;
        }
        return  articleService.findArticle(titleId,IP,userGuid,channelId);
    }




}
