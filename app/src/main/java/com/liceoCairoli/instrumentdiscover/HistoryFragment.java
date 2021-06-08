package com.liceoCairoli.instrumentdiscover;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HistoryFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.lv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String x[] = {"1", "2","3","4","5","6","7"};
        HistoryFragment.MyAdapter historyAdapter;
        historyAdapter = new HistoryFragment.MyAdapter(Arrays.asList(x), getContext());
        recyclerView.setAdapter(historyAdapter);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private List<String> name_here;
        private Context context;

        public MyAdapter(List<String> name_here,  Context ctx) {
            this.name_here = name_here;
            context = ctx;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_element, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.name.setText(new StringBuilder().append("Nome :").append(name_here.get(position)));

        }

        @Override
        public int getItemCount() {
            return 7;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public TextView name;

            public ViewHolder(View view) {
                super(view);
                name = (TextView) view.findViewById(R.id.InstrumentName);

            }
        }
    }
}