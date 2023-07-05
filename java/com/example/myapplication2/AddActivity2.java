package com.example.myapplication2;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.bean.Note;
import com.example.myapplication2.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.SharedPreferences;

public class AddActivity2 extends AppCompatActivity {
    //EditText类允许用户进行输入
    private EditText etTitle, etContent, etAuthor;
    private NoteDbOpenHelper mNoteDbOpenHelper;
    boolean data;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);

        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        etAuthor = findViewById(R.id.et_author);
        mNoteDbOpenHelper=new NoteDbOpenHelper(this);
        String authorFromSharedPreferences = getAuthorFromSharedPreferences();
        if(authorFromSharedPreferences.equals("")&&data==false){
            etAuthor.setText("苏云昊");
        }
    }



//这里的this，也就是context也就是指当前界面（Activity）
    public void add(View view) {
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();
        String author = etAuthor.getText().toString();
        if (TextUtils.isEmpty(title)) {
            ToastUtil.toastShort(this, "请输入标题！");
            return;
        }

        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setCreateTime(getCurrentTimeFormat());
        saveAuthorToSharedPreferences(author);
        if(!author.equals("")){
            note.setAuthor(author);
            data=true;
        }else{
            note.setAuthor("苏云昊");
        }
        long row=mNoteDbOpenHelper.insertData(note);
        if (row!=-1){
            ToastUtil.toastShort(this,"添加成功");
            this.finish();//自动关闭当前页面
        }else{
            ToastUtil.toastShort(this,"添加失败");
        }
    }
    private String getCurrentTimeFormat(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY年MM月dd HH:mm:ss");
        Date date=new Date();
        return simpleDateFormat.format(date);
    }


    private void saveAuthorToSharedPreferences(String author) {
        SharedPreferences AuthorNames=getSharedPreferences("AuthorNames",MODE_PRIVATE);
        SharedPreferences.Editor edit=AuthorNames.edit();
        edit.putString("author",author);
        edit.commit();
    }


    private String getAuthorFromSharedPreferences() {
        SharedPreferences AuthorNames=getSharedPreferences("AuthorNames",MODE_PRIVATE);
        return AuthorNames.getString("author", "苏云昊");
    }
}
