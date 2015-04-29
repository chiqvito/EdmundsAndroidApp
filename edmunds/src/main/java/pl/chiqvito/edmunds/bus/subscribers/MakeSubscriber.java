package pl.chiqvito.edmunds.bus.subscribers;

import android.content.Context;
import android.util.Log;

import de.greenrobot.event.EventBus;
import pl.chiqvito.edmunds.Constants;
import pl.chiqvito.edmunds.bus.events.GetMakesEvent;
import pl.chiqvito.edmunds.bus.events.MakesEvent;
import pl.chiqvito.edmunds.cache.blob.CacheData;
import pl.chiqvito.edmunds.sdk.client.BasicOnResultCallback;
import pl.chiqvito.edmunds.sdk.client.vehicle.MakesClient;
import pl.chiqvito.edmunds.sdk.dto.vehicle.response.MakesDTO;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MakeSubscriber extends BaseSubscriber {

    private final Context context;

    public MakeSubscriber(Context context) {
        super(MakeSubscriber.class.getName());
        this.context = context;
    }

    public void onEventAsync(final GetMakesEvent event) {
        Log.v(TAG, "event:" + event);
        CacheData<MakesDTO> data = restoreFromCache(key(event), MakesDTO.class);
        EventBus.getDefault().post(new MakesEvent(data.getItem()));
        if (data.isOld()) {
            MakesClient client = new MakesClient(context, Constants.API_KEY, event.getState(), event.getYear());
            client.setOnResultCallback(new BasicOnResultCallback<MakesDTO>() {
                @Override
                public void onResponseOk(MakesDTO makesDTO, Response r) {
                    storeToCache(key(event), makesDTO);
                    EventBus.getDefault().post(new MakesEvent(makesDTO));
                }

                @Override
                public void onFail(RetrofitError error) {
                    super.onFail(error);
                    EventBus.getDefault().post(new MakesEvent(null));
                }
            });
            client.execute();
        }
    }

}
