package pl.chiqvito.edmunds.ui.fragment;

import android.support.v4.app.Fragment;

public class FragmentBuilder {
    public enum FragmentName {
        MAKES,
    }

    FragmentBuilder.FragmentName fn;

    public FragmentBuilder(FragmentName fn) {
        this.fn = fn;
    }

    public Fragment build() {
        switch (fn) {
            case MAKES: {
                return MakesFragment.newInstance(fn);
            }
        }
        throw new IllegalArgumentException("Unknown fragment for " + fn);
    }
}
