package com.example.myapplication3.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetUtil {

    public static  final String URL_WEATHER="https://www.yiketianqi.com/free/week?unescape=1&appid=33587241&appsecret=u4Aj74w4";

    public static String doGet(String urlStr) throws IOException {
        String result="";
        InputStreamReader inputStreamReader=null;
        HttpURLConnection connection=null;
        BufferedReader bufferedReader=null;
        try {
            URL url=new URL(urlStr);
            connection=(HttpURLConnection) url.openConnection();//打开连接
            connection.setRequestMethod("GET");//设置HTTP请求方法为GET
            connection.setConnectTimeout(5000);//设置连接超时时间为5秒
            //从连接中读取数据
            InputStream inputStream=connection.getInputStream();//通过连接对象获取输入流，用于读取响应数据。
             inputStreamReader=new InputStreamReader(inputStream);//将输入流包装成字符流

             bufferedReader=new BufferedReader(inputStreamReader);//二进制流送入缓冲区
            StringBuilder stringBuilder=new StringBuilder();
            String line="";
            while ((line=bufferedReader.readLine())!=null){
                        stringBuilder.append(line);
            }
            result=stringBuilder.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(connection!=null){
                connection.disconnect();//关闭连接
            }
            if(inputStreamReader!=null){
                inputStreamReader.close();//释放输入流资源
            }
            if (bufferedReader!=null){
                bufferedReader.close();//释放缓冲区的资源
            }
        }
        return result;
    }

    public  static String getWeather(String city) throws IOException {
        String result="";
        //拼接出天气的url

        String weatherUrl=URL_WEATHER+"&city="+city;
        Log.d("fan","----weatherUrl----"+weatherUrl);
        result=doGet(weatherUrl);
        Log.d("fan","----result----"+result);
        return result;
    }
}
