package pl.chiqvito.edmunds.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.chiqvito.edmunds.Constants;
import pl.chiqvito.edmunds.bus.events.GetMakesEvent;
import pl.chiqvito.edmunds.bus.events.MakesEvent;
import pl.chiqvito.edmunds.sdk.dto.enums.StateEnum;
import pl.chiqvito.edmunds.sdk.dto.vehicle.response.MakeDTO;
import pl.chiqvito.edmunds.ui.adapter.BaseRecyclerViewAdapter;
import pl.chiqvito.edmunds.ui.adapter.MakeAdapter;
import pl.chiqvito.edmunds.ui.model.BaseModel;
import pl.chiqvito.edmunds.ui.model.CountModel;
import pl.chiqvito.edmunds.ui.model.MakeModel;
import pl.chiqvito.edmunds.ui.model.StateYearFilterModel;

public class MakesFragment extends BaseListFragment implements StateYearFilterModel.FilterCallback {

    private static final String TAG = MakesFragment.class.getName();
    private StateYearFilterModel filter;

    public static MakesFragment newInstance(FragmentBuilder.FragmentName fn) {
        MakesFragment fragment = new MakesFragment();
        Bundle args = new Bundle();
        args.putString(Constants.FRAGMENT_NAME, fn.name());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        filter = new StateYearFilterModel(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected BaseRecyclerViewAdapter adapter() {
        return new MakeAdapter() {
            @Override
            protected void initEmptyList() {
                super.initEmptyList();
                getItemsModel().add(filter);
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        fetchData(new GetMakesEvent());
    }

    @Override
    public void filter(StateEnum state, Integer year) {
        fetchData(new GetMakesEvent(year, state));
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
