package pl.chiqvito.edmunds.bus.events;

import pl.chiqvito.edmunds.Constants;
import pl.chiqvito.edmunds.sdk.dto.media.response.PhotosDTO;

public class PhotosEvent extends Event {
    private PhotosDTO photos;

    public PhotosEvent(PhotosDTO photos) {
        this.photos = photos;
    }

    @Override
    public String key() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(Constants.DELIMITER);
        sb.append(getPhotos().getPhotosCount());
        return sb.toString();
    }

    public PhotosDTO getPhotos() {
        return photos;
    }
}
