package pl.chiqvito.edmunds.bus;

import android.content.Context;

import de.greenrobot.event.EventBus;
import pl.chiqvito.edmunds.BuildConfig;
import pl.chiqvito.edmunds.bus.subscribers.MakeSubscriber;

public class EventBusConfigurator {

    private Context context;

    private MakeSubscriber makeSubscriber;

    public EventBusConfigurator(Context context) {
        this.context = context;
    }

    private void build() {
        makeSubscriber = new MakeSubscriber(context);
    }

    public void register() {
        build();
        EventBus.getDefault().register(makeSubscriber);
    }

    public void unregister() {
        EventBus.getDefault().unregister(makeSubscriber);
    }

    public static void configDefaultEventBus() {
        EventBus.builder()
                .logNoSubscriberMessages(false)
                .sendNoSubscriberEvent(false)
                .throwSubscriberException(BuildConfig.DEBUG)
                .installDefaultEventBus();
    }

}
