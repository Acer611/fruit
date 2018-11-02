package com.dragon.fruit.dao.fruit.sqlprovider;

import com.dragon.fruit.entity.po.fruit.ArticleInfoEntity;
import com.dragon.fruit.entity.po.fruit.UserArticleInfoVisitLogEntity;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

/**
 * @program fruit
 * @description: 文章访问记录sql
 * @author: Gaofei
 * @create: 2018/11/02 15:35
 */

public class ArticleVisitSQLProvider {


    public String updateUserVistInfo(UserArticleInfoVisitLogEntity userArticleInfoVisitLogEntity){
        return new SQL() {{
            {
                UPDATE("UserArticleVisitLog");
                if(!StringUtils.isEmpty(userArticleInfoVisitLogEntity.getVisitCount())){
                    SET("VisitCount=#{visitCount}");
                }
                if(!StringUtils.isEmpty(userArticleInfoVisitLogEntity.getVisitTime())){
                    SET("VisitTime=#{visitTime}");
                }

                WHERE("ID=#{ID}");
            }
        }}.toString();
    }


    public String updateVisitCount(ArticleInfoEntity articleInfoEntity){
        return new SQL() {{
            {
                UPDATE("ArticleInfo");
                if(!StringUtils.isEmpty(articleInfoEntity.getTitleID())){
                    SET("VisitCount=#{visitCount}");
                }

                WHERE("TitleID=#{titleID}");
            }
        }}.toString();
    }


}
