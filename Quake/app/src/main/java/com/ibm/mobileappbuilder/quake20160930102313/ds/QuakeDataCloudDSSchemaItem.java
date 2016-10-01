
package com.ibm.mobileappbuilder.quake20160930102313.ds;

import ibmmobileappbuilder.mvp.model.MutableIdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class QuakeDataCloudDSSchemaItem implements Parcelable, MutableIdentifiableBean {

    private transient String cloudantIdentifiableId;

    @SerializedName("lat") public Double lat;
    @SerializedName("lng") public Double lng;
    @SerializedName("value") public Double value;

    @Override
    public void setIdentifiableId(String id) {
        this.cloudantIdentifiableId = id;
    }

    @Override
    public String getIdentifiableId() {
        return cloudantIdentifiableId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cloudantIdentifiableId);
        dest.writeValue(lat);
        dest.writeValue(lng);
        dest.writeValue(value);
    }

    public static final Creator<QuakeDataCloudDSSchemaItem> CREATOR = new Creator<QuakeDataCloudDSSchemaItem>() {
        @Override
        public QuakeDataCloudDSSchemaItem createFromParcel(Parcel in) {
            QuakeDataCloudDSSchemaItem item = new QuakeDataCloudDSSchemaItem();
            item.cloudantIdentifiableId = in.readString();

            item.lat = (Double) in.readValue(null);
            item.lng = (Double) in.readValue(null);
            item.value = (Double) in.readValue(null);
            return item;
        }

        @Override
        public QuakeDataCloudDSSchemaItem[] newArray(int size) {
            return new QuakeDataCloudDSSchemaItem[size];
        }
    };
}


