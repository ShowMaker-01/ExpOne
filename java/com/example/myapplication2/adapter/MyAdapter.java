package com.example.myapplication2.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.EditActivity2;
import com.example.myapplication2.NoteDbOpenHelper;
import com.example.myapplication2.R;
import com.example.myapplication2.bean.Note;
import com.example.myapplication2.util.ToastUtil;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Note> mBeanList;
    private LayoutInflater mLayoutInflater;//视图类对象
    private Context mContext;
    private NoteDbOpenHelper mNoteDbOpenHelper;

    public MyAdapter(Context context,List<Note> mBeanList){
        this.mContext=context;
        this.mBeanList=mBeanList;
        mLayoutInflater=LayoutInflater.from(mContext);//通过Context类对象来实例化LayoutInflater对象
        mNoteDbOpenHelper=new NoteDbOpenHelper(mContext);

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // 使用布局填充器（LayoutInflater）将布局文件 R.layout.list_item_layout 填充为一个 View 对象
        View view=mLayoutInflater.inflate(R.layout.list_item_layout,parent,false);

        // 创建自定义的 ViewHolder 对象，并将填充后的 View 传递给它
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    //绑定数据到指定位置的列表项视图
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note=mBeanList.get(position);
        holder.mTvTitle.setText(note.getTitle());
        holder.mTvContent.setText(note.getContent());
        holder.mTvTime.setText(note.getCreateTime());
        holder.mTvAuthor.setText(note.getAuthor());
        //通过将监听器设置给r1Container，当用户点击r1Container触发下面事件
        holder.rlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //将intent传递给Android应用程序的上下文对象mContext
                Intent intent=new Intent(mContext, EditActivity2.class);
                intent.putExtra("note",note);//将note数据添加到intent中
                mContext.startActivity(intent);//通过mContext，启动EditActivity2活动
                        
            }
        });
        holder.rlContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //长按弹出、删除或者编辑
                Dialog dialog=new Dialog(mContext);//显示对话框
                View view1=mLayoutInflater.inflate(R.layout.list_item_dialog_layout,null);
                TextView tvDelete=view1.findViewById(R.id.tv_delete);
                tvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int row=mNoteDbOpenHelper.deleteFromDbById(note.getId());
                        if (row>0){
                            removeData(holder.getAdapterPosition());
                            ToastUtil.toastShort(mContext,"删除成功");
                        }else{
                            ToastUtil.toastShort(mContext,"删除失败");
                        }
                        dialog.dismiss();
                    }
                });

                dialog.setContentView(view1);
                dialog.show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

    public void refreshData(List<Note> notes){
        this.mBeanList=notes;
        notifyDataSetChanged();
    }

    public void removeData(int pos) {
        mBeanList.remove(pos);
        notifyItemRemoved(pos);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        //添加子视图应用，TextView类用于显示文本内容
        TextView mTvTitle;
        TextView mTvContent;
        TextView mTvTime;

        TextView mTvAuthor;

        ViewGroup rlContainer;
        //对子视图进行初始化
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mTvTitle = itemView.findViewById(R.id.tv_title);
            this.mTvContent = itemView.findViewById(R.id.tv_content);
            this.mTvTime = itemView.findViewById(R.id.tv_time);
            this.mTvAuthor=itemView.findViewById(R.id.tv_author);
            this.rlContainer = itemView.findViewById(R.id.rl_item_container);
        }

    }
}
