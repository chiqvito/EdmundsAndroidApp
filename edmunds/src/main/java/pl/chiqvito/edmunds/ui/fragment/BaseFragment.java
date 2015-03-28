package pl.chiqvito.edmunds.ui.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;

import de.greenrobot.event.EventBus;
import pl.chiqvito.edmunds.Constants;

public class BaseFragment extends Fragment {

    protected FragmentBuilder.FragmentName fragmentName() {
        return FragmentBuilder.FragmentName.valueOf(getArguments().getString(Constants.FRAGMENT_NAME));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
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

}
