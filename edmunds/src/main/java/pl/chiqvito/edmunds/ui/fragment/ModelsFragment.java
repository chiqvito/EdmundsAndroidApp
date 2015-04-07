package pl.chiqvito.edmunds.ui.fragment;

import android.os.Bundle;

import pl.chiqvito.edmunds.Constants;
import pl.chiqvito.edmunds.sdk.dto.vehicle.response.MakeDTO;
import pl.chiqvito.edmunds.ui.adapter.BaseRecyclerViewAdapter;
import pl.chiqvito.edmunds.ui.adapter.ModelAdapter;

public class ModelsFragment extends BaseListFragment {

    public static ModelsFragment newInstance(FragmentBuilder.FragmentName fn, MakeDTO make) {
        ModelsFragment fragment = new ModelsFragment();
        Bundle args = new Bundle();
        args.putString(Constants.FRAGMENT_NAME, fn.name());
        args.putParcelable("make", make);
        fragment.setArguments(args);
        return fragment;
    }

    private MakeDTO make() {
        return getArguments().getParcelable("make");
    }

    @Override
    protected BaseRecyclerViewAdapter adapter() {
        return new ModelAdapter();
    }
}
