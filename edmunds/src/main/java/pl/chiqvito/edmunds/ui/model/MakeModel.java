package pl.chiqvito.edmunds.ui.model;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import pl.chiqvito.edmunds.R;
import pl.chiqvito.edmunds.sdk.dto.vehicle.response.MakeDTO;

public class MakeModel extends BaseModel {

    private MakeDTO make;

    public MakeModel(MakeDTO make) {
        this.make = make;
    }

    public MakeModel(SparseArray<BaseModel> viewModels) {
        super(viewModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup container) {
        return new ViewHolder(inflate(container, R.layout.row_make));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder itemHolder, int position) {
        ((ViewHolder) itemHolder).bindView(make);
    }

    @Override
    public int getViewType() {
        return ModelType.MAKE;
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout fl;
        private TextView text;

        private ViewHolder(View itemView) {
            super(itemView);
            fl = (FrameLayout) itemView.findViewById(R.id.cont_fl);
            text = (TextView) itemView.findViewById(R.id.txt);
        }

        public void bindView(MakeDTO make) {
            text.setText(make.getName());
            fl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
                }
            });
        }
    }

}
