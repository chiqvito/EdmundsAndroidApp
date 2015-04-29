package pl.chiqvito.edmunds.bus;

import android.content.Context;

import de.greenrobot.event.EventBus;
import pl.chiqvito.edmunds.BuildConfig;
import pl.chiqvito.edmunds.bus.subscribers.MakeSubscriber;
import pl.chiqvito.edmunds.bus.subscribers.ModelSubscriber;

public class EventBusConfigurator {

    private Context context;

    private MakeSubscriber makeSubscriber;
    private ModelSubscriber modelSubscriber;

    public EventBusConfigurator(Context context) {
        this.context = context;
    }

    private void build() {
        makeSubscriber = new MakeSubscriber(context);
        modelSubscriber = new ModelSubscriber(context);
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
