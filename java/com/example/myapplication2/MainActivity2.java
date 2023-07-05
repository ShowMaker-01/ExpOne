package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication2.adapter.MyAdapter;
import com.example.myapplication2.bean.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView mRecyclerView; //RecyclerView类用于显示滚动的列表
    private FloatingActionButton mBtnAdd;
    private List<Note> mNote;
    private MyAdapter myAdapter;
    private NoteDbOpenHelper mNoteDbOpenHelper;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    //后台回到前台，也就是主界面时被调用
    @Override
    protected void onResume() {
        super.onResume();
        refreshDataFromDb();
    }

    private void refreshDataFromDb() {
        mNote=getDataFromDB();
        myAdapter.refreshData(mNote);
    }

    //先将数据放入适配器，通过适配器，图表与适配器再连接
    private void initEvent() {
        myAdapter=new MyAdapter(this,mNote);
        mRecyclerView.setAdapter(myAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);//设置线性布局
    }

    private void initData() {
        mNote=new ArrayList<>();
        mNoteDbOpenHelper=new NoteDbOpenHelper(this);
    }

    private List<Note> getDataFromDB() {
       return mNoteDbOpenHelper.queryAllFromDb();
    }

    //SimpleDateFormat类对象用于对日期data进行指定格式化
    private String getCurrentTimeFormat(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY年MM月dd HH:mm:ss");
        Date date=new Date();
        return simpleDateFormat.format(date);
    }

    private void initView() {
            mRecyclerView=findViewById(R.id.rlv); //实例化列表
    }

    //从主界面跳转到添加界面
    public void add(View view) {
        Intent intent=new Intent(this,AddActivity2.class);
        startActivity(intent);
    }




}
