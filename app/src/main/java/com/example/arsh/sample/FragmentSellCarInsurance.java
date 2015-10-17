package com.example.arsh.sample;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by arsh on 15/10/15.
 */
public class FragmentSellCarInsurance extends Fragment implements TextWatcher, View.OnClickListener{


    Switch switchInsurance;
    RadioButton rbComprehensive, rbthirdParty;
    View parentView;
    TextView tvDate;
    LinearLayout llInsuranceDetails,llKilometerValue,llInsuranceHeader;
    FloatingActionButton fabSubmit;
    DatePickerDialog datePickerDialog;
    EditText etKm;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sell_car_insurance,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        parentView = view ;
        initViews();
        setListeners();

        switchInsurance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    showViews();
                else
                    hideViews();

            }
        });

        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                tvDate.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
            }
        }, Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH);

    }

    private void showViews() {

        Animation expandIn = AnimationUtils.loadAnimation(getActivity(), R.anim.expand_in);
        expandIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        llInsuranceDetails.startAnimation(expandIn);
        llKilometerValue.startAnimation(expandIn);
        llInsuranceDetails.setVisibility(View.VISIBLE);
        llKilometerValue.setVisibility(View.VISIBLE);
    }

    private void hideViews() {

        Animation expandOut = AnimationUtils.loadAnimation(getActivity(), R.anim.expand_out);
        expandOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                llInsuranceDetails.setVisibility(View.GONE);
                llKilometerValue.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        llInsuranceDetails.startAnimation(expandOut);
        llKilometerValue.startAnimation(expandOut);



    }

    private void setListeners() {

        etKm.addTextChangedListener(this);
        tvDate.setOnClickListener(this);

    }

    private void initViews() {

        llInsuranceDetails = (LinearLayout)parentView.findViewById(R.id.ll_insurance_details);
        llKilometerValue = (LinearLayout)parentView.findViewById(R.id.ll_km_value);
        switchInsurance = (Switch)parentView.findViewById(R.id.switch_insurance);
        rbComprehensive = (RadioButton)parentView.findViewById(R.id.rb_comprehensive);
        rbthirdParty = (RadioButton)parentView.findViewById(R.id.rb_third_party);
        tvDate = (TextView)parentView.findViewById(R.id.tv_date);
        fabSubmit = (FloatingActionButton)parentView.findViewById(R.id.fab_submit);
        etKm = (EditText)parentView.findViewById(R.id.et_km);
        llInsuranceHeader = (LinearLayout)parentView.findViewById(R.id.ll_insurance_header);


    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if(tvDate.getText().toString().length()>0)
            fabSubmit.setVisibility(View.VISIBLE);
        else
            fabSubmit.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tv_date :
                datePickerDialog.show();
                break;



        }

    }
}
