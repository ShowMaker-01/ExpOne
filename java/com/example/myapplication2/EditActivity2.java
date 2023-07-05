package com.example.myapplication2;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.bean.Note;
import com.example.myapplication2.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity2 extends AppCompatActivity{
    private Note note;
    private EditText etTitle, etContent, etAuthor;

    private NoteDbOpenHelper mNoteDbOpenHelper;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit2);
        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        etAuthor = findViewById(R.id.et_author);
        initData();

    }

    private void initData() {
        Intent intent=getIntent();
        note=(Note) intent.getSerializableExtra("note");//获取额外数据
        if(note!=null){
            etTitle.setText(note.getTitle());
            etContent.setText(note.getContent());
            etAuthor.setText(note.getAuthor());
        }
        mNoteDbOpenHelper=new NoteDbOpenHelper(this);
    }

    public void save(View view) {
        String title=etTitle.getText().toString();
        String content=etContent.getText().toString();
        String author=etAuthor.getText().toString();
        if (TextUtils.isEmpty(title)) {
            ToastUtil.toastShort(this, "请输入标题！");
            return;
        }
        note.setTitle(title);
        note.setContent(content);
        note.setCreateTime(getCurrentTimeFormat());
        note.setAuthor(author);
        long rowId=mNoteDbOpenHelper.updateData(note);
        if (rowId!=-1){
            ToastUtil.toastShort(this,"修改成功");
            this.finish();//自动关闭当前页面
        }else{
            ToastUtil.toastShort(this,"修改失败");
        }
    }

    private String getCurrentTimeFormat(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY年MM月dd HH:mm:ss");
        Date date=new Date();
        return simpleDateFormat.format(date);
    }


}
