package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by CaramelHeaven on 12:29, 18/02/2019.
 */
public class ApkResponse {
    @SerializedName("value")
    @Expose
    private List<ApkNetwork> value = null;

    public List<ApkNetwork> getValue() {
        return value;
    }

    public class ApkNetwork {
        @SerializedName("Length")
        @Expose
        private String length;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("ServerRelativeUrl")
        @Expose
        private String serverRelativeUrl;
        @SerializedName("TimeCreated")
        @Expose
        private String timeCreated;
        @SerializedName("TimeLastModified")
        @Expose
        private String timeLastModified;
        @SerializedName("Title")
        @Expose
        private Object title;
        @SerializedName("UIVersion")
        @Expose
        private Integer uIVersion;
        @SerializedName("UIVersionLabel")
        @Expose
        private String uIVersionLabel;

        public String getLength() {
            return length;
        }

        public String getName() {
            return name;
        }

        public String getServerRelativeUrl() {
            return serverRelativeUrl;
        }

        public String getTimeCreated() {
            return timeCreated;
        }

        public String getTimeLastModified() {
            return timeLastModified;
        }

        public Object getTitle() {
            return title;
        }

        public Integer getuIVersion() {
            return uIVersion;
        }

        public String getuIVersionLabel() {
            return uIVersionLabel;
        }
    }
}
