package pl.chiqvito.edmunds.ui.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;

import de.greenrobot.event.EventBus;
import pl.chiqvito.edmunds.Constants;
import pl.chiqvito.edmunds.bus.events.SwitchFragmentEvent;
import pl.chiqvito.edmunds.ui.activity.NavigationCallbacks;

public class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getName();

    private NavigationCallbacks mCallbacks;

    protected FragmentBuilder.FragmentName fragmentName() {
        return FragmentBuilder.FragmentName.valueOf(getArguments().getString(Constants.FRAGMENT_NAME));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(SwitchFragmentEvent event) {
        Log.v(TAG, "event:" + event);
        Fragment fragment = new FragmentBuilder(event.getFragmentName()).parcelable(event.getParcelable()).build();
        mCallbacks.onNavigationItemSelected(fragment);
    }

}
