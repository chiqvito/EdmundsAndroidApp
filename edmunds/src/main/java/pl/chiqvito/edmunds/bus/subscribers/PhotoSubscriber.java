package pl.chiqvito.edmunds.bus.subscribers;

import android.content.Context;
import android.util.Log;

import de.greenrobot.event.EventBus;
import pl.chiqvito.edmunds.Constants;
import pl.chiqvito.edmunds.bus.events.GetPhotosEvent;
import pl.chiqvito.edmunds.bus.events.PhotosEvent;
import pl.chiqvito.edmunds.cache.blob.BlobCache;
import pl.chiqvito.edmunds.cache.blob.CacheData;
import pl.chiqvito.edmunds.sdk.client.BasicOnResultCallback;
import pl.chiqvito.edmunds.sdk.client.media.PhotosClient;
import pl.chiqvito.edmunds.sdk.dto.media.response.PhotosDTO;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PhotoSubscriber extends BaseSubscriber {
    private final Context context;

    public PhotoSubscriber(Context context, BlobCache blobCache) {
        super(PhotoSubscriber.class.getName(), blobCache);
        this.context = context;
    }

    public void onEventAsync(final GetPhotosEvent event) {
        Log.v(TAG, "event:" + event);
        CacheData<PhotosDTO> data = restoreFromCache(key(event), PhotosDTO.class);
        EventBus.getDefault().post(new PhotosEvent(data.getItem()));
        if (data.isOld()) {
            PhotosClient client = new PhotosClient(context, Constants.API_KEY, event.getYear().getMake().getNiceName(), event.getYear().getModel().getNiceName(), event.getYear().getYear());
            client.setOnResultCallback(new BasicOnResultCallback<PhotosDTO>() {
                @Override
                public void onResponseOk(PhotosDTO photosDTO, Response r) {
                    storeToCache(key(event), photosDTO);
                    EventBus.getDefault().post(new PhotosEvent(photosDTO));
                }

                @Override
                public void onFail(RetrofitError error) {
                    super.onFail(error);
                    EventBus.getDefault().post(new PhotosEvent(null));
                }
            });
            client.execute();
        }
    }
}
