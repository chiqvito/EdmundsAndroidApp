package pl.chiqvito.edmunds.cache.blob;

import android.content.Context;
import android.os.Parcel;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;

import org.apache.commons.io.IOUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import pl.chiqvito.edmunds.cache.BaseCache;
import pl.chiqvito.edmunds.utils.ContextHelper;

public class BlobCache extends BaseCache {

    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 100; // 100MB
    private static final int VALUE_COUNT = 2;

    private DiskLruCache diskCache;
    private int indexItem = 0;
    private int indexDate = 1;

    public BlobCache(Context context) {
        super(BlobCache.class.getSimpleName(), context);
    }

    public void put(String key, CacheData<?> item) {
        ItemWriteToFile wtf = new ItemWriteToFile(item);
        put(key, wtf);
    }

    public <T> CacheData<T> get(String key, Class<T> resultClass) {
        T item = deparcelable(key, indexItem, resultClass);
        Date date = deserializable(key);
        return new CacheData<T>(item, date);
    }

    private Date deserializable(String key) {
        Long time = deparcelable(key, indexDate, Long.class);
        if (time != null)
            return new Date(time);
        //invalidate cache
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.HOUR, -12);
        return cal.getTime();
    }

    private <T> T deparcelable(String key, int index, Class<T> resultClass) {
        DiskLruCache.Snapshot snapshot = null;
        try {
            String cacheKey = hexKey(key);
            snapshot = diskCache().get(cacheKey);
            if (snapshot == null) {
                return null;
            }
            final InputStream in = snapshot.getInputStream(index);
            if (in != null) {
                byte[] bytes = IOUtils.toByteArray(in);
                Parcel pracel = Parcel.obtain();
                pracel.unmarshall(bytes, 0, bytes.length);
                pracel.setDataPosition(0);
                @SuppressWarnings("unchecked")
                T result = (T) pracel.readValue(resultClass.getClassLoader());
                pracel.recycle();
                return result;
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        } finally {
            if (snapshot != null) {
                snapshot.close();
            }
        }
        return null;
    }

    @Override
    public void clearCache() {
        Log.d(TAG, "disk cache CLEARED");
        try {
            diskCache().delete();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    protected DiskLruCache diskCache() {
        if (diskCache != null && !diskCache.isClosed())
            return diskCache;
        try {
            final File diskCacheDir = ContextHelper.getDiskCacheDir(context, "blob");
            diskCache = DiskLruCache.open(diskCacheDir, APP_VERSION, VALUE_COUNT, DISK_CACHE_SIZE);
            return diskCache;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return null;
    }

    private class ItemWriteToFile<T> implements WriteToFile {

        private CacheData<T> item;

        public ItemWriteToFile(CacheData<T> item) {
            this.item = item;
        }

        @Override
        public boolean write(DiskLruCache.Editor editor) throws Exception {
            Parcel pracel = Parcel.obtain();
            pracel.writeValue(item.getItem());

            byte[] bytes = pracel.marshall();
            pracel.recycle();

            boolean statusI = write(editor, indexItem, bytes);

            pracel = Parcel.obtain();
            pracel.writeValue(item.getDate().getTime());

            bytes = pracel.marshall();
            pracel.recycle();

            boolean statusD = write(editor, indexDate, bytes);

            return statusI && statusD;
        }

        private boolean write(DiskLruCache.Editor editor, int index, byte[] bytes) throws Exception {
            OutputStream out = null;
            try {
                out = new BufferedOutputStream(editor.newOutputStream(index));
                out.write(bytes);
                out.flush();

                return true;
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }

    }

}
