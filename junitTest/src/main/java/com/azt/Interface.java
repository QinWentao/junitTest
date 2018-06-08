package com.azt;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class Interface {
	static final String JSESSIONID = "970F96AB8F3A019CC08B14643B93B010";
	static final String token = "964f10ce-f14d-4432-97b1-cb1612f11e72";
	public void firstAudit(String orderID) {
		System.out.println("��ʼһ������");
		//�ӿڵ�ַ
		String path = "http://192.168.0.186:6008/saferycom/auditsys_firstAudit.do";
		//����
        String parm = "id=0&doc_id=" + orderID + "&audit_state=1&reason=&revoked=0&achieve=1&bug_type=&auditHisIds=&hasProblem=0&audit_flag=0&iscodekey=false&ajaxFlag=true";
		try {
			Interface obj = new Interface();			
			String response = new String(obj.postRequest(path, parm).getBytes("gbk"),"utf-8");
			System.out.println("Respond result:" + response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
	}
	
	public String postRequest(String path,String parm) {
		PrintWriter dataout = null;
		BufferedReader datain = null;
		String response = "";
		System.out.println("Start to Request������");
		try {
			URL realUrl = new URL(path);  
            // �򿪺�URL֮�������  
            URLConnection connection = realUrl.openConnection();
			//�������������Ϊtrue,Ĭ��false (post �����������ķ�ʽ��ʽ�Ĵ��ݲ���)
			connection.setDoOutput(true); 
			// ��������������Ϊtrue  
	        connection.setDoInput(true); 
	        connection.setUseCaches(false);
	        // ��������ʽΪpost
	        //connection.setRequestMethod("POST");
	        // ���ø�HttpURLConnectionʵ���Ƿ��Զ�ִ���ض���
	        //connection.setInstanceFollowRedirects(true);
	        //����Cookie
	        connection.setRequestProperty("Cookie", "JSESSIONID=" + JSESSIONID +";"+"token=" + token);
	        connection.setRequestProperty("accept", "*/*");
	        connection.setRequestProperty("connection", "Keep-Alive");
	        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        connection.connect();
	        // ��ȡ������Ӧͷ�ֶ�  
//	        Map<String, List<String>> map = connection.getHeaderFields();  
//	        // �������е���Ӧͷ�ֶ�  
//	        for (String key : map.keySet()) {  
//	            System.out.println(key + "--->" + map.get(key));  
//	        }  
	        //���������
	        dataout = new PrintWriter(connection.getOutputStream());
	        //dataout=new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
	        //�����������
	        dataout.print(parm);
	        //flush������Ļ���  
	        dataout.flush();
	        datain= new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			//ѭ����ȡ��
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
	public static String sendPost(String url, String param) {  
        PrintWriter out = null;  
        BufferedReader in = null;  
        String result = "";  
        try {  
            URL realUrl = new URL(url);  
            // �򿪺�URL֮�������  
            URLConnection conn = realUrl.openConnection();  
            // ����ͨ�õ���������  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Cookie", "JSESSIONID=" + JSESSIONID +";"+"token=" + token);
            // ����POST�������������������  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            //1.��ȡURLConnection�����Ӧ�������  
            out = new PrintWriter(conn.getOutputStream());  
            //2.�������������Ҫ��PrintWriter��Ϊ����  
            //out=new OutputStreamWriter(conn.getOutputStream(),"UTF-8")  
            // �����������  
            out.print(param);  
            // flush������Ļ���  
            out.flush();  
            // ����BufferedReader����������ȡURL����Ӧ  
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("���� POST ��������쳣��"+e);  
            e.printStackTrace();  
        }  
        //ʹ��finally�����ر��������������  
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
        System.out.println("post���ͽ����"+result);  
        return result;  
    }  

}
