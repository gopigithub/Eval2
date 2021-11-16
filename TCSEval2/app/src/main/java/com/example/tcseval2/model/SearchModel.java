package com.example.tcseval2.model;

import com.google.gson.annotations.SerializedName;

public class SearchModel {

    public SearchModel(String artistName, String artistId, String primaryGenreName) {
        this.artistName = artistName;
        this.artistId = artistId;
        this.primaryGenreName = primaryGenreName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    @SerializedName("artistName")
    String artistName;
    @SerializedName("artistId")
    String artistId;
    @SerializedName("primaryGenreName")
    String primaryGenreName;
}
