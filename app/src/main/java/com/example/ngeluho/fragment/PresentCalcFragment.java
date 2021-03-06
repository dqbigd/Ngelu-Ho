package com.example.ngeluho.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngeluho.activity.FindTargetActivity;
import com.example.ngeluho.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import me.abhinay.input.CurrencyEditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class PresentCalcFragment extends Fragment {
    private TextView tvHasil, tvPercenYear, tvPercenRate;
    private Button btnHasil, btnFindTarget;
    private CurrencyEditText txtFutureValue;
    private SeekBar sbYear, sbRate;
    private int hasilYear, hasilRate;
    private boolean isPencet = false;
    public static final String EXTRA_HARGA = "harga";

    public PresentCalcFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_present_calc, container, false);

        tvHasil = (TextView) view.findViewById(R.id.tvHasil);
        txtFutureValue = (CurrencyEditText) view.findViewById(R.id.txtPresentValue);
        sbYear = (SeekBar) view.findViewById(R.id.sbYear);
        sbRate = (SeekBar) view.findViewById(R.id.sbRate);
        tvPercenRate = (TextView) view.findViewById(R.id.tvPercenRate);
        tvPercenYear = (TextView) view.findViewById(R.id.tvPercenYear);

        btnHasil = view.findViewById(R.id.btnHitung);
        btnFindTarget = view.findViewById(R.id.btnFindTarget);

        Bundle data = new Bundle();
        data.putString("harga", "");

        settingFormatDuit();

        btnFindTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //isPencet = true;

                //Toast.makeText(getActivity(), String.valueOf(isPencet), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), FindTargetActivity.class);
                startActivity(intent);
            }
        });

        //Toast.makeText(getActivity(), String.valueOf(getArguments().getString("harga")), Toast.LENGTH_SHORT).show();

        //Bundle bundle = this.getArguments();
//        String harga = this.getArguments().getString("harga");
//        Toast.makeText(getActivity(), harga, Toast.LENGTH_SHORT).show();

//        if (getArguments() != null){
//            //Bundle bundle = this.getArguments();
//            String harga = getArguments().getString("harga");
//            int harg = Integer.valueOf(harga);
//
//            Toast.makeText(getActivity(), String.valueOf(harg), Toast.LENGTH_SHORT).show();
//            if (harga == null){
//                Toast.makeText(getActivity(), "null anjer", Toast.LENGTH_SHORT).show();
//            }
//            txtFutureValue.setText(harga);
//            if(!harga.equals("")){
//                txtFutureValue.setText(harg);
//            }
//        }

        sbYear.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String a = String.valueOf(i);
                tvPercenYear.setText(a+" year");
                hasilYear = i;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String a = String.valueOf(i);
                tvPercenRate.setText(a+" %");
                hasilRate = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean kosong = false;

                if(txtFutureValue.getText().toString().matches("") && hasilRate == 0 && hasilYear == 0){
                    Toast.makeText(getActivity(), getResources().getString(R.string.all_required), Toast.LENGTH_SHORT).show();
                    kosong = true;
                } else if(hasilYear == 0){
                    Toast.makeText(getActivity(), getResources().getString(R.string.year_required), Toast.LENGTH_SHORT).show();
                    kosong = true;
                } else if(txtFutureValue.getText().toString().matches("")){
                    Toast.makeText(getActivity(), getResources().getString(R.string.present_value_required), Toast.LENGTH_SHORT).show();
                    kosong = true;
                }else if(hasilRate == 0){
                    Toast.makeText(getActivity(), getResources().getString(R.string.interest_rate_required), Toast.LENGTH_SHORT).show();
                    kosong = true;
                }

                if (kosong == false){
                    double persen,hasl ;
                    double presentValue = txtFutureValue.getCleanDoubleValue();
                    double year = Double.valueOf(hasilYear);
                    double rate = Double.valueOf(hasilRate);

                    persen = rate/100;
                    hasl = presentValue * (1/(Math.pow((1 + persen), year)));

                    NumberFormat formatter = new DecimalFormat("#,###,###");
                    String hsail = "Rp."+formatter.format(hasl);
                    tvHasil.setText(hsail);
                }
            }
        });

        return view;
    }

    private void settingFormatDuit() {
        txtFutureValue.setCurrency("Rp.");
        txtFutureValue.setDelimiter(false);
        txtFutureValue.setSpacing(true);
        txtFutureValue.setDecimals(false);
        txtFutureValue.setSeparator(",");
    }

}
