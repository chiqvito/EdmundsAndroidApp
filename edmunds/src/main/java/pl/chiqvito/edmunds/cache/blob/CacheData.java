package pl.chiqvito.edmunds.cache.blob;

import java.util.Date;

public class CacheData<T> {

    private T item;
    private Date date;

    public CacheData(T item, Date date) {
        this.item = item;
        this.date = date;
    }

    public T getItem() {
        return item;
    }

    public boolean isOld() {
        //TODO
        return true;
    }

}
