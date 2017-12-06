package com.ayvengoza.pet.uiprototype;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ang on 06.12.17.
 */

public class ToolArrayAdapter extends ArrayAdapter<Tool> {
    private final LayoutInflater mLayoutInflater;

    public ToolArrayAdapter(Context context, List<Tool> objects){
        super(context, -1, objects);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.list_item_tool, parent, false);
        }
        final Tool tool = getItem(position);

        TextView textView = (TextView) convertView.findViewById(R.id.price);
        textView.setText(tool.getPrice());
        textView = (TextView) convertView.findViewById(R.id.name);
        textView.setText(tool.getName());
        textView = (TextView) convertView.findViewById(R.id.meta);
        textView.setText(tool.getDetails()[0]);

        convertView.findViewById(R.id.thumbnail).setBackgroundColor(getThumbnailColor(tool.getName().hashCode()));
        return convertView;
    }

    private int getThumbnailColor(int key){
        switch(Math.abs(key)%3){
            case 0:
                return 0xff777777;
            case 1:
                return 0xff999999;
            case 2:
                return 0xffbbbbbb;
        }
        return 0;
    }
}
