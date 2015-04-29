package pl.chiqvito.edmunds.bus.subscribers;

import android.content.Context;
import android.util.Log;

import de.greenrobot.event.EventBus;
import pl.chiqvito.edmunds.Constants;
import pl.chiqvito.edmunds.bus.events.GetModelsEvent;
import pl.chiqvito.edmunds.bus.events.ModelsEvent;
import pl.chiqvito.edmunds.sdk.client.BasicOnResultCallback;
import pl.chiqvito.edmunds.sdk.client.vehicle.ModelsClient;
import pl.chiqvito.edmunds.sdk.dto.vehicle.response.ModelsDTO;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ModelSubscriber {

    private final static String TAG = ModelSubscriber.class.getName();

    private final Context context;

    public ModelSubscriber(Context context) {
        this.context = context;
    }

    public void onEventAsync(GetModelsEvent event) {
        Log.v(TAG, "event:" + event);
        ModelsClient client = new ModelsClient(context, Constants.API_KEY, event.getMake().getName());
        client.setOnResultCallback(new BasicOnResultCallback<ModelsDTO>() {
            @Override
            public void onResponseOk(ModelsDTO modelsDTO, Response r) {
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
