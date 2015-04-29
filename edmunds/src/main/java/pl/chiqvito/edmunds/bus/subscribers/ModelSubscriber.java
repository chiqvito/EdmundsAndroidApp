package pl.chiqvito.edmunds.bus.subscribers;

import android.content.Context;
import android.util.Log;

import de.greenrobot.event.EventBus;
import pl.chiqvito.edmunds.Constants;
import pl.chiqvito.edmunds.bus.events.GetModelsEvent;
import pl.chiqvito.edmunds.bus.events.ModelsEvent;
import pl.chiqvito.edmunds.cache.blob.CacheData;
import pl.chiqvito.edmunds.sdk.client.BasicOnResultCallback;
import pl.chiqvito.edmunds.sdk.client.vehicle.ModelsClient;
import pl.chiqvito.edmunds.sdk.dto.vehicle.response.ModelsDTO;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ModelSubscriber extends BaseSubscriber {

    private final Context context;

    public ModelSubscriber(Context context) {
        super(ModelSubscriber.class.getName());
        this.context = context;
    }

    public void onEventAsync(final GetModelsEvent event) {
        Log.v(TAG, "event:" + event);
        CacheData<ModelsDTO> data = restoreFromCache(key(event), ModelsDTO.class);
        EventBus.getDefault().post(new ModelsEvent(data.getItem()));
        if (data.isOld()) {
            ModelsClient client = new ModelsClient(context, Constants.API_KEY, event.getMake().getNiceName());
            client.setOnResultCallback(new BasicOnResultCallback<ModelsDTO>() {
                @Override
                public void onResponseOk(ModelsDTO modelsDTO, Response r) {
                    storeToCache(key(event), modelsDTO);
                    EventBus.getDefault().post(new ModelsEvent(modelsDTO));
                }

                @Override
                public void onFail(RetrofitError error) {
                    super.onFail(error);
                    EventBus.getDefault().post(new ModelsEvent(null));
                }
            });
            client.execute();
        }
    }

}
