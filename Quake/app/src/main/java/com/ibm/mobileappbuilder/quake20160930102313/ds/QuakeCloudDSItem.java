
package com.ibm.mobileappbuilder.quake20160930102313.ds;
import ibmmobileappbuilder.ds.restds.GeoPoint;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class QuakeCloudDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("id") public String id;
    @SerializedName("loc") public GeoPoint loc;
    @SerializedName("mag") public Double mag;

    @Override
    public String getIdentifiableId() {
      return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeDoubleArray(loc != null  && loc.coordinates.length != 0 ? loc.coordinates : null);
        dest.writeValue(mag);
    }

    public static final Creator<QuakeCloudDSItem> CREATOR = new Creator<QuakeCloudDSItem>() {
        @Override
        public QuakeCloudDSItem createFromParcel(Parcel in) {
            QuakeCloudDSItem item = new QuakeCloudDSItem();

            item.id = in.readString();
            double[] loc_coords = in.createDoubleArray();
            if (loc_coords != null)
                item.loc = new GeoPoint(loc_coords);
            item.mag = (Double) in.readValue(null);
            return item;
        }

        @Override
        public QuakeCloudDSItem[] newArray(int size) {
            return new QuakeCloudDSItem[size];
        }
    };

}


