package com.ayvengoza.pet.uiprototype;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Created by ang on 10.01.18.
 */

public class ToolGridFragment extends Fragment implements View.OnClickListener {
    private ToolGridAdapter mToolGridAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tool_grid, container, false);
        final GridView gridView = (GridView) rootView.findViewById(R.id.gridview);
        mToolGridAdapter = new ToolGridAdapter(getActivity());
        gridView.setAdapter(mToolGridAdapter);
        gridView.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {

    }
}
