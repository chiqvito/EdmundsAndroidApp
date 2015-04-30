package pl.chiqvito.edmunds.ui.model;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import de.greenrobot.event.EventBus;
import pl.chiqvito.edmunds.R;
import pl.chiqvito.edmunds.bus.events.SwitchFragmentEvent;
import pl.chiqvito.edmunds.sdk.dto.vehicle.response.YearDTO;
import pl.chiqvito.edmunds.ui.fragment.FragmentBuilder;

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
        private FrameLayout fl;
        private TextView text;

        private ViewHolder(View itemView) {
            super(itemView);
            fl = (FrameLayout) itemView.findViewById(R.id.cont_fl);
            text = (TextView) itemView.findViewById(R.id.txt);
        }

        public void bindView(final YearDTO year) {
            text.setText(" - " + year.getYear());
            fl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new SwitchFragmentEvent(FragmentBuilder.FragmentName.PHOTOS, year));
                }
            });
        }
    }

}
