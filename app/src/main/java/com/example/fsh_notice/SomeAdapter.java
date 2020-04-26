package com.example.fsh_notice;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SomeAdapter extends RecyclerView.Adapter<SomeAdapter.ViewHolder>{
    private List<Upload> someUploads;
    private Context rContext;

    public SomeAdapter(List<Upload> someUploads, Context rContext) {
        this.someUploads = someUploads;
        this.rContext = rContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.some_list_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d("SomeAdapter", "onBindViewHolder: Started");
        holder.someTitle.setText(someUploads.get(position).getTitle());
        holder.someDept.setText(someUploads.get(position).getDepartmentNames());
        holder.somePublisher.setText(someUploads.get(position).getPublisherNames());
        holder.label1.setText("Posted by:");
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPos=position;
                Intent intent=new Intent(rContext,EntirePostActivity.class);
                intent.putExtra("iPos",itemPos);
                rContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return someUploads.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout parentLayout;
        TextView someTitle,someDept,somePublisher,label1;
        //View line;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout=itemView.findViewById(R.id.some_parent_layout);
            someTitle=itemView.findViewById(R.id.some_title);
            someDept=itemView.findViewById(R.id.some_dept);
            somePublisher=itemView.findViewById(R.id.some_publisher);
            //line=itemView.findViewById(R.id.line_d);
            label1=itemView.findViewById(R.id.published);
        }
    }
}
