package pl.chiqvito.edmunds.ui.adapter;

import pl.chiqvito.edmunds.ui.model.CountModel;
import pl.chiqvito.edmunds.ui.model.ModelModel;
import pl.chiqvito.edmunds.ui.model.YearModel;

public class ModelAdapter extends BaseRecyclerViewAdapter {
    public ModelAdapter() {
        super(ModelAdapter.class.getName());
        new CountModel(mViewModels);
        new ModelModel(mViewModels);
        new YearModel(mViewModels);
    }
}
