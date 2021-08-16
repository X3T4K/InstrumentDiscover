package com.liceoCairoli.instrumentdiscover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.liceoCairoli.instrumentdiscover.dB.Instrument;
import com.liceoCairoli.instrumentdiscover.dB.InstrumentRepository;

import java.util.List;

public class  HistoryFragment extends Fragment {

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
        final InstrumentListAdapter adapter = new InstrumentListAdapter(new InstrumentDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    }

    public static class InstrumentListAdapter extends ListAdapter<Instrument, InstrumentViewHolder> {

        public InstrumentListAdapter(@NonNull DiffUtil.ItemCallback<Instrument> diffCallback) {
            super(diffCallback);
        }

        @Override
        public InstrumentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return InstrumentViewHolder.create(parent);
        }

        @Override
        public void onBindViewHolder(InstrumentViewHolder holder, int position) {
            Instrument current = getItem(position);
            holder.bind(current.getName());
        }


    }

    static class InstrumentDiff extends DiffUtil.ItemCallback<Instrument> {

        @Override
        public boolean areItemsTheSame(@NonNull Instrument oldItem, @NonNull Instrument newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Instrument oldItem, @NonNull Instrument newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }

    static class InstrumentViewHolder extends RecyclerView.ViewHolder {
        private final TextView instrumentItemView;

        private InstrumentViewHolder(View itemView) {
            super(itemView);
            instrumentItemView = itemView.findViewById(R.id.textView);
        }

        public void bind(String text) {
            instrumentItemView.setText(text);
        }

        static InstrumentViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.history_list_element, parent, false);
            return new InstrumentViewHolder(view);
        }

    }

}