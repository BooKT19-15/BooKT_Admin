package com.bookt.bookt_admin;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuItem implements Parcelable {
    String description;
    String image;
    String name;
    String price;

    public MenuItem() {
    }

    public MenuItem(String description, String image, String name, String price) {
        this.description = description;
        this.image = image;
        this.name = name;
        this.price = price;
    }

    protected MenuItem(Parcel in) {
        description = in.readString();
        image = in.readString();
        name = in.readString();
        price = in.readString();
    }

    public static final Creator<MenuItem> CREATOR = new Creator<MenuItem>() {
        @Override
        public MenuItem createFromParcel(Parcel in) {
            return new MenuItem(in);
        }

        @Override
        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(image);
        dest.writeString(name);
        dest.writeString(price);
    }
}
