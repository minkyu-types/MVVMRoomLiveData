package com.architecture.mvvmroomlivedata;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<MemoDto> items = new ArrayList<>();
    private Context mContext;
    private MemoDatabase db;

    public RecyclerAdapter(MemoDatabase db){
        this.db = db;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_maker, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<MemoDto> getItems(){return items;}



    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private TextView tvContents;
        private Button btnSave;
        private int index;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.textview_title);
            tvContents = itemView.findViewById(R.id.edittext_content);
            btnSave = itemView.findViewById(R.id.button_save);

            btnSave.setOnClickListener(view -> {
            });
        }

        public void onBind(MemoDto memoDto, int position){
            index = position;
            tvTitle.setText(memoDto.getTitle());
            tvContents.setText(memoDto.getContents());
        }

        public void editData(String contents){
            new Thread(()->{
                items.get(index).setContents(contents);
                db.memoDao().update(items.get(index));
            }).start();
            Toast.makeText(mContext, "저장완료!", Toast.LENGTH_SHORT).show();
        }
    }

    public void setItem(List<MemoDto> data){
        items = data;
        notifyDataSetChanged();
    }
}
