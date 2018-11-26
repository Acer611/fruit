<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<title></title>
<meta charset="utf-8">
<meta name="Keywords" content="龙源数媒，数字文化城市">
<meta name="description" content="龙源数媒，数字文化城市" />
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="css/article.css">
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
	<div class="container">
		<div class="top">
			<h1>${articleInfo.title}</h1>
			<div class="source">来源：${articleInfo.magazineName} &nbsp; 作者：${articleInfo.author}</div>
		</div>
		<!-- 文章主体-->
		<div class="content">
			${articleInfo.articleContent}
			<p class="banquan">版权支持: 龙源</p>
		</div>
	</div>
</body>
</html>
