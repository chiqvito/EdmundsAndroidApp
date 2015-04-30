package pl.chiqvito.edmunds;

import android.app.Application;

import pl.chiqvito.edmunds.bus.EventBusConfigurator;
import pl.chiqvito.edmunds.cache.blob.BlobCache;

public class EdmundsApplication extends Application {

    private EventBusConfigurator eventBusConfigurator;
    private BlobCache blobCache;

    @Override
    public void onCreate() {
        super.onCreate();
        blobCache = new BlobCache(this);
        EventBusConfigurator.configDefaultEventBus();
        eventBusConfigurator = new EventBusConfigurator(this, blobCache);
        eventBusConfigurator.register();
    }

    @Override
    public void onTerminate() {
        eventBusConfigurator.unregister();
        // not guaranteed to be called
        super.onTerminate();
    }
}
