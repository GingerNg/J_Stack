package com.ginger.study.utils;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * MessageSender
 */
public class MessageSender {

//发送JSON字符串 如果成功则返回成功标识。
public static String sendMessage(String urlPath, String Json) {
    String result = "";
    BufferedReader reader = null;
    try {
        URL url = new URL(urlPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "UTF-8");
        // 设置文件类型:
        conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
        // 设置接收类型否则返回415错误
        //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
        conn.setRequestProperty("accept","application/json");
        // 往服务器里面发送数据
        if (Json != null ) {
            byte[] writebytes = Json.getBytes();
            // 设置文件长度
            conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
            OutputStream outwritestream = conn.getOutputStream();
            outwritestream.write(Json.getBytes());
            outwritestream.flush();
            outwritestream.close();
        }
        if (conn.getResponseCode() == 200) {
            reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            result = getResult(reader);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return result;
}

private static String getResult(BufferedReader reader){
    StringBuffer fileData = new StringBuffer(1000);
    char[] buf = new char[1024];
    int numRead=0;
    try{
        while((numRead=reader.read(buf)) != -1){
            fileData.append(buf, 0, numRead);
        }
    }catch (Exception e){
        e.printStackTrace();
    }
    return fileData.toString();

}

    public static void main(String[] args){
        if (args.length <3){
            System.exit(0);
        }
        JSONObject obj = new JSONObject();
        obj.put("deliveryKey",args[1]);
        obj.put("status",args[2]);
        MessageSender.sendMessage(args[0],obj.toJSONString());
    }


}

