package com.ayvengoza.pet.uiprototype;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by ayven on 03.12.2017.
 */

public enum ToolType {
    CLAMPS(R.string.clamps, R.string.clamps_description, R.drawable.hero_image_clamps),
    SAWS(R.string.saws, R.string.saws_description, R.drawable.hero_image_saws),
    DRILLS(R.string.drills, R.string.drills_description, R.drawable.hero_image_drills),
    SANDERS(R.string.sanders, R.string.sanders_description, R.drawable.hero_image_sanders),
    ROUTERS(R.string.routers, R.string.routers_description, R.drawable.hero_image_routers),
    MORE(R.string.more, R.string.more_description, R.drawable.hero_image_more);

    private final int mToolNameResourceId;
    private final int mToolDescriptionResourceId;
    private final int mToolImageResourceId;

    private ToolType(@StringRes int toolName, @StringRes int toolDescription,
    @DrawableRes int toolImage){
        mToolNameResourceId = toolName;
        mToolDescriptionResourceId = toolDescription;
        mToolImageResourceId = toolImage;
    }

    @StringRes
    public int getToolNameResourceId(){
        return mToolNameResourceId;
    }

    @StringRes
    public int getToolDescriptionResourceId(){
        return mToolDescriptionResourceId;
    }

    @DrawableRes
    public int getToolImageResourceId(){
        return mToolImageResourceId;
    }
}
