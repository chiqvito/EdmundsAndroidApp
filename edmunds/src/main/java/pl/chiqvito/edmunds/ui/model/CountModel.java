package pl.chiqvito.edmunds.ui.model;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.chiqvito.edmunds.R;

public class CountModel extends BaseModel {

    private Integer count;

    public CountModel(Integer count) {
        this.count = count;
    }

    public CountModel(SparseArray<BaseModel> viewModels) {
        super(viewModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup container) {
        return new ViewHolder(inflate(container, R.layout.row_count));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder itemHolder, int position) {
        ((ViewHolder) itemHolder).bindView(count);
    }

    @Override
    public int getViewType() {
        return ModelType.COUNT;
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView text;

        private ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.txt);
        }

        public void bindView(Integer count) {
            text.setText(itemView.getContext().getString(R.string.label_count) + ": " + count);
        }
    }
}
