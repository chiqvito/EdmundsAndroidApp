package pl.chiqvito.edmunds.bus.subscribers;

import android.content.Context;
import android.util.Log;

import de.greenrobot.event.EventBus;
import pl.chiqvito.edmunds.Constants;
import pl.chiqvito.edmunds.bus.events.GetMakesEvent;
import pl.chiqvito.edmunds.bus.events.MakesEvent;
import pl.chiqvito.edmunds.sdk.client.BasicOnResultCallback;
import pl.chiqvito.edmunds.sdk.client.vehicle.MakesClient;
import pl.chiqvito.edmunds.sdk.dto.vehicle.response.MakesDTO;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MakeSubscriber {

    private final static String TAG = MakeSubscriber.class.getName();

    private final Context context;

    public MakeSubscriber(Context context) {
        this.context = context;
    }

    public void onEventAsync(GetMakesEvent event) {
        Log.v(TAG, "event:" + event);
        MakesClient client = new MakesClient(context, Constants.API_KEY, event.getState(), event.getYear());
        client.setOnResultCallback(new BasicOnResultCallback<MakesDTO>() {
            @Override
            public void onResponseOk(MakesDTO makesDTO, Response r) {
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
