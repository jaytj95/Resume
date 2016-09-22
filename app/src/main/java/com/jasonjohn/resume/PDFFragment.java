package com.jasonjohn.resume;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PDFFragment extends Fragment {


    PDFView pdfView;
    public PDFFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pdf, container, false);
        pdfView = (PDFView) rootView.findViewById(R.id.pdfView);
        pdfView.fromAsset("resume.pdf").load();

        return rootView;
    }

}
