package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

    private int count = 0;
    Bundle grid_bundle;
    private String[] category = {"Choose","Rearrange","Listen","Connection"};

    public GridAdapter(int count, Bundle grid_bundle) {
        this.count = count;
        this.grid_bundle = grid_bundle;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final View view;
        if(convertView==null){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.set_item, parent, false);
        }
        else{
            view = convertView;
        }
        ((TextView)view.findViewById((R.id.myTextView))).setText(category[position]);
        view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                if(position==0){
                    Intent intent1 = new Intent(parent.getContext(), QuestionActivity.class);
                    //intent1.putExtra("bundle_level",grid_bundle);
                    intent1.putExtras(grid_bundle);
                    intent1.putExtra("scores",0);
                    view.getContext().startActivity(intent1);
                }
                if(position==1){
                    Intent intent2 = new Intent(parent.getContext(), QuestionActivity2.class);
                    //intent2.putExtra("bundle_level",grid_bundle);
                    intent2.putExtras(grid_bundle);
                    intent2.putExtra("scores",0);
                    view.getContext().startActivity(intent2);
                }
                if(position==2){
                    Intent intent3 = new Intent(parent.getContext(), QuestionActivity3.class);
                    //intent3.putExtra("bundle_level",grid_bundle);
                    intent3.putExtras(grid_bundle);
                    intent3.putExtra("scores",0);
                    view.getContext().startActivity(intent3);
                }
                if(position==3){
                    Intent intent4 = new Intent(parent.getContext(), QuestionActivity4.class);
                    //intent4.putExtra("bundle_level",grid_bundle);
                    intent4.putExtras(grid_bundle);
                    intent4.putExtra("scores",0);
                    view.getContext().startActivity(intent4);
                }
            }
        });
        return view;
    }
}
