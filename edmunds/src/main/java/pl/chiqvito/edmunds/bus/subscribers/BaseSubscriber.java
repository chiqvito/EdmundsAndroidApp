package pl.chiqvito.edmunds.bus.subscribers;

import android.os.Parcelable;
import android.util.Log;

import java.util.Date;

import pl.chiqvito.edmunds.bus.events.Event;
import pl.chiqvito.edmunds.cache.blob.CacheData;

public abstract class BaseSubscriber {

    protected static String TAG;

    protected BaseSubscriber(String TAG) {
        this.TAG = TAG;
    }

    protected String key(Event event) {
        return event.key();
    }

    protected <T> CacheData<T> restoreFromCache(String key, Class<T> resultClass) {
        Log.d(TAG, "restoreFromCache:" + key);
        CacheData<T> data = new CacheData<>(null, new Date());
        //TODO
        return data;
    }

    protected void storeToCache(String key, Parcelable parcelable) {
        Log.d(TAG, "storeToCache:" + key);
        //TODO
    }

}
