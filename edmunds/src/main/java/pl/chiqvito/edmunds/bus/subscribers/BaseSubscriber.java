package pl.chiqvito.edmunds.bus.subscribers;

import android.os.Parcelable;
import android.util.Log;

import java.util.Date;

import pl.chiqvito.edmunds.bus.events.Event;
import pl.chiqvito.edmunds.cache.blob.BlobCache;
import pl.chiqvito.edmunds.cache.blob.CacheData;

public abstract class BaseSubscriber {

    protected final String TAG;
    private final BlobCache blobCache;

    protected BaseSubscriber(String TAG, BlobCache blobCache) {
        this.TAG = TAG;
        this.blobCache = blobCache;
    }

    protected String key(Event event) {
        return event.key();
    }

    protected <T> CacheData<T> restoreFromCache(String key, Class<T> resultClass) {
        Log.d(TAG, "restoreFromCache:" + key);
        CacheData<T> data = blobCache.get(key, resultClass);
        Log.d(TAG, "restoreFromCache data:" + data);
        return data;
    }

    protected void storeToCache(String key, Parcelable parcelable) {
        Log.d(TAG, "storeToCache:" + key);
        CacheData<Parcelable> data = new CacheData<Parcelable>(parcelable, new Date());
        Log.d(TAG, "storeToCache data:" + data);
        blobCache.put(key, data);
    }

}
