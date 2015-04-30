package pl.chiqvito.edmunds.cache;

import android.content.Context;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;

import pl.chiqvito.edmunds.utils.MD5;

public abstract class BaseCache {

    private static final Charset UTF_8 = Charset.forName("UTF-8");
    protected static final int APP_VERSION = 7;

    protected final String TAG;
    protected final Context context;

    protected BaseCache(String TAG, Context context) {
        this.TAG = TAG;
        this.context = context;
    }

    public abstract void clearCache();
    
    protected abstract DiskLruCache diskCache();

    protected String hexKey(String key) {
        String md5key = MD5.md5(key);
        // to ensure 40 Characters, should add zero padding
        String hexString = String.format("%040x", new BigInteger(1, md5key.getBytes(UTF_8)));
        Log.v(TAG, "hexString:" + hexString + ", md5key:" + md5key + ", key:" + key);
        return hexString;
    }

    protected boolean remove(String key) {
        try {
            String hexKey = hexKey(key);
            return diskCache().remove(hexKey);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return false;
    }

    protected void put(String key, WriteToFile wtf) {
        Log.d(TAG, "put on cache:" + key);
        String hkey = hexKey(key);
        DiskLruCache.Editor editor = null;
        try {
            editor = diskCache().edit(hkey);
            if (editor == null) {
                return;
            }
            if (wtf.write(editor)) {
                diskCache().flush();
                editor.commit();
                Log.d(TAG, "put on disk cache " + hkey);
            } else {
                editor.abort();
                Log.e(TAG, "ERROR on: put on disk cache " + hkey);
            }
        } catch (Exception e) {
            Log.d(TAG, "ERROR on: put on disk cache " + hkey);
            try {
                if (editor != null) {
                    editor.abort();
                }
            } catch (IOException ignored) {
            }
        }
    }

    protected boolean containsKey(String key) {
        boolean contained = false;
        DiskLruCache.Snapshot snapshot = null;
        try {
            String hexKey = hexKey(key);
            if (diskCache() != null)
                snapshot = diskCache().get(hexKey);
            contained = snapshot != null;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        } finally {
            if (snapshot != null) {
                snapshot.close();
            }
        }
        Log.i(TAG, "key:" + key + ", contained:" + contained);
        return contained;
    }

    public interface WriteToFile {
        boolean write(DiskLruCache.Editor editor) throws Exception;
    }

}
