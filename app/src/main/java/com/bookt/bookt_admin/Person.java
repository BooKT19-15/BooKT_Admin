package com.bookt.bookt_admin;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
    String email;
    String first_name;
    String last_name;
    String mobile_number;

    public Person() {
    }

    public Person(String email, String first_name, String last_name, String mobile_number) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mobile_number = mobile_number;
    }

    protected Person(Parcel in) {
        email = in.readString();
        first_name = in.readString();
        last_name = in.readString();
        mobile_number = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(mobile_number);
    }
}
