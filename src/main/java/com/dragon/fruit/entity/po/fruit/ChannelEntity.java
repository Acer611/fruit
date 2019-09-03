package com.dragon.fruit.entity.po.fruit;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 频道的实体类
 * @author  Gaofei
 * @date 2018-10-30
 */
public class ChannelEntity implements Serializable {


	@ApiModelProperty(value = "频道集合")
	private String channelGuid;
	@ApiModelProperty(value = "频道名称")
	private String channelName;
	@ApiModelProperty(value = "所属APP")
	private String appId;
	@ApiModelProperty(value = "是否改变")
	private Boolean isChange;
	@ApiModelProperty(value = "是否启用")
	private Boolean isEnabale;
	@ApiModelProperty(value = "创建时间")
	private Date createDate;
	@ApiModelProperty(value = "修改时间")
	private Date updateDate;
	@ApiModelProperty(value = "创建人")
	private String createUser;
	@ApiModelProperty(value = "修改人")
	private String updateUser;
	@ApiModelProperty(value = "")
	private Integer aduitOut;
	@ApiModelProperty(value = "区域代码")
	private String areaCode;
	@ApiModelProperty(value = "内容来源")
	private String contentForm;
	@ApiModelProperty(value = "头类型")
	private Integer headFigureType;
	@ApiModelProperty(value = "频道类型")
	private Integer channelType;
	@ApiModelProperty(value = "频道排序")
	private Integer channelSort;
	@ApiModelProperty(value = "是否是推荐频道")
	private Boolean isRecommand;
	@ApiModelProperty(value = "访问次数")
	private Long visitCount;

	public String getChannelGuid() {
		return channelGuid;
	}

	public void setChannelGuid(String channelGuid) {
		this.channelGuid = channelGuid;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Boolean getChange() {
		return isChange;
	}

	public void setChange(Boolean change) {
		isChange = change;
	}

	public Boolean getEnabale() {
		return isEnabale;
	}

	public void setEnabale(Boolean enabale) {
		isEnabale = enabale;
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

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Integer getAduitOut() {
		return aduitOut;
	}

	public void setAduitOut(Integer aduitOut) {
		this.aduitOut = aduitOut;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getContentForm() {
		return contentForm;
	}

	public void setContentForm(String contentForm) {
		this.contentForm = contentForm;
	}

	public Integer getHeadFigureType() {
		return headFigureType;
	}

	public void setHeadFigureType(Integer headFigureType) {
		this.headFigureType = headFigureType;
	}

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public Integer getChannelSort() {
		return channelSort;
	}

	public void setChannelSort(Integer channelSort) {
		this.channelSort = channelSort;
	}

	public Boolean getRecommand() {
		return isRecommand;
	}

	public void setRecommand(Boolean recommand) {
		isRecommand = recommand;
	}

	public Long getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Long visitCount) {
		this.visitCount = visitCount;
	}
}


