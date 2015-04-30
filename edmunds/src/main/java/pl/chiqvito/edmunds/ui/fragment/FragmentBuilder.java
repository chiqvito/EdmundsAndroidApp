package pl.chiqvito.edmunds.ui.fragment;

import android.os.Parcelable;

import pl.chiqvito.edmunds.sdk.dto.vehicle.response.MakeDTO;

public class FragmentBuilder {
    public enum FragmentName {
        MAKES,
        MODELS,
    }

    private FragmentBuilder.FragmentName fn;
    private Parcelable parcelable;

    public FragmentBuilder(FragmentName fn) {
        this.fn = fn;
    }

    public FragmentBuilder parcelable(Parcelable parcelable) {
        this.parcelable = parcelable;
        return this;
    }

    public BaseFragment build() {
        switch (fn) {
            case MAKES: {
                return MakesFragment.newInstance(fn);
            }
            case MODELS: {
                return ModelsFragment.newInstance(fn, (MakeDTO) parcelable);
            }
        }
        throw new IllegalArgumentException("Unknown fragment for " + fn);
    }
}
