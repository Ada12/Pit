package com.example.zeducation;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;


public class RetriveData {

	String GET_URL;
	String POST_URL;
	JSONObject jsonObject = new JSONObject();
	
	public String sendPOST(JSONObject jsonObject)throws IOException {
		URL postUrl = new URL(POST_URL); 
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection(); 
        connection.setDoOutput(true);                 
        connection.setDoInput(true);
        connection.setRequestMethod("POST"); 
        connection.setUseCaches(false); 
        connection.setInstanceFollowRedirects(true); 
        connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        connection.setRequestProperty("Content-Type", "application/json");
        DataOutputStream out = new DataOutputStream(connection.getOutputStream()); 
        //            jsonObject.put("email", "5@qq.com");
		//            jsonObject.put("username", "100");
		//            jsonObject.put("password", "111111");
		String content = jsonObject.toString();
		out.writeBytes(content); 
		out.flush(); 
	    out.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
		String line; 
		String result = "";
		System.out.println(" ============================= "); 
		System.out.println(" Contents of post request "); 
		System.out.println(" ============================= "); 
		while ((line = reader.readLine()) != null) { 
		     System.out.println(line); 
		     result = result + line;
		} 
		System.out.println(" ============================= ");
		System.out.println(" Contents of post request ends "); 
		System.out.println(" ============================= ");
		reader.close(); 
		return result;
	}
	
	public String sendGet() throws IOException {
        URL getUrl = new URL(GET_URL); 
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); 
        connection.setRequestProperty("Content-Type", "application/json");
        connection.connect(); 
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
        System.out.println(" ============================= "); 
        System.out.println(" Contents of get request "); 
        System.out.println(" ============================= "); 
        String lines; 
        String result = "";
        while ((lines = reader.readLine()) != null) { 
            System.out.println(lines);
            result = result + lines;
        } 
        reader.close();              
        connection.disconnect(); 
        System.out.println(" ============================= "); 
        System.out.println(" Contents of get request ends "); 
        System.out.println(" ============================= "); 
             return result;
        } 
	
	public String getGET_URL(){
		return GET_URL;
	}
	
	public void setGET_URL(String get){
		this.GET_URL = get;
	}
	
	public String getPOST_URL(){
		return POST_URL;
	}
	
	public void setPOST_URL(String set){
		this.POST_URL = set;
	}
}
