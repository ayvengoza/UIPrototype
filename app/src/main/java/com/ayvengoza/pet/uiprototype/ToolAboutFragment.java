package com.ayvengoza.pet.uiprototype;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ToolAboutFragment extends Fragment {
    private static final String ARG_TOOL_TYPE = "toolType";

    private ToolType mToolType;

    public static ToolAboutFragment newInstance(ToolType toolType) {
        ToolAboutFragment fragment = new ToolAboutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TOOL_TYPE, toolType.name());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mToolType = ToolType.valueOf(args.getString(ARG_TOOL_TYPE));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tool_about, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.title);
        textView.setText(mToolType.getToolNameResourceId());
        textView = (TextView) rootView.findViewById(R.id.description);
        textView.setText(mToolType.getToolDescriptionResourceId());
        return rootView;
    }
}
