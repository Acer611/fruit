package com.dragon.fruit.entity.po.fruit;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章实体
 * @author  Gaofei
 * @date 2018-10-30
 */
public class ArticleInfoEntity implements Serializable {

    @ApiModelProperty(value = "唯一标示")
	private Integer Id;
	@ApiModelProperty(value = "所属分类")
	private String categoryCode;
	@ApiModelProperty(value = "文章ID")
	private String titleID;
	@ApiModelProperty(value = "文章标题")
	private String title;
	@ApiModelProperty(value = "文章作者")
	private String author;
	@ApiModelProperty(value = "所属杂志")
	private String magazineName;
	@ApiModelProperty(value = "年份")
	private Integer year;
	@ApiModelProperty(value = "期数")
	private Integer issue;
	@ApiModelProperty(value = "")
	private String columns;
	@ApiModelProperty(value = "文章创建时间")
	private Date articleCreateDate;
	@ApiModelProperty(value = "文章原时间")
	private Date createDate;
	@ApiModelProperty(value = "修改时间")
	private Date updateDate;
	@ApiModelProperty(value = "")
	private String abstractName;
	@ApiModelProperty(value = "字数")
	private Integer wordSize;
	@ApiModelProperty(value = "")
	private Double rank;
	@ApiModelProperty(value = "是否在线")
	private Integer isOnline;
	@ApiModelProperty(value = "文章内容")
	private String articleContent;
	@ApiModelProperty(value = "封面图片")
	private String imgs;
	@ApiModelProperty(value = "是否有图片")
	private Boolean hasImage;
	@ApiModelProperty(value = "是否推荐")
	private Integer recommend;
	@ApiModelProperty(value = "关键字")
	private String keyword;
	@ApiModelProperty(value = "内容图片")
	private String simgs;
	@ApiModelProperty(value = "操作者")
	private String oprator;
    @ApiModelProperty(value = "文章被访问次数")
    private Long visitCount;
	@ApiModelProperty(value = "封面图列表")
    private List<String> imageList;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getTitleID() {
		return titleID;
	}

	public void setTitleID(String titleID) {
		this.titleID = titleID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getMagazineName() {
		return magazineName;
	}

	public void setMagazineName(String magazineName) {
		this.magazineName = magazineName;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getIssue() {
		return issue;
	}

	public void setIssue(Integer issue) {
		this.issue = issue;
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	public Date getArticleCreateDate() {
		return articleCreateDate;
	}

	public void setArticleCreateDate(Date articleCreateDate) {
		this.articleCreateDate = articleCreateDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getAbstractName() {
		return abstractName;
	}

	public void setAbstractName(String abstractName) {
		this.abstractName = abstractName;
	}

	public Integer getWordSize() {
		return wordSize;
	}

	public void setWordSize(Integer wordSize) {
		this.wordSize = wordSize;
	}

	public Double getRank() {
		return rank;
	}

	public void setRank(Double rank) {
		this.rank = rank;
	}

	public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public Boolean getHasImage() {
		return hasImage;
	}

	public void setHasImage(Boolean hasImage) {
		this.hasImage = hasImage;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSimgs() {
		return simgs;
	}

	public void setSimgs(String simgs) {
		this.simgs = simgs;
	}

	public String getOprator() {
		return oprator;
	}

	public void setOprator(String oprator) {
		this.oprator = oprator;
	}

    public Long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Long visitCount) {
        this.visitCount = visitCount;
    }

	public List<String> getImageList() {
		return imageList;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}
}


