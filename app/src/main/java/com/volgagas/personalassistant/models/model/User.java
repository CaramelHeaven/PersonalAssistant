package com.volgagas.personalassistant.models.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

import timber.log.Timber;

public class User implements Parcelable {
    private String name;
    private String position;
    private String codekey;
    private String lastEntered;
    private String category;
    private String userImage;

    private String userCliendId;
    private String dynamics365Token;
    private String sharePointToken;

    private String modifiedName;

    public void setBaseFields(User user) {
        Timber.d("setBase felds" + user.toString());
        this.name = user.getName();
        this.position = user.getPosition();
        this.codekey = user.getCodekey();
        this.lastEntered = user.getLastEntered();
        this.category = user.getCategory();
        this.userImage = user.getUserImage();

        String[] partsName = name.split(" ");
        this.modifiedName = partsName[1] + " " + partsName[0];
    }

    //Andrew Vasiliev
    public String getModifiedNormalName() {
        String[] partsName = name.split(" ");
        return partsName[1] + " " + partsName[0];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(codekey, user.codekey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, codekey);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", codekey='" + codekey + '\'' +
                ", lastEntered='" + lastEntered + '\'' +
                ", category='" + category + '\'' +
                ", userImage='" + userImage + '\'' +
                ", userCliendId='" + userCliendId + '\'' +
                ", dynamics365Token='" + dynamics365Token + '\'' +
                ", sharePointToken='" + sharePointToken + '\'' +
                '}';
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

    public String getCodekey() {
        return codekey;
    }

    public void setCodekey(String codekey) {
        this.codekey = codekey;
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

    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName;
    }

    public String getModifiedName() {
        return modifiedName;
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

    public void clear() {
        this.name = "";
        this.position = "";
        this.codekey = "";
        this.lastEntered = "";
        this.category = "";

        this.userCliendId = "";
        this.dynamics365Token = "";
        this.sharePointToken = "";

        this.modifiedName = "";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.position);
        dest.writeString(this.codekey);
        dest.writeString(this.lastEntered);
        dest.writeString(this.category);
        dest.writeString(this.userImage);
        dest.writeString(this.userCliendId);
        dest.writeString(this.dynamics365Token);
        dest.writeString(this.sharePointToken);
        dest.writeString(this.modifiedName);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.position = in.readString();
        this.codekey = in.readString();
        this.lastEntered = in.readString();
        this.category = in.readString();
        this.userImage = in.readString();
        this.userCliendId = in.readString();
        this.dynamics365Token = in.readString();
        this.sharePointToken = in.readString();
        this.modifiedName = in.readString();
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
