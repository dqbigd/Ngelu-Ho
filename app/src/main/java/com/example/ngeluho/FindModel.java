package com.example.ngeluho;

import android.os.Parcel;
import android.os.Parcelable;

public class FindModel implements Parcelable {
    private int photo;
    private String title;
    private String describ;

    public FindModel(){

    }

    protected FindModel(Parcel in) {
        photo = in.readInt();
        title = in.readString();
        describ = in.readString();
    }

    public static final Creator<FindModel> CREATOR = new Creator<FindModel>() {
        @Override
        public FindModel createFromParcel(Parcel in) {
            return new FindModel(in);
        }

        @Override
        public FindModel[] newArray(int size) {
            return new FindModel[size];
        }
    };

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(photo);
        parcel.writeString(title);
        parcel.writeString(describ);
    }
}
