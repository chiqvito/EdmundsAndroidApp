package pl.chiqvito.edmunds.ui.fragment;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pl.chiqvito.edmunds.Constants;
import pl.chiqvito.edmunds.bus.events.GetModelsEvent;
import pl.chiqvito.edmunds.bus.events.ModelsEvent;
import pl.chiqvito.edmunds.sdk.dto.vehicle.response.MakeDTO;
import pl.chiqvito.edmunds.sdk.dto.vehicle.response.ModelDTO;
import pl.chiqvito.edmunds.ui.adapter.BaseRecyclerViewAdapter;
import pl.chiqvito.edmunds.ui.adapter.ModelAdapter;
import pl.chiqvito.edmunds.ui.model.BaseModel;
import pl.chiqvito.edmunds.ui.model.CountModel;
import pl.chiqvito.edmunds.ui.model.ModelModel;

public class ModelsFragment extends BaseListFragment {

    private static final String TAG = ModelsFragment.class.getName();

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

    @Override
    public void onStart() {
        super.onStart();
        fetchData(new GetModelsEvent(make()));
    }

    public void onEventMainThread(ModelsEvent event) {
        Log.v(TAG, "event:" + event);
        List<BaseModel> models = new ArrayList<BaseModel>();
        if (event.getModels() != null) {
            models.add(new CountModel(event.getModels().getModelsCount()));
            for (ModelDTO model : event.getModels().getModels()) {
                models.add(new ModelModel(model));
            }
        }
        load(models);
    }

}
