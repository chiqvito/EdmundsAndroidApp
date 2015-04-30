package pl.chiqvito.edmunds.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import pl.chiqvito.edmunds.R;
import pl.chiqvito.edmunds.ui.fragment.BaseFragment;
import pl.chiqvito.edmunds.ui.fragment.FragmentBuilder;

public class MainActivity extends ActionBarActivity implements NavigationCallbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            onNavigationItemSelected(new FragmentBuilder(FragmentBuilder.FragmentName.MAKES).build());
        }
    }

    @Override
    public void onNavigationItemSelected(BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.name())
                .commit();
    }
}
