package pl.chiqvito.edmunds.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import pl.chiqvito.edmunds.R;
import pl.chiqvito.edmunds.ui.fragment.FragmentBuilder;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new FragmentBuilder(FragmentBuilder.FragmentName.MAKES).build())
                    .commit();
        }
    }

}
