package pl.chiqvito.edmunds;

import android.app.Application;

import pl.chiqvito.edmunds.bus.EventBusConfigurator;

public class EdmundsApplication extends Application {

    private EventBusConfigurator eventBusConfigurator;

    @Override
    public void onCreate() {
        super.onCreate();
        EventBusConfigurator.configDefaultEventBus();
        eventBusConfigurator = new EventBusConfigurator(this);
        eventBusConfigurator.register();
    }

    @Override
    public void onTerminate() {
        eventBusConfigurator.unregister();
        // not guaranteed to be called
        super.onTerminate();
    }
}
