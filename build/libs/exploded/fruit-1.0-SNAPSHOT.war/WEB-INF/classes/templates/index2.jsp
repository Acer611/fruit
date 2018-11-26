<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<!--在您实际的项目中,请留意mescroll-option.css的图片路径是否引用正确,可写成绝对路径或网络地址-->
		<!--可把mescroll-option.css代码拷贝到mescroll.min.css中,合为一个css文件,方便引用-->
		<link rel="stylesheet" href="css/mescroll.min.css">
		<link rel="stylesheet" href="css/list.css">
				<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?552f5833bd8222c2dc049c1385f7ece2";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
	</head>

	<body>
		<!--标题-->
		<div class="wrapper">
		<div class="header">
			<div class="scrollx mescroll-touch-x">
				<div id="scrollxContent" class="scrollx-content">
					<ul id="nav" class="nav">
						<!-- <li class="active" ><a href="${ctx}">推荐<a></li> -->
					</ul>
				</div>
			</div>
		</div>
		</div>
		<p id="downloadTip" class="download-tip">更新了10条内容</p>
		<!--滑动区域-->
		<div id="mescroll" class="mescroll">
			<!--展示上拉加载的数据列表-->
			<ul id="dataList" class="data-list">
				<c:forEach var="articleInfo" items="${articleInfo}">
					 <c:choose>
					 	<c:when test="${fn:length(articleInfo.imgsArray)>2}">
					 		<a href="${ctx}/selectAritleByTitleId?TitleId=${articleInfo.titleID}&userId=${articleInfo.userId}&ChannelGuid=${ChannelGuid}">
						 		<div class="box ">
									<div class="pic3">
										<div class="tit">${articleInfo.title}</div>
										<div class="pic">
											<li><div style="background: url(${articleInfo.imgsArray[0]}) no-repeat" class="picok"></div></li>
											<li><div style="background: url(${articleInfo.imgsArray[1]}) no-repeat" class="picok"></div></li>
											<li><div style="background: url(${articleInfo.imgsArray[2]}) no-repeat" class="picok"></div></li>
										</div>
										<div class="source">${articleInfo.magazineName}</div>
									</div>
								</div>
							</a>
					 	</c:when>
					 	<c:otherwise>
					 		<a href="${ctx}/selectAritleByTitleId?TitleId=${articleInfo.titleID}&userId=${articleInfo.userId}&ChannelGuid=${ChannelGuid}">
						 		<div class="box ">
									<div class="pic1">
										<div class="pic">
											<div style="background: url(${articleInfo.imgsArray[0]}) no-repeat" class="picok"></div>
										</div>
										<div class="tit">
											<div class="tit1">
												<div class="tit2">${articleInfo.title}</div>
											</div>
										</div>
										<div class="source">${articleInfo.magazineName}</div>
									</div>
								</div>
							</a>
					 	</c:otherwise>
					 </c:choose>
				</c:forEach>
			</ul>
		</div>
	</body>
	
	<!--在您实际的项目中,请留意mescroll-option.js的图片路径是否引用正确,可写成绝对路径或网络地址-->
	<!--可把mescroll-option.js代码拷贝到mescroll.min.js中,合为一个js文件,方便引用-->
	<script src="js/mescroll.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/mescroll-option.js" type="text/javascript" charset="utf-8"></script>
	<!--mescroll本身不依赖jq,这里为了模拟发送ajax请求-->
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/iscroll5.js" type="text/javascript" charset="utf-8"></script>


	<script type="text/javascript" charset="utf-8">
		var ChannelGuid = "${ChannelGuid}";
		
		$(function(){
			//创建MeScroll对象
			var mescroll = initMeScroll("mescroll", {
				down:{
					auto:false,//是否在初始化完毕之后自动执行下拉回调callback; 默认true
					callback: downCallback //下拉刷新的回调
				},
				up: {
					auto:true,//初始化完毕,是否自动触发上拉加载的回调
					isBoth: true, //上拉加载时,如果滑动到列表顶部是否可以同时触发下拉刷新;默认false,两者不可同时触发; 这里为了演示改为true,不必等列表加载完毕才可下拉;
					callback: upCallback, //上拉加载的回调
					isBounce: false, //此处禁止ios回弹,解析(务必认真阅读,特别是最后一点): http://www.mescroll.com/qa.html#q10

				}
			});
			
			/*下拉刷新的回调 */
			function downCallback(){
				//加载轮播数据..
				//...
				//加载列表数据
                getDownListDataFromNet(0, 1, function(data){
					//联网成功的回调,隐藏下拉刷新的状态
					mescroll.endSuccess();
					//设置列表数据
					setListData(data, false);
					$("#downloadTip").css("top","40px");
					$(".mescroll").css("top","70px");
					setTimeout(function () {
					$("#downloadTip").css("top","0px");
					$(".mescroll").css("top","40px");
					},3000);
				}, function(){
					//联网失败的回调,隐藏下拉刷新的状态
	                mescroll.endErr();
				});
			}
			
			/*上拉加载的回调 page = {num:1, size:10}; num:当前页 从1开始, size:每页数据条数 */
			function upCallback(page){
				//mescroll.lockDownScroll(true);
				//联网加载数据
                getUpListDataFromNet(page.num, page.size, function(curPageData){
					//联网成功的回调,隐藏下拉刷新和上拉加载的状态;
					//mescroll会根据传的参数,自动判断列表如果无任何数据,则提示空;列表无下一页数据,则提示无更多数据;
					console.log("page.num="+page.num+", page.size="+page.size+", curPageData.length="+curPageData.length);
					
					//方法一(推荐): 后台接口有返回列表的总页数 totalPage
					//mescroll.endByPage(curPageData.length, totalPage); //必传参数(当前页的数据个数, 总页数)
					
					//方法二(推荐): 后台接口有返回列表的总数据量 totalSize
					//mescroll.endBySize(curPageData.length, totalSize); //必传参数(当前页的数据个数, 总数据量)
					
					//方法三(推荐): 您有其他方式知道是否有下一页 hasNext
					//mescroll.endSuccess(curPageData.length, hasNext); //必传参数(当前页的数据个数, 是否有下一页true/false)
					
					//方法四 (不推荐),会存在一个小问题:比如列表共有20条数据,每页加载10条,共2页.如果只根据当前页的数据个数判断,则需翻到第三页才会知道无更多数据,如果传了hasNext,则翻到第二页即可显示无更多数据.
					mescroll.endSuccess(curPageData.length);
					
					//设置列表数据
					setListData(curPageData, true);
				}, function(){
					//联网失败的回调,隐藏上拉加载的状态
	                mescroll.endErr();
				});
			}
			
			/*设置列表数据*/
			function setListData(curPageData,isAppend){
				if (!isAppend) {
					$("#dataList").find("li").remove();
					$("#dataList").find("a").remove();
				}
				var listDom=document.getElementById("dataList");
				for (var i = 0; i < curPageData.length; i++) {
					var str = "";
					var pd = curPageData[i];
                    var imgs = pd.imgs;

                    imgs = jQuery.parseJSON(imgs);

					//alert(imgs);
					if(imgs.length > 2){//三图
						str += '<a href="${ctx}/selectAritleByTitleId?TitleId='+pd.titleID+'&userId='+pd.userId+'&ChannelGuid='+ChannelGuid+'" target="_blank">'
						+'<div class="box ">'
						 +'<div class="pic3">'
						 +'<div class="tit">'
						 +pd.title
						 +'</div>'
						 +'<div class="pic">'
						 +'<li><div style="background: url('+imgs[0].url+') no-repeat" class="picok"></div></li>'
						 +'<li><div style="background: url('+imgs[1].url+') no-repeat" class="picok"></div></li>'
						 +'<li><div style="background: url('+imgs[2].url+') no-repeat" class="picok"></div></li>'
						 +'</div>'
						 +'<div class="source">'+pd.magazineName+'</div>'
						 +'</div>'
						 +'</div>'
						 +'</a>';
					}else{
						str += '<a href="${ctx}/selectAritleByTitleId?TitleId='+pd.titleID+'&userId='+pd.userId+'&ChannelGuid='+ChannelGuid+'" target="_blank">'
						    +'<div class="box ">'
							+'<div class="pic1">'
							+'<div class="pic">'
							+'<div style="background: url('+imgs[0].url+') no-repeat" class="picok"></div></div>'
							+'<div class="tit">'
							+'<div class="tit1">'
							+'<div class="tit2">'
							+pd.title
						 +'</div>'
						 +'</div>'
						 +'</div>'
						 +'<div class="source">'+pd.magazineName+'</div>'
						 +'</div>'
						 +'</div>' 
						 +'</a>';
					}
					var liDom=document.createElement("li");
					liDom.innerHTML=str;
					
					if (isAppend) {
						listDom.appendChild(liDom);//加在列表的后面,上拉加载
					} else{
						listDom.insertBefore(liDom, listDom.firstChild);//加在列表的前面,下拉刷新
					}
				}
			}
			
			/*联网加载列表数据
			 在您的实际项目中,请参考官方写法: http://www.mescroll.com/api.html#tagUpCallback
			 请忽略getListDataFromNet的逻辑,这里仅仅是在本地模拟分页数据,本地演示用
			 实际项目以您服务器接口返回的数据为准,无需本地处理分页.
			 * */
			function getUpListDataFromNet(pageNum,pageSize,successCallback,errorCallback) {
				//延时一秒,模拟联网
                setTimeout(function () {
                	$.ajax({
		                type: 'GET',
		                url: '${ctx}/findArticleInfo',
//		                url: '../res/pdlist1.json?num='+pageNum+'&size='+pageSize,
		                dataType: 'json',
		                data:{ChannelGuid:ChannelGuid,pageNum:pageNum+1,pageSize:10},
		                success: function(data){
		                	ChannelGuid = data.data.ChannelGuid;
		                	var listData=[];
		                	
		                	if(pageNum==0){
		                		//此处模拟下拉刷新返回的数据
								/* var i = Math.floor(Math.random() * data.length);//随机取一个商品返回
								data[i].pdName="【新增商品】 商品标题";
								listData.push(data[i]); */
								for (var i = 9; i >= 0; i--) {
				            		listData.push(data.data.articleInfo[i]);
				            	}
								
		                	}else{
		                		//此处模拟上拉加载返回的数据 (模拟分页数据)
								for (var i = 0; i < 10; i++) {
				            		if(i==data.length) break;
				            		listData.push(data.data.articleInfo[i]);
				            	}
		                	}
		                	
		                	//回调
		                	successCallback(listData);
		                },
		                error: errorCallback
		            });
                },500)
			}



            function getDownListDataFromNet(pageNum,pageSize,successCallback,errorCallback) {
                //延时一秒,模拟联网
                setTimeout(function () {
                    $.ajax({
                        type: 'GET',
                        url: '${ctx}/findNewArticleInfo',
//		                url: '../res/pdlist1.json?num='+pageNum+'&size='+pageSize,
                        dataType: 'json',
                        data:{ChannelGuid:ChannelGuid,pageNum:1,pageSize:10},
                        success: function(data){
                            ChannelGuid = data.data.ChannelGuid;
                            var listData=[];

                            if(pageNum==0){
                                //此处模拟下拉刷新返回的数据
                                /* var i = Math.floor(Math.random() * data.length);//随机取一个商品返回
                                data[i].pdName="【新增商品】 商品标题";
                                listData.push(data[i]); */
                                for (var i = 9; i >= 0; i--) {
                                    listData.push(data.data.articleInfo[i]);
                                }

                            }else{
                                //此处模拟上拉加载返回的数据 (模拟分页数据)
                                for (var i = 0; i < 10; i++) {
                                    if(i==data.length) break;
                                    listData.push(data.data.articleInfo[i]);
                                }
                            }

                            //回调
                            successCallback(listData);
                        },
                        error: errorCallback
                    });
                },500)
            }
			
		});
		var liIndex = 0;
		//获取频道	
		$.ajax({
		    type: 'GET',
		    url: '${ctx}/selectChannelByUserId',
		    dataType: 'json',
		    data:{UserId:"${UserId}"},
		    success: function(result){
		    	$('ul li').removeClass('active');
		    	var data=result.data;
				for (var i in data) {
					var strtap='<li';
						if(ChannelGuid == data[i].channelGuid){
							liIndex = parseInt(i)+1;
							strtap += ' class="active" style="postion:relative"';
						}				
						strtap += '><a href="${ctx}/selectAritleByChannelIddesc?ChannelGuid='+data[i].channelGuid+'&pageNum=1&pageSize=10&UserId=${UserId}">'+data[i].channelName+'</a></li>';
						//strtap += '><a onclick="getData(\''+data[i].channelGuid+'\')" >'+data[i].channelName+'</a></li>';
					$(".nav").append(strtap);
				}
				//alert(liIndex);
				//设置导航栏位置
				var nHDw = 0;
				var oHD = $("#scrollxContent");
				var hdScroll = new IScroll('#scrollxContent', { 
			        scrollX: true,
			        scrollY: false,
			        momentum: false,
			        preventDefault:false,
			        useTransition: true,
			        bounce:true,
			        click:false,
			        snap: false
				});
				$("#scrollxContent").find('li').each(function() {
			        nHDw += $(this).width();
			    });
				$('#scrollxConten').find('ul').css('width', nHDw);
				header = oHD.find('li.active');
				headerall = oHD.find('li');
				//alert(headerall);
				if(liIndex>6){
					var fheader= 1 ; 
					var lheader = 0;
					var rheader =0;
					var len = oHD.find('li').length+1;
					headerall.each(function(k,v){
						if(v.className=='active') { fheader=0;};
						if(fheader){
							lheader+=$(v).width();
						}else{
							rheader+=$(v).width();
						}
					})
					if(rheader > $(window).width()/2 ){
						if(lheader >= $(window).width()/2){
							hdScroll.scrollTo(-1*(lheader-$(window).width()/2+header.width()/2+120),0);
						}
					}else{
						if(liIndex != len){
							hdScroll.scrollTo(-1*(lheader-$(window).width()/2- header.width()/2+250),0);
						}else{
							hdScroll.scrollTo(-1*(nHDw - $(window).width()+250),0);
						}
					}
				}
		    }
		});
		
/* 		document.addEventListener('click', function(event) {
		    // 判断默认行为是否可以被禁用
		    if (event.cancelable) {
		        // 判断默认行为是否已经被禁用
		        if (!event.defaultPrevented) {
		            event.stopPropagation();
		        }
		    }
		}, false); */
	 	//获取列表
		/* function getData(ChannelGuid) {
			$("#dataList").find("li").remove();
			$("#dataList").find("a").remove();
			//延时一秒,模拟联网
               setTimeout(function () {
               	$.ajax({
	                type: 'GET',
	                url: '${ctx}/selectAritleByChannelId',
	                dataType: 'json',
	                data:{ChannelGuid:ChannelGuid,pageNum:1,pageSize:10},
	                success: function(data){
	                	ChannelGuid = data.data.ChannelGuid;
	                	//pageNum = data.data.pageNum;
	                	var curPageData = data.data.articleInfo;
                		//此处模拟上拉加载返回的数据 (模拟分页数据)
						for (var i = 0; i < data.data.length; i++) {
		            		var listDom=document.getElementById("dataList");
		    				for (var i = 0; i < curPageData.length; i++) {
		    					var str = "";
		    					var pd = curPageData[i];
		    					var imgs = pd.imgs;
		    					imgs = jQuery.parseJSON(imgs);
		    					//alert(imgs);
		    					if(imgs.length > 2){//三图
		    						str += '<div class="box ">'
		    						 +'<div class="pic3">'
		    						 +'<div class="tit">'
		    						 +pd.title
		    						 +'</div>'
		    						 +'<div class="pic">'
		    						 +'<li><img src="'+imgs[0].url+'"></li>'
		    						 +'<li><img src="'+imgs[1].url+'"></li>'
		    						 +'<li><img src="'+imgs[2].url+'"></li>'
		    						 +'</div>'
		    						 +'<div class="source">'+pd.magazineName+'</div>'
		    						 +'</div>'
		    						 +'</div>';
		    					}else{
		    						str += '<div class="box ">'
		    							+'<div class="pic1">'
		    							+'<div class="pic">'
		    							+'<img src="'+imgs[0].url+'"></div>'
		    							+'<div class="tit">'
		    							+'<div class="tit1">'
		    							+'<div class="tit2">'
		    							+pd.title
		    						 +'</div>'
		    						 +'</div>'
		    						 +'</div>'
		    						 +'<div class="source">'+pd.magazineName+'</div>'
		    						 +'</div>'
		    						 +'</div>' 
		    					}
		    					var liDom=document.createElement("li");
		    					liDom.innerHTML=str;
		    					listDom.appendChild(liDom);//加在列表的后面,上拉加载
		    				}
		            	}
	                }
	            });
               },500)
		} */
	</script>
	
<!-- <script type="text/javascript">
    var nHDw = "";
	$("#scrollxContent").find('li').each(function() {
		alert(123)
        nHDw += $(this).width();
    });
    alert(nHDw);
    $('#nav').find('ul').css('width', nHDw);
	var hdScroll = new IScroll('#scrollxContent', { 
        scrollX: true,
        scrollY: false,
        momentum: true,
        click:true,
        snap: false
	});
	header = oHD.find('li.active');
	headerall = oHD.find('li');
   // alert(header);
	if(header.index()>2){
		var fheader= 1 ; 
		var lheader = 0;
		var rheader =0;
		var len = oHD.find('li').length-1;
		headerall.each(function(k,v){
			if(v.className=='active') { fheader=0;};
			if(fheader){
				lheader+=$(v).width();
			}else{
				rheader+=$(v).width();
			}
		})
		if(rheader > $(window).width()/2 ){
			if(lheader >= $(window).width()/2){
				hdScroll.scrollTo(-1*(lheader-$(window).width()/2+header.width()/2),0);
			}
		}else{
			if(header.index() != len){
				hdScroll.scrollTo(-1*(lheader-$(window).width()/2-header.width()/2-rheader/2),0);
			}else{
				hdScroll.scrollTo(-1*(nHDw - $(window).width()),0);
			}
		}
	}
</script> -->
	
	

</html>