package pl.chiqvito.edmunds.ui.model;

public class OptionItem<T> {

    private T type;

    public OptionItem(T type) {
        this.type = type;
    }

    public T getType() {
        return type;
    }

    @Override
    public String toString() {
        if (getType() == null)
            return "All";
        return getType().toString();
    }
}
