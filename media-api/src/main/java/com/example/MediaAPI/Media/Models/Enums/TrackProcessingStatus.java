package com.example.MediaAPI.Media.Models.Enums;

public enum TrackProcessingStatus {
    NOT_PROCESSED(Short.valueOf("0"), "not processed"),
    IN_QUEUE(Short.valueOf("1"), "in queue"),
    PROCESSING(Short.valueOf("2"), "processing"),
    PROCESSED(Short.valueOf("3"), "processed"),
    ERROR(Short.valueOf("4"), "error during processing");

    private Short value;
    private String description;

    TrackProcessingStatus(Short value, String description) {
        this.value = value;
        this.description = description;
    }

    public Short getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }
}
