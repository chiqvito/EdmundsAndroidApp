package pl.chiqvito.edmunds.ui.fragment;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pl.chiqvito.edmunds.Constants;
import pl.chiqvito.edmunds.bus.events.GetPhotosEvent;
import pl.chiqvito.edmunds.bus.events.PhotosEvent;
import pl.chiqvito.edmunds.sdk.dto.vehicle.response.YearDTO;
import pl.chiqvito.edmunds.ui.adapter.BaseRecyclerViewAdapter;
import pl.chiqvito.edmunds.ui.adapter.PhotoAdapter;
import pl.chiqvito.edmunds.ui.model.BaseModel;

public class PhotosFragment extends BaseListFragment {

    private static final String TAG = PhotosFragment.class.getName();

    public static PhotosFragment newInstance(FragmentBuilder.FragmentName fn, YearDTO year) {
        PhotosFragment fragment = new PhotosFragment();
        Bundle args = new Bundle();
        args.putString(Constants.FRAGMENT_NAME, fn.name());
        args.putParcelable("year", year);
        fragment.setArguments(args);
        return fragment;
    }

    private YearDTO year() {
        return getArguments().getParcelable("year");
    }

    @Override
    protected BaseRecyclerViewAdapter adapter() {
        return new PhotoAdapter();
    }

    @Override
    public void onStart() {
        super.onStart();
        fetchData(new GetPhotosEvent(year()));
    }

    public void onEventMainThread(PhotosEvent event) {
        Log.v(TAG, "event:" + event);
        List<BaseModel> models = new ArrayList<BaseModel>();
        if (event.getPhotos() != null) {
            //TODO
        }
        load(models);
    }
}
