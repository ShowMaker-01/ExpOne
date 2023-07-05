package com.example.myapplication2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication2.bean.Note;

import java.util.ArrayList;
import java.util.List;

//对数据库进行创建
public class NoteDbOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "noteSQLite.db";//数据库名字
    private static final String TABLE_NAME_NOTE = "note";//数据库表名
    private static final String CREATE_TABLE_SQL = "create table "  + TABLE_NAME_NOTE + " (id integer primary key autoincrement, title text, content text, create_time text,author text)";

    //调用父类构造函数，创建一个数据库
    public NoteDbOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }
    @Override
    //创建表方法
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //想数据库中插入数据
    public long insertData(Note note){
        SQLiteDatabase db=getWritableDatabase();//创建一个数据库对象
        ContentValues values=new ContentValues();//values存储要插入的数据
        values.put("title",note.getTitle());
        values.put("content",note.getContent());
        values.put("create_time",note.getCreateTime());
        values.put("author",note.getAuthor());
        return db.insert(TABLE_NAME_NOTE,null,values);
    }
    public int updateData(Note note) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", note.getTitle());
        values.put("content", note.getContent());
        values.put("create_time", note.getCreateTime());
        values.put("author",note.getAuthor());
        return db.update(TABLE_NAME_NOTE, values, "id like ?", new String[]{note.getId()});
    }

    public int deleteFromDbById(String id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME_NOTE, "id like ?", new String[]{id});
    }

    public List<Note> queryAllFromDb() {
        SQLiteDatabase db = getWritableDatabase();
        List<Note> noteList = new ArrayList<>();
        //cursor用于访问数据库查询结果
        Cursor cursor = db.query(TABLE_NAME_NOTE, null, null, null, null, null, "create_time DESC");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
                String createTime = cursor.getString(cursor.getColumnIndexOrThrow("create_time"));
                String author=cursor.getString(cursor.getColumnIndexOrThrow("author"));

                Note note = new Note();
                note.setId(id);
                note.setTitle(title);
                note.setContent(content);
                note.setCreateTime(createTime);
                note.setAuthor(author);

                noteList.add(note);
            }
            cursor.close();
        }
        return noteList;

    }
}
