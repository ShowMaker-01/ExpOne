package com.example.myapplication3;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.example.myapplication3.bean.DayWeatherBean;
import com.example.myapplication3.bean.WeatherBean;
import com.example.myapplication3.util.NetUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {

    private AppCompatSpinner mSpinner;
    private ArrayAdapter<String> mSpAdapter;
    private  String[] mCities;
    private TextView tv_place,tv_tem,tv_hum,tv_weather,update_time,cityID;

    private Button button,button_GZ,button_NoGZ;

    private TextView list;
    private  String store;




    private Handler mHandler=new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                String weather=(String)msg.obj;
                Log.d("fan","---主线程收到天气数据---"+weather);

                Gson gson=new Gson();
                WeatherBean weatherBean=gson.fromJson(weather, WeatherBean.class);
                Log.d("fan","---解析后的数据---"+weatherBean.toString());

                updateUiOfWeather(weatherBean);
            }
        }
    };

    //该方法将数据填充到界面上
    private void updateUiOfWeather(WeatherBean weatherBean) {
         if(weatherBean==null){
             return;
         }

         List<DayWeatherBean> dayWeathers=weatherBean.getDayWeatherBeans();
         DayWeatherBean todayWeather=dayWeathers.get(0);
         if (todayWeather==null) return;
         update_time.setText(getCurrentTimeFormat());
         tv_place.setText(weatherBean.getCity()+"市");
         tv_weather.setText("天气："+todayWeather.getWea());
         tv_tem.setText("温度："+todayWeather.getTem_day()+"°C");
         tv_hum.setText("湿度："+todayWeather.getTem_night()+"%");
         cityID.setText("ID："+weatherBean.getCityId());

    }

    String now;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        SharedPreferences sharedPreferences = getSharedPreferences("city", Context.MODE_PRIVATE);
        Map<String, ?> allData = sharedPreferences.getAll();
        for (String key : allData.keySet()) {
            Object value = allData.get(key);
            if (value instanceof String) {
                String stringValue = (String) value;
                if(stringValue!=null){
                    store=store+stringValue+" ";
                }
            }
        }

        list.setText(store.substring(4));
    }
    String total=" ";

    private void initView() {
        mSpinner=findViewById(R.id.sp_city);
        mCities=getResources().getStringArray(R.array.cities);
        mSpAdapter=new ArrayAdapter<>(this,R.layout.sp_item_layout,mCities);
        mSpinner.setAdapter(mSpAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    String selectedCity=mCities[position];
                    getWeatherOfCity(selectedCity);
            }//当选中一个城市时，调用该方法

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        tv_place=findViewById(R.id.tv_place);
        tv_tem=findViewById(R.id.tv_tem);
        tv_hum=findViewById(R.id.tv_hum);
        tv_weather=findViewById(R.id.tv_weather);
        update_time=findViewById(R.id.update_time);
        cityID=findViewById(R.id.tv_cityID);
        button=findViewById(R.id.tv_button);
        list=findViewById(R.id.list);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshWeatherData();
            }
        }
        );
        button_GZ=findViewById(R.id.tv_GZ);
        button_GZ.setOnClickListener(new View.OnClickListener() {
            //给关注按钮设置点击事件
            @Override
            public void onClick(View view) {
                saveGZ();
                if(!total.contains(getCityFromSharedPreferences())){
                    if(!store.contains(getCityFromSharedPreferences())){
                        total=total+getCityFromSharedPreferences()+"  ";
                        list.setText(store.substring(4)+total);
                    }else{
                        list.setText(store.substring(4));
                    }
                }else{
                    list.setText(store.substring(4)+total);
                }
            }
        });

        button_NoGZ=findViewById(R.id.tv_NoGZ);
        //给取消关注按钮设置点击事件
        button_NoGZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("city", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if(sharedPreferences.contains(cityID.getText().toString())){
                    editor.remove(cityID.getText().toString());
                    editor.commit();
                    Map<String, ?> allData = sharedPreferences.getAll();
                    for (String key : allData.keySet()) {
                        Object value = allData.get(key);
                        if (value instanceof String) {
                            String stringValue = (String) value;
                            if(stringValue!=null){
                                now=now+stringValue+" ";
                            }
                        }
                    }
                    list.setText(now.substring(4));
                }
            }
        });
    }

    //存储城市的方法
    private void saveGZ() {
        String SaveCity = tv_place.getText().toString();
        //获取sharedPreferences
        SharedPreferences sharedPreferences= getSharedPreferences("city", Context .MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if (!sharedPreferences.contains(cityID.getText().toString())){
            editor.putString(cityID.getText().toString(),SaveCity);
        }
        editor.commit();
    }
    private String getCityFromSharedPreferences() {
        String result="";
        SharedPreferences sharedPreferences=getSharedPreferences("city",MODE_PRIVATE);
         result=sharedPreferences.getString(cityID.getText().toString(), "");
         return result;
    }

    private void refreshWeatherData() {
        update_time.setText(getCurrentTimeFormat());
    }


    private void getWeatherOfCity(String selectedCity) {
        //开启子线程，请求网络
        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求网络
                try {
                    String weatherOfCity=NetUtil.getWeather(selectedCity);
                    Message message=Message.obtain();
                    message.what=0;
                    message.obj=weatherOfCity;
                    mHandler.sendMessage(message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                performSearch(s);
                return true;
            } //点击一个选项执行这项内容

            @Override
            public boolean onQueryTextChange(String s) {
                mCities=getResources().getStringArray(R.array.cities);
                return true;
            }//每输入一个数字，则使用它
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void performSearch(String s) {
        TextView searchID=findViewById(R.id.tv_cityID);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return super.onContextItemSelected(item);
    }

    //获取当前时间方法
    private String getCurrentTimeFormat(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY年MM月dd HH:mm:ss");
        Date date=new Date();
        return simpleDateFormat.format(date);
    }

}
