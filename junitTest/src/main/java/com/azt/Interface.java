package com.azt;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class Interface {
	static final String JSESSIONID = "41021E2611EA587C1CF42BC2E4D9FD9E";
	static final String token = "964f10ce-f14d-4432-97b1-cb1612f11e72";
	/*
	 * 一级稽核通过
	 * */
	public String firstAudit(String orderID) {
		System.out.println("开始一级稽核");
		String response = "";
		//接口地址
		String path = "http://192.168.0.186:6008/saferycom/auditsys_firstAudit.do";
		//报文
        String parm = "id=0&doc_id=" + orderID + "&audit_state=1&reason=&revoked=0&achieve=1&bug_type=&auditHisIds=&hasProblem=0&audit_flag=0&iscodekey=false&ajaxFlag=true";
		try {
			Interface obj = new Interface();			
			response = obj.characterTransfer(obj.postRequest(path, parm));
			//System.out.println("Respond result:" + response);
		} catch (Exception e) {
			System.out.println("一级稽核失败");
			e.printStackTrace();
		}      
		return response;
	}
	/*
	 * 字符串转码
	 * */
	public String characterTransfer(String str) {
		String newstr = "";
		try {
			newstr = new String(str.getBytes("gbk"),"utf-8");			
		} catch (UnsupportedEncodingException e) {
			System.out.println("Fail to transfer character!");
			e.printStackTrace();
		}
		return newstr;
	}
	
	/*发送Post请求
	 * */
	public String postRequest(String path,String parm) {
		PrintWriter dataout = null;
		BufferedReader datain = null;
		String response = "";
		System.out.println("Start to Request。。。");
		try {
			URL realUrl = new URL(path);  
            // 打开和URL之间的连接  
            URLConnection connection = realUrl.openConnection();
			//设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
			connection.setDoOutput(true); 
			// 设置连接输入流为true  
	        connection.setDoInput(true); 
	        connection.setUseCaches(false);
	        // 设置请求方式为post
	        //connection.setRequestMethod("POST");
	        // 设置该HttpURLConnection实例是否自动执行重定向
	        //connection.setInstanceFollowRedirects(true);
	        //设置Cookie
	        connection.setRequestProperty("Cookie", "JSESSIONID=" + JSESSIONID +";"+"token=" + token);
	        connection.setRequestProperty("accept", "*/*");
	        connection.setRequestProperty("connection", "Keep-Alive");
	        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        connection.connect();
	        // 获取所有响应头字段  
//	        Map<String, List<String>> map = connection.getHeaderFields();  
//	        // 遍历所有的响应头字段  
//	        for (String key : map.keySet()) {  
//	            System.out.println(key + "--->" + map.get(key));  
//	        }  
	        //创建输出流
	        dataout = new PrintWriter(connection.getOutputStream());
	        //采用utf-8格式发送请求
	        //dataout=new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
	        //发送请求参数
	        dataout.print(parm);
	        //flush输出流的缓冲  
	        dataout.flush();
	        datain= new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			//循环读取流
	        while ((line = datain.readLine()) != null) {  
	            response = response + line;
	            }
	        datain.close();
	        
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	/*
	 * 参考使用
	 * */
	public static String sendPost(String url, String param) {  
        PrintWriter out = null;  
        BufferedReader in = null;  
        String result = "";  
        try {  
            URL realUrl = new URL(url);  
            // 打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();  
            // 设置通用的请求属性  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Cookie", "JSESSIONID=" + JSESSIONID +";"+"token=" + token);
            // 发送POST请求必须设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            //1.获取URLConnection对象对应的输出流  
            out = new PrintWriter(conn.getOutputStream());  
            //2.中文有乱码的需要将PrintWriter改为如下  
            //out=new OutputStreamWriter(conn.getOutputStream(),"UTF-8")  
            // 发送请求参数  
            out.print(param);  
            // flush输出流的缓冲  
            out.flush();  
            // 定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("发送 POST 请求出现异常！"+e);  
            e.printStackTrace();  
        }  
        //使用finally块来关闭输出流、输入流  
        finally{  
            try{  
                if(out!=null){  
                    out.close();  
                }  
                if(in!=null){  
                    in.close();  
                }  
            }  
            catch(IOException ex){  
                ex.printStackTrace();  
            }  
        }  
        System.out.println("post推送结果："+result);  
        return result;  
    }  

}
