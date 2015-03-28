package pl.chiqvito.edmunds.ui.fragment;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pl.chiqvito.edmunds.Constants;
import pl.chiqvito.edmunds.bus.events.GetMakesEvent;
import pl.chiqvito.edmunds.bus.events.MakesEvent;
import pl.chiqvito.edmunds.sdk.dto.vehicle.response.MakeDTO;
import pl.chiqvito.edmunds.ui.adapter.BaseRecyclerViewAdapter;
import pl.chiqvito.edmunds.ui.adapter.MakeAdapter;
import pl.chiqvito.edmunds.ui.model.BaseModel;
import pl.chiqvito.edmunds.ui.model.CountModel;
import pl.chiqvito.edmunds.ui.model.MakeModel;

public class MakesFragment extends BaseListFragment {

    private static final String TAG = MakesFragment.class.getName();

    public static MakesFragment newInstance(FragmentBuilder.FragmentName fn) {
        MakesFragment fragment = new MakesFragment();
        Bundle args = new Bundle();
        args.putString(Constants.FRAGMENT_NAME, fn.name());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected BaseRecyclerViewAdapter adapter() {
        return new MakeAdapter();
    }

    @Override
    public void onStart() {
        super.onStart();
        fetchData(new GetMakesEvent());
    }

    public void onEventMainThread(MakesEvent event) {
        Log.v(TAG, "event:" + event);
        List<BaseModel> models = new ArrayList<BaseModel>();
        if (event.getMakes() != null) {
            models.add(new CountModel(event.getMakes().getMakesCount()));
            for (MakeDTO make : event.getMakes().getMakes()) {
                models.add(new MakeModel(make));
            }
        }
        load(models);
    }

}
