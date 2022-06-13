package com.api.QuickResponse.Model.YoutubeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoObject {
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("average_rating")
    @Expose
    private Object averageRating;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("channel_id")
    @Expose
    private String channelId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("is_live_content")
    @Expose
    private String isLiveContent;
    @SerializedName("keywords")
    @Expose
    private List<String> keywords = null;
    @SerializedName("number_of_views")
    @Expose
    private String numberOfViews;
    @SerializedName("published_time")
    @Expose
    private String publishedTime;
    @SerializedName("status")
    @Expose
    private VideoObjectStatus status;
    @SerializedName("thumbnails")
    @Expose
    private List<VideoObjectThumbnail> thumbnails = null;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("video_id")
    @Expose
    private String videoId;
    @SerializedName("video_length")
    @Expose
    private String videoLength;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Object getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Object averageRating) {
        this.averageRating = averageRating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsLiveContent() {
        return isLiveContent;
    }

    public void setIsLiveContent(String isLiveContent) {
        this.isLiveContent = isLiveContent;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getNumberOfViews() {
        return numberOfViews;
    }

    public void setNumberOfViews(String numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(String publishedTime) {
        this.publishedTime = publishedTime;
    }

    public VideoObjectStatus getStatus() {
        return status;
    }

    public void setStatus(VideoObjectStatus status) {
        this.status = status;
    }

    public List<VideoObjectThumbnail> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<VideoObjectThumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(String videoLength) {
        this.videoLength = videoLength;
    }
}
