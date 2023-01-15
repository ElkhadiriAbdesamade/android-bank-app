package com.example.bankapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangeLanguagFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangeLanguagFragment extends Fragment {

    TextView icon_lang,icon_close;
    RelativeLayout lang_fragment;
  EditText txtusername;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChangeLanguagFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChangeLanguagFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChangeLanguagFragment newInstance(String param1, String param2) {
        ChangeLanguagFragment fragment = new ChangeLanguagFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_languag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

     /*   for (int btn_lg = 0; btn_lg < 3; btn_lg++) {
            int idView=getResources().getIdentifier("btn"+ btn_lg,"id",getContext().getPackageName());
            View eventView=view.findViewById(idView);
            eventView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "English", Toast.LENGTH_SHORT).show();
                }
            });
        }*/

        int btn_eng_id=getResources().getIdentifier("btn_eng","id",getContext().getPackageName());
        View btn_eng=view.findViewById(btn_eng_id);
        btn_eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("en");
                Toast.makeText(getContext(), "English", Toast.LENGTH_SHORT).show();
                restart();
            }
        });

        int btn_fr_id=getResources().getIdentifier("btn_fr","id",getContext().getPackageName());
        View btn_fr=view.findViewById(btn_fr_id);
        btn_fr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("fr");
                Toast.makeText(getContext(), "france", Toast.LENGTH_SHORT).show();
                restart();
            }
        });

        int btn_ar_id=getResources().getIdentifier("btn_ar","id",getContext().getPackageName());
        View btn_ar=view.findViewById(btn_ar_id);
        btn_ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("ar");
                Toast.makeText(getContext(), "arab", Toast.LENGTH_SHORT).show();
                restart();
            }
        });
    }

    private void setLocale(String lang) {
        Resources resources=getResources();
        DisplayMetrics metrics=resources.getDisplayMetrics();
        Configuration configuration=resources.getConfiguration();
        configuration.locale=new Locale(lang);
        resources.updateConfiguration(configuration,metrics);
        onConfigurationChanged(configuration);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
       // txtusername=getActivity().findViewById(R.id.txtusername);
        //txtusername.setHint(R.string.user_name);
    }

    public void restart(){
        Intent intent = new Intent(getContext(), MainActivity.class);
        getActivity().startActivity(intent);
        //getActivity().finishAffinity();
    }



    @Override
    public void onResume() {
        super.onResume();


    }
}