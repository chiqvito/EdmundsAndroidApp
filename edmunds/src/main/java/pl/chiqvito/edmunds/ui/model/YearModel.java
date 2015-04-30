package pl.chiqvito.edmunds.ui.model;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.chiqvito.edmunds.R;
import pl.chiqvito.edmunds.sdk.dto.vehicle.response.YearDTO;

public class YearModel extends BaseModel {

    private YearDTO year;

    public YearModel(YearDTO year) {
        this.year = year;
    }

    public YearModel(SparseArray<BaseModel> viewModels) {
        super(viewModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup container) {
        return new ViewHolder(inflate(container, R.layout.row_year));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder itemHolder, int position) {
        ((ViewHolder) itemHolder).bindView(year);
    }

    @Override
    public int getViewType() {
        return ModelType.YEAR;
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text;

        private ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.txt);
        }

        public void bindView(final YearDTO year) {
            text.setText(" - " + year.getYear());
        }
    }

}
