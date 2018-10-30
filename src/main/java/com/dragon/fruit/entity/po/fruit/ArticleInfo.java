package com.dragon.fruit.entity.po.fruit;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章实体
 * @author  Gaofei
 * @date 2018-10-30
 */
public class ArticleInfo implements Serializable {

	private Integer iD;
	private String categoryCode; 
	private String titleID; 
	private String title; 
	private String author;
	private String magazineName; 
	private Integer year;
	private Integer issue;
	private String columns;
	private Date articleCreateDate;
	private Date createDate; 
	private Date updateDate;
	private String abstractName;
	private Integer wordSize;
	private Double rank;
	private Integer isOnline;
	private String articleContent; 
	private String imgs;
	private Boolean hasImage;
	private Integer recommend;
	private String keyword;
	private String simgs;
	private String[] imgsArray;
	private String oprator;
	private String userId;

	public Integer getiD() {
		return iD;
	}

	public void setiD(Integer iD) {
		this.iD = iD;
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

	public String[] getImgsArray() {
		return imgsArray;
	}

	public void setImgsArray(String[] imgsArray) {
		this.imgsArray = imgsArray;
	}

	public String getOprator() {
		return oprator;
	}

	public void setOprator(String oprator) {
		this.oprator = oprator;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}


