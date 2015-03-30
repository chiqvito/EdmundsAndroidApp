package pl.chiqvito.edmunds.ui.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pl.chiqvito.edmunds.R;
import pl.chiqvito.edmunds.sdk.dto.enums.StateEnum;
import pl.chiqvito.edmunds.ui.adapter.SpinnerOptionAdapter;

public class StateYearFilterModel extends BaseModel {

    private FilterCallback callback;

    public StateYearFilterModel(FilterCallback callback) {
        this.callback = callback;
    }

    public StateYearFilterModel(SparseArray<BaseModel> viewModels) {
        super(viewModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup container) {
        return new ViewHolder(inflate(container, R.layout.row_filter_state_year));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder itemHolder, int position) {
        ((ViewHolder) itemHolder).bindView(callback);
    }

    @Override
    public int getViewType() {
        return ModelType.STATE_YEAR_FILTER;
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        private Spinner yearSpinner;
        private Spinner stateSpinner;
        private SpinnerOptionAdapter<Integer> yearsAdapter;
        private SpinnerOptionAdapter<StateEnum> stateAdapter;
        private int yearPosition = 0;
        private int statePosition = 0;

        public ViewHolder(View itemView) {
            super(itemView);
            yearSpinner = (Spinner) itemView.findViewById(R.id.year_spinner);
            stateSpinner = (Spinner) itemView.findViewById(R.id.state_spinner);

            List<OptionItem<StateEnum>> states = new ArrayList<OptionItem<StateEnum>>();
            states.add(new OptionItem<StateEnum>(null));
            for (StateEnum se : StateEnum.values())
                states.add(new OptionItem<StateEnum>(se));
            stateAdapter = new SpinnerOptionAdapter<StateEnum>(itemView.getContext(), states) {
                @Override
                protected String getTitle(Context context, OptionItem<StateEnum> item) {
                    if (item.getType() == null)
                        return context.getString(R.string.label_all);
                    return item.getType().toString();
                }
            };
            stateSpinner.setAdapter(stateAdapter);

            List<OptionItem<Integer>> years = new ArrayList<OptionItem<Integer>>();
            years.add(new OptionItem<Integer>(null));
            for (int i = Calendar.getInstance().get(Calendar.YEAR); i >= 1990; i--)
                years.add(new OptionItem<Integer>(i));
            yearsAdapter = new SpinnerOptionAdapter<Integer>(itemView.getContext(), years) {
                @Override
                protected String getTitle(Context context, OptionItem<Integer> item) {
                    if (item.getType() == null)
                        return context.getString(R.string.label_all);
                    return item.getType().toString();
                }
            };
            yearSpinner.setAdapter(yearsAdapter);
        }

        public void bindView(final FilterCallback callback) {
            stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    statePosition = position;
                    callback.filter(stateAdapter.getItem(statePosition).getType(), yearsAdapter.getItem(yearPosition).getType());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    yearPosition = position;
                    callback.filter(stateAdapter.getItem(statePosition).getType(), yearsAdapter.getItem(yearPosition).getType());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    public static interface FilterCallback {
        void filter(StateEnum state, Integer year);
    }
}
