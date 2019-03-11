package com.volgagas.personalassistant.models.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class User implements Parcelable {
    private String name;
    private String position;
    private List<String> codekeyList;
    private String lastEntered;
    private String category;
    private String userImage;

    private String personalDynamics365Number; // used for get information from D365
    private String workerRecId; // field for nomenclatures

    private String userCliendId;
    private String dynamics365Token;
    private String sharePointToken;

    public void setBaseFields(User user) {
        this.name = user.getName();
        this.position = user.getPosition();
        this.codekeyList = user.getCodekeyList();
        this.lastEntered = user.getLastEntered();
        this.category = user.getCategory();
        this.userImage = user.getUserImage();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", codekeyList=" + codekeyList +
                ", lastEntered='" + lastEntered + '\'' +
                ", category='" + category + '\'' +
                ", userImage='" + userImage + '\'' +
                ", personalDynamics365Number='" + personalDynamics365Number + '\'' +
                ", workerRecId='" + workerRecId + '\'' +
                ", userCliendId='" + userCliendId + '\'' +
                ", dynamics365Token='" + dynamics365Token + '\'' +
                ", sharePointToken='" + sharePointToken + '\'' +
                '}';
    }

    //Andrew Vasiliev
    public String getModifiedNormalName() {
        String[] partsName = name.split(" ");
        return partsName[1] + " " + partsName[0];
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<String> getCodekeyList() {
        return codekeyList;
    }

    public void setCodekeyList(List<String> codekeyList) {
        this.codekeyList = codekeyList;
    }

    public String getLastEntered() {
        return lastEntered;
    }

    public void setLastEntered(String lastEntered) {
        this.lastEntered = lastEntered;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserCliendId() {
        return userCliendId;
    }

    public void setUserCliendId(String userCliendId) {
        this.userCliendId = userCliendId;
    }

    public String getDynamics365Token() {
        return dynamics365Token;
    }

    public void setDynamics365Token(String dynamics365Token) {
        this.dynamics365Token = dynamics365Token;
    }

    public String getSharePointToken() {
        return sharePointToken;
    }

    public void setSharePointToken(String sharePointToken) {
        this.sharePointToken = sharePointToken;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getPersonalDynamics365Number() {
        return personalDynamics365Number;
    }

    public void setPersonalDynamics365Number(String personalDynamics365Number) {
        this.personalDynamics365Number = personalDynamics365Number;
    }

    public String getWorkerRecId() {
        return workerRecId;
    }

    public void setWorkerRecId(String workerRecId) {
        this.workerRecId = workerRecId;
    }

    public void clear() {
        this.name = "";
        this.position = "";
        this.codekeyList = new ArrayList<>();
        this.lastEntered = "";
        this.category = "";

        this.userCliendId = "";
        this.dynamics365Token = "";
        this.sharePointToken = "";
        this.workerRecId = "";

        this.personalDynamics365Number = "";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.position);
        dest.writeStringList(this.codekeyList);
        dest.writeString(this.lastEntered);
        dest.writeString(this.category);
        dest.writeString(this.userImage);
        dest.writeString(this.personalDynamics365Number);
        dest.writeString(this.workerRecId);
        dest.writeString(this.userCliendId);
        dest.writeString(this.dynamics365Token);
        dest.writeString(this.sharePointToken);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.position = in.readString();
        this.codekeyList = in.createStringArrayList();
        this.lastEntered = in.readString();
        this.category = in.readString();
        this.userImage = in.readString();
        this.personalDynamics365Number = in.readString();
        this.workerRecId = in.readString();
        this.userCliendId = in.readString();
        this.dynamics365Token = in.readString();
        this.sharePointToken = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
