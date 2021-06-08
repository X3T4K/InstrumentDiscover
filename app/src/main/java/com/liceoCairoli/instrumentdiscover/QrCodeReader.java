package com.liceoCairoli.instrumentdiscover;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class QrCodeReader extends Fragment {
    private CodeScanner mCodeScanner;
    List<String> scanResult;
    String x;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final Activity activity = getActivity();
        View root = inflater.inflate(R.layout.fragment_qrcode, container, false);
        CodeScannerView scannerView = root.findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(activity, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        StringTokenizer st = new StringTokenizer(result.getText(), "&&");
                        scanResult = new ArrayList<>();
                        while (st.hasMoreTokens()) {
                            scanResult.add(st.nextToken());
                        }
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        System.out.println("GG");
        super.onPause();
    }
    public void goTovideo(View view){
        newInstance(scanResult.get(1));
    }
    public void goTodoc(View view){

    }

    public static VideoPlayer newInstance(String link) {
        VideoPlayer fragmentclass1 = new VideoPlayer();

        Bundle args = new Bundle();
        args.putString("message", link);
        fragmentclass1.setArguments(args);

        return fragmentclass1;
    }
}

