package pl.chiqvito.edmunds.ui.adapter;

import pl.chiqvito.edmunds.ui.model.MakeModel;

public class MakeAdapter extends BaseRecyclerViewAdapter {
    public MakeAdapter() {
        super(MakeAdapter.class.getName());
        new MakeModel(mViewModels);
    }
}