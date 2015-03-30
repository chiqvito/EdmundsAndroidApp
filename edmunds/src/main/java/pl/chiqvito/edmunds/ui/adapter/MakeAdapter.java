package pl.chiqvito.edmunds.ui.adapter;

import pl.chiqvito.edmunds.ui.model.CountModel;
import pl.chiqvito.edmunds.ui.model.MakeModel;
import pl.chiqvito.edmunds.ui.model.StateYearFilterModel;

public class MakeAdapter extends BaseRecyclerViewAdapter {
    public MakeAdapter() {
        super(MakeAdapter.class.getName());
        new StateYearFilterModel(mViewModels);
        new CountModel(mViewModels);
        new MakeModel(mViewModels);
    }
}
