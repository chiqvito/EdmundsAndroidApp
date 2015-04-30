package pl.chiqvito.edmunds.bus;

import android.content.Context;

import de.greenrobot.event.EventBus;
import pl.chiqvito.edmunds.BuildConfig;
import pl.chiqvito.edmunds.bus.subscribers.MakeSubscriber;
import pl.chiqvito.edmunds.bus.subscribers.ModelSubscriber;
import pl.chiqvito.edmunds.cache.blob.BlobCache;

public class EventBusConfigurator {

    private Context context;
    private BlobCache blobCache;

    private MakeSubscriber makeSubscriber;
    private ModelSubscriber modelSubscriber;

    public EventBusConfigurator(Context context, BlobCache blobCache) {
        this.context = context;
        this.blobCache = blobCache;
    }

    private void build() {
        makeSubscriber = new MakeSubscriber(context, blobCache);
        modelSubscriber = new ModelSubscriber(context, blobCache);
    }

    public void register() {
        build();
        EventBus.getDefault().register(makeSubscriber);
        EventBus.getDefault().register(modelSubscriber);
    }

    public void unregister() {
        EventBus.getDefault().unregister(makeSubscriber);
        EventBus.getDefault().unregister(modelSubscriber);
    }

    public static void configDefaultEventBus() {
        EventBus.builder()
                .logNoSubscriberMessages(false)
                .sendNoSubscriberEvent(false)
                .throwSubscriberException(BuildConfig.DEBUG)
                .installDefaultEventBus();
    }

}
