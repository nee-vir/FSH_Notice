package com.example.fsh_notice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fsh_notice.entire_posts.EntirePosts;

import java.util.List;

public class CAAdapter extends RecyclerView.Adapter<CAAdapter.ViewHolder>{
    private Context context;
    private List<DepartmentPost> dPosts;

    public CAAdapter(Context context, List<DepartmentPost> dPosts) {
        this.context = context;
        this.dPosts = dPosts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dept_item_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.dpUserName.setText(dPosts.get(position).getUserName());
        holder.dpTitle.setText(dPosts.get(position).getPostTitle());
        holder.dpCourse.setText(dPosts.get(position).getDeptCourse());
        holder.dpYear.setText(dPosts.get(position).getCourseYear());
        holder.dpDateTime.setText(dPosts.get(position).getDateTime());
        holder.dpParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, EntirePosts.class);
                intent.putExtra("UniqueID",dPosts.get(position).getUniqueID());
                intent.putExtra("Category",dPosts.get(position).getCategory());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout dpParentLayout;
        TextView dpUserName,dpTitle,dpCourse,dpYear,dpDateTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dpParentLayout=itemView.findViewById(R.id.dp_parent_layout);
            dpUserName=itemView.findViewById(R.id.dp_username);
            dpTitle=itemView.findViewById(R.id.dp_title);
            dpCourse=itemView.findViewById(R.id.dp_course);
            dpYear=itemView.findViewById(R.id.dp_year);
            dpDateTime=itemView.findViewById(R.id.dp_date_time);

        }
    }
}
