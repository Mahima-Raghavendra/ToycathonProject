package com.example.quiz;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.Viewholder> {

    List<LevelModel> LevelModelList;

    public LevelAdapter(List<LevelModel> levelModelList) {
        LevelModelList = levelModelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.level_item, parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.setData(LevelModelList.get(position).getName(), position);
    }

    @Override
    public int getItemCount() {
        return LevelModelList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{

        private TextView title;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.level_tile);
        }

        private void setData(final String tit, final int pos)
        {
            title.setText(tit);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), ShlokaActivity.class);
                    intent.putExtra("title", tit);
                    intent.putExtra("level",pos);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
