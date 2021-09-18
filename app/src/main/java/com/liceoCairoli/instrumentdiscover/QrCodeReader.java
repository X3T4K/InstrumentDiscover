package com.liceoCairoli.instrumentdiscover;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.liceoCairoli.instrumentdiscover.data.InstrumentViewModel;
import com.google.zxing.Result;
import com.liceoCairoli.instrumentdiscover.data.Instrument;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class QrCodeReader extends Fragment {
    private CodeScanner mCodeScanner;
    static List<String> scanResult;
    InstrumentViewModel mInstrumentViewModel;
    View root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final Activity activity = getActivity();
        root = inflater.inflate(R.layout.fragment_qrcode, container, false);
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
                        insertnewInstrument();
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

        Button goToVideo = root.findViewById(R.id.button_video);
        goToVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(QrCodeReader.this)
                        .navigate(R.id.action_HistoryFragment_to_VideoPlayerFragment);
            }

        });
        Button goToDoc = root.findViewById(R.id.button_doc);
        goToDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pdf_url = scanResult.get(2);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdf_url));
                startActivity(browserIntent);
            }
        });
        mInstrumentViewModel = new ViewModelProvider(this).get(InstrumentViewModel.class);

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
        super.onPause();
    }

    public void insertnewInstrument(){
        String name = scanResult.get(0);
        String ytLink = scanResult.get(1);
        String docLink = scanResult.get(2);
        Instrument newInstrument = new Instrument(0, name, ytLink, docLink);
        mInstrumentViewModel.addInstrument(newInstrument);

    }




}

