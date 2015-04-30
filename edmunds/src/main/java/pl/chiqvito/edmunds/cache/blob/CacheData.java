package pl.chiqvito.edmunds.cache.blob;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import pl.chiqvito.edmunds.Constants;

public class CacheData<T> {

    private final static String TAG = CacheData.class.getSimpleName();

    private T item;
    private Date date;

    public CacheData(T item, Date date) {
        this.item = item;
        this.date = date;
    }

    public T getItem() {
        return item;
    }

    public Date getDate() {
        return date;
    }

    public boolean isOld() {
        Log.v(TAG, "isOld:" + date);
        if (date == null)
            return true;
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.MINUTE, Constants.CACHE_TO_CLEAR_MIN);
        Log.v(TAG, "isOld:" + date + ", exp:" + cal.getTime());
        boolean status = date.before(cal.getTime());
        Log.v(TAG, "isOld:" + status);
        return status;
    }

    @Override
    public String toString() {
        return "CacheData{date=" + date + ", item=" + item + '}';
    }
}
