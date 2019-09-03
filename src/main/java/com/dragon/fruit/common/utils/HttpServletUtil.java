package com.dragon.fruit.common.utils;

import cn.jiguang.common.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取ip地址工具
 * @author  Gaofei 2018-07-18
 */
public class HttpServletUtil {
	/**
	 * 获取真实的远程客户端IP，不受Nginx和apache等前端webserver分发影响
	 * @param request
	 * @return
	 * @author
	 */
	public static String getRealRemoteAddr(HttpServletRequest request) {
		if (request == null)
			return null;
		String ip = null;
		StringBuffer sf = new StringBuffer("");
		// support multiple values
		// 多个x-forwarded-for，中间以逗号分隔开
		Enumeration values = request.getHeaders("x-forwarded-for"); 
		if (values != null) {
			// 循环多个 ，获取包括代理ip在内的多个ip
			while (values.hasMoreElements()) {
				String value = (String) values.nextElement();
				if (sf.length() > 0) {
					sf.append(",").append(value);
				} else {
					sf.append(value);
				}
			}
		}
		ip = sf.toString();
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static String getRealRemoteAddr(Map<String, String> headerMap, String remoteAddr) {
		if (headerMap ==null)
			return null;

		String ip = headerMap.get("X-Real-IP");//该值为运维配置项
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = headerMap.get("x-forwarded-for");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = headerMap.get("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = headerMap.get("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = remoteAddr;
		}
		return ip;
	}	

	public static String getServerIP() {
		   String ip = "";
	       try {
	           Enumeration<?> e1 = (Enumeration<?>) NetworkInterface.getNetworkInterfaces();
	           while (e1.hasMoreElements()) {
	               NetworkInterface ni = (NetworkInterface) e1.nextElement();
	               if (!ni.getName().equals("eth0")) {
	                   continue;
	               } else {
	                   Enumeration<?> e2 = ni.getInetAddresses();
	                   while (e2.hasMoreElements()) {
	                       InetAddress ia = (InetAddress) e2.nextElement();
	                       if (ia instanceof Inet6Address)
	                           continue;
	                       ip = ia.getHostAddress();
	                   }
	                   break;
	               }
	           }
	       } catch (SocketException e) {
	           System.exit(-1);
	       }
	       return ip;

	}
	
	public static String getCurrentUrl(HttpServletRequest request){
        String url = request.getServletPath();
        if (!StringUtils.isEmpty(request.getQueryString())) {
			url = url + "?" + request.getQueryString();
		}
		
        return url;
	}
	
	public static String getCompleteUrl(HttpServletRequest request){
        //String url = request.getScheme() + "://" + request.getServerName() + request.getRequestURI();
		String url = request.getRequestURL().toString();
		if (!StringUtils.isEmpty(request.getQueryString())) {
			url = url + "?" + request.getQueryString();
		}
        return url;
	}

	public static String getCompleteUrl(HttpServletRequest request, String url) {
		if (!url.startsWith("https") && !url.startsWith("http")) {
			url = request.getScheme() + "://"
					+ request.getServerName() + ":"
					+ request.getServerPort()
					+ request.getContextPath()
					+ url;
			// 替换80及443端口
			url = url.replace(":80/", "/");
			url = url.replace(":443/", "/");
		}
		return url;
	}

	public static Map<String, String> printRequestHeadersInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration headerNames = request.getHeaderNames();
		System.out.println("Request header start:--------------------------");
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
			System.out.println("key:" + key + ", value:" + value);
		}
		System.out.println("Request header end:----------------------------");
		return map;
	}

	public static Map<String, String> printResponseHeadersInfo(HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		Collection<String> headerNames = response.getHeaderNames();
		System.out.println("Response header start:--------------------------");
		for (String key : headerNames) {
			String value = response.getHeader(key);
			map.put(key, value);
			System.out.println("key:" + key + ", value:" + value);
		}
		System.out.println("Response header end:----------------------------");
		return map;
	}

}
