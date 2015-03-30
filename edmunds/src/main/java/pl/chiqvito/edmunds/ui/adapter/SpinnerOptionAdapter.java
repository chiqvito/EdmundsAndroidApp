package pl.chiqvito.edmunds.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.chiqvito.edmunds.R;
import pl.chiqvito.edmunds.ui.model.OptionItem;

public abstract class SpinnerOptionAdapter<T> extends BaseArrayAdapter<OptionItem<T>> {
    public SpinnerOptionAdapter(Context context) {
        this(context, new ArrayList<OptionItem<T>>());
    }

    protected SpinnerOptionAdapter(Context context, List<OptionItem<T>> items) {
        super(context, R.layout.row_layout_spinner_option, items);
    }

    protected abstract String getTitle(Context context, OptionItem<T> item);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View result = convertView;
        ViewHolder holder = null;

        if (result == null) {
            result = super.getView(position, convertView, parent);
            holder = new ViewHolder();

            holder.text = (TextView) result.findViewById(R.id.text);

            result.setTag(holder);
        } else {
            holder = (ViewHolder) result.getTag();
        }

        holder.text.setText(getTitle(getContext(), getItem(position)));

        return result;
    }

    private static class ViewHolder {
        TextView text;
    }
}
