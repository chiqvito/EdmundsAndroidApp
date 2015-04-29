package pl.chiqvito.edmunds.ui.model;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.chiqvito.edmunds.R;
import pl.chiqvito.edmunds.sdk.dto.vehicle.response.ModelDTO;

public class ModelModel extends BaseModel {

    private ModelDTO model;

    public ModelModel(ModelDTO model) {
        this.model = model;
    }

    public ModelModel(SparseArray<BaseModel> viewModels) {
        super(viewModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup container) {
        return new ViewHolder(inflate(container, R.layout.row_model));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder itemHolder, int position) {
        ((ViewHolder) itemHolder).bindView(model);
    }

    @Override
    public int getViewType() {
        return ModelType.MODEL;
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text;
        
        private ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.txt);
        }

        public void bindView(final ModelDTO model) {
            text.setText(model.getName());
        }
    }

}
