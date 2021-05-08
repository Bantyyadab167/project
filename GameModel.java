package com.example.bet;

import android.os.Parcel;
import android.os.Parcelable;

public class GameModel implements Parcelable {
    String banner,gameid;

    public GameModel()
    {

    }

    public GameModel(String banner, String gameid) {
        this.banner = banner;
        this.gameid = gameid;
    }

    protected GameModel(Parcel in) {
        banner = in.readString();
        gameid = in.readString();
    }

    public static final Creator<GameModel> CREATOR = new Creator<GameModel>() {
        @Override
        public GameModel createFromParcel(Parcel in) {
            return new GameModel(in);
        }

        @Override
        public GameModel[] newArray(int size) {
            return new GameModel[size];
        }
    };
    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(banner);
        dest.writeString(gameid);
    }
}
