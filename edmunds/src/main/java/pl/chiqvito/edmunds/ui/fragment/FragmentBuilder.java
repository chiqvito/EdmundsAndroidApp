package pl.chiqvito.edmunds.ui.fragment;

import android.os.Parcelable;

import pl.chiqvito.edmunds.sdk.dto.vehicle.response.MakeDTO;
import pl.chiqvito.edmunds.sdk.dto.vehicle.response.YearDTO;

public class FragmentBuilder {
    public enum FragmentName {
        MAKES,
        MODELS,
        PHOTOS,
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
            case PHOTOS: {
                return PhotosFragment.newInstance(fn, (YearDTO) parcelable);
            }
        }
        throw new IllegalArgumentException("Unknown fragment for " + fn);
    }
}
