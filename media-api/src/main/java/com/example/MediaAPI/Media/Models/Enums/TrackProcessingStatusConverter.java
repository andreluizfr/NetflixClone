package com.example.MediaAPI.Media.Models.Enums;

import javax.persistence.AttributeConverter;

public class TrackProcessingStatusConverter implements AttributeConverter<TrackProcessingStatus, Short> {

    @Override
    public Short convertToDatabaseColumn(final TrackProcessingStatus trackProcessingStatus) {

        if (trackProcessingStatus == null) {
            return null;
        }
        return trackProcessingStatus.getValue();
    }

    @Override
    public TrackProcessingStatus convertToEntityAttribute(final Short dbData) {
        if (dbData == 0) {
            return TrackProcessingStatus.NOT_PROCESSED;
        } else if (dbData == 1) {
            return TrackProcessingStatus.IN_QUEUE;
        } else if (dbData == 2) {
            return TrackProcessingStatus.PROCESSING;
        } else if (dbData == 3) {
            return TrackProcessingStatus.PROCESSED;
        } else if (dbData == 4) {
            return TrackProcessingStatus.ERROR;
        } else {
            return null;
        }
    }
}