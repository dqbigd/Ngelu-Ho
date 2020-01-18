package com.example.ngeluho.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngeluho.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import me.abhinay.input.CurrencyEditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FutureCalcFragment extends Fragment {
    private TextView tvHasil, tvPercenYear, tvPercenRate, tvProfit;
    private Button btnHasil;
    private CurrencyEditText txtPresentValue;
    private SeekBar sbYear, sbRate;
    private int hasilYear, hasilRate;

    public FutureCalcFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_future_calc, container, false);

        tvHasil = (TextView) view.findViewById(R.id.tvHasil);
        tvProfit = (TextView) view.findViewById(R.id.tvProfit);
        txtPresentValue = (CurrencyEditText) view.findViewById(R.id.txtPresentValue);
        sbYear = (SeekBar) view.findViewById(R.id.sbYear);
        sbRate = (SeekBar) view.findViewById(R.id.sbRate);
        tvPercenRate = (TextView) view.findViewById(R.id.tvPercenRate);
        tvPercenYear = (TextView) view.findViewById(R.id.tvPercenYear);

        btnHasil = view.findViewById(R.id.btnHitung);

        settingFormatDuit();

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

                if(txtPresentValue.getText().toString().matches("") && hasilRate == 0 && hasilYear == 0){
                    Toast.makeText(getActivity(), getResources().getString(R.string.all_required), Toast.LENGTH_SHORT).show();
                    kosong = true;
                } else if(hasilYear == 0){
                    Toast.makeText(getActivity(), getResources().getString(R.string.year_required), Toast.LENGTH_SHORT).show();
                    kosong = true;
                } else if(txtPresentValue.getText().toString().matches("")){
                    Toast.makeText(getActivity(), getResources().getString(R.string.present_value_required), Toast.LENGTH_SHORT).show();
                    kosong = true;
                }else if(hasilRate == 0){
                    Toast.makeText(getActivity(), getResources().getString(R.string.interest_rate_required), Toast.LENGTH_SHORT).show();
                    kosong = true;
                }

                if (kosong == false){
                    double persen,hasl, profitt ;
                    double futureValue = txtPresentValue.getCleanDoubleValue();
                    double year = Double.valueOf(hasilYear);
                    double rate = Double.valueOf(hasilRate);


                    persen = rate/100;
                    hasl = futureValue * (Math.pow((1 + persen), year));

                    profitt = hasl - futureValue;

                    NumberFormat formatter = new DecimalFormat("#,###,###");
                    String hsail = "Rp."+formatter.format(hasl);
                    String profit = "Rp. "+formatter.format(profitt);

                    tvProfit.setText(profit);
                    tvHasil.setText(hsail);
                }
            }
        });

        return view;
    }


    private void settingFormatDuit() {
        txtPresentValue.setCurrency("Rp.");
        txtPresentValue.setDelimiter(false);
        txtPresentValue.setSpacing(true);
        txtPresentValue.setDecimals(false);
        txtPresentValue.setSeparator(",");
    }

}
