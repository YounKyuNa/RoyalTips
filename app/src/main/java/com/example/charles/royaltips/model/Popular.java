package com.example.charles.royaltips.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by charles on 2017. 10. 23..
 * Popular
 */

public class Popular implements Parcelable {

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_image")
    @Expose
    private String nameImage;
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("type")
    @Expose
    private String type;

    public final static Parcelable.Creator<Popular> CREATOR = new Creator<Popular>() {
        @Override
        public Popular createFromParcel(Parcel in) {
            Popular instance = new Popular();
            instance.image = (String)in.readValue(String.class.getClassLoader());
            instance.name = (String)in.readValue(String.class.getClassLoader());
            instance.nameImage = (String)in.readValue(String.class.getClassLoader());
            instance.count = (int)in.readValue(int.class.getClassLoader());
            instance.type = (String)in.readValue(String.class.getClassLoader());
            return instance;
        }

        @Override
        public Popular[] newArray(int size) {
            return new Popular[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(image);
        dest.writeValue(name);
        dest.writeValue(nameImage);
        dest.writeValue(count);
        dest.writeValue(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //
    // Getter / Setter
    //

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
