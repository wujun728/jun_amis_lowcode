package com.jun.plugin.system.webapi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * ClassName: IP Description: layui date: 2020/4/15 16:42
 *
 * 
 * 
 * @since JDK 1.8 通过ip 获取对应的地址 简易版
 */

public class IP {
	public static void main(String[] args) throws IOException, NoSuchFieldException {
//        http://api.map.baidu.com/location/ip?ak=您的AK&ip=您的IP&coor=bd09ll
//        String a="\u5317\u4eac\u5e02";
//        System.out.println(a);

		BaiduMap baiduMap = getWebapiAddress("223.152.19.101", "nMBfefpMVsdP6BUVn350G1ByUmOu07GK");
		System.out.println(baiduMap);

	}

	/**
	 * 通过ip获取地址
	 *
	 * @param ip
	 * @param ak
	 * @throws IOException
	 */
	public static BaiduMap getWebapiAddress(String ip, String ak) throws IOException, NoSuchFieldException {
//        nMBfefpMVsdP6BUVn350G1ByUmOu07GK  "223.152.19.101"

		// 创建默认http连接
		HttpClient client = HttpClients.createDefault();
		// 创建一个post请求
		HttpPost post = new HttpPost("http://api.map.baidu.com/location/ip");
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		// 传递的参数
		paramList.add(new BasicNameValuePair("ip", ip));
		// ak凭证
		paramList.add(new BasicNameValuePair("ak", ak));
		// 传递的参数
		// paramList.add(new BasicNameValuePair("sn", "2b91b2409107"));
//        paramList.add(new BasicNameValuePair("coor", ""));
		// 把参转码后放入请求实体中
		HttpEntity entitya = new UrlEncodedFormEntity(paramList, "utf-8");
		// 把请求实体放post请求中
		post.setEntity(entitya);
		// 用http连接去执行get请求并且获得http响应
		HttpResponse response = client.execute(post);
		// 从response中取到响实体
		HttpEntity entity = response.getEntity();
		// 把响应实体转成文本
		String html = EntityUtils.toString(entity);
		JSONObject jsonObject = JSON.parseObject(html);
		String top_address = jsonObject.getString("address");
		BaiduMap baiduMap = new BaiduMap();
		JSONObject content = jsonObject.getJSONObject("content");
		String address = content.getString("address");
		String point = content.getString("point");
		String status = jsonObject.getString("status");

		baiduMap.setAddress(top_address);
		baiduMap.setStatus(Integer.parseInt(status));
		baiduMap.getContent().put("address", address);
		baiduMap.getContent().put("point", point);

		return baiduMap;
	}

}
