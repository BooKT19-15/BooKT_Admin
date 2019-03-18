package com.bookt.bookt_admin;

import android.os.Parcel;
import android.os.Parcelable;

public class Table implements Parcelable {
    String seatCount;
    String tableCount;

    public Table() {
    }

    public Table(String seatCount, String tableCount) {
        this.seatCount = seatCount;
        this.tableCount = tableCount;
    }

    protected Table(Parcel in) {
        seatCount = in.readString();
        tableCount = in.readString();
    }

    public static final Creator<Table> CREATOR = new Creator<Table>() {
        @Override
        public Table createFromParcel(Parcel in) {
            return new Table(in);
        }

        @Override
        public Table[] newArray(int size) {
            return new Table[size];
        }
    };

    public String getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(String seatCount) {
        this.seatCount = seatCount;
    }

    public String getTableCount() {
        return tableCount;
    }

    public void setTableCount(String tableCount) {
        this.tableCount = tableCount;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(seatCount);
        dest.writeString(tableCount);
    }
}
