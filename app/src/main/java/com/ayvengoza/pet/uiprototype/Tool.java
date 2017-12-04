package com.ayvengoza.pet.uiprototype;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ang on 04.12.17.
 */

public class Tool implements Parcelable {
    private static final int DETAILS_COUNT = 3;

    private final String mName;
    private final String mPrice;
    private final String[] mDetails;
    private final String mDescription;

    public Tool(String name, String price, String[] details, String description) {
        mName = name;
        mPrice = price;
        mDetails = new String[DETAILS_COUNT];
        if(details != null){
            for(int i = 0; i < details.length && i < mDetails.length; i++){
                mDetails[i] = details[i];
            }
        }
        mDescription = description;
    }

    public String getName() {
        return mName;
    }

    public String getPrice() {
        return mPrice;
    }

    public String[] getDetails() {
        return mDetails;
    }

    public String getDescription() {
        return mDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeString(this.mPrice);
        dest.writeStringArray(this.mDetails);
        dest.writeString(this.mDescription);
    }

    protected Tool(Parcel in) {
        this.mName = in.readString();
        this.mPrice = in.readString();
        this.mDetails = in.createStringArray();
        this.mDescription = in.readString();
    }

    public static final Creator<Tool> CREATOR = new Creator<Tool>() {
        @Override
        public Tool createFromParcel(Parcel source) {
            return new Tool(source);
        }

        @Override
        public Tool[] newArray(int size) {
            return new Tool[size];
        }
    };
}
