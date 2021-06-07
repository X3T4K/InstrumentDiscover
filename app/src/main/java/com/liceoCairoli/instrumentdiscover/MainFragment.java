package com.liceoCairoli.instrumentdiscover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.budiyev.android.codescanner.CodeScanner;

public class MainFragment extends Fragment {
    private CodeScanner mCodeScanner;

    public MainFragment(CodeScanner mCodeScanner) {
        this.mCodeScanner = mCodeScanner;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {



        return null;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


        @Override
        public void onResume () {
            super.onResume();
            mCodeScanner.startPreview();
        }

        @Override
        public void onPause () {
            mCodeScanner.releaseResources();
            super.onPause();
        }

}
