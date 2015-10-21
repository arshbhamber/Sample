package com.example.arsh.sample;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
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
    LinearLayout llInsuranceDetails,llInsuranceHeader;
    FloatingActionButton fabSubmit;
    DatePickerDialog datePickerDialog;
    EditText etKm;
    ImageView ivInsurance,ivKilometers;
    AnimatedDrawable kmAnimatedDrawable,insAnimatedDrawable;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sell_car_insurance,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        parentView = view;
        initViews();
        kmAnimatedDrawable = new AnimatedDrawable(getContext(), R.drawable.kilometers_selected, R.drawable.kilometers_unselected, ivKilometers, 500);
        insAnimatedDrawable  = new AnimatedDrawable(getContext(), R.drawable.insurance_selected, R.drawable.insurance_unselected, ivInsurance, 500);

        ivInsurance.setImageDrawable(insAnimatedDrawable.getDrawable());
        ivKilometers.setImageDrawable(kmAnimatedDrawable.getDrawable());

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

        final float cardWidth = ((RelativeLayout)llInsuranceHeader.getParent()).getHeight()/2 - llInsuranceHeader.getHeight()/2;
        final Animation expandIn = AnimationUtils.loadAnimation(getActivity(), R.anim.expand_in);

        expandIn.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                insAnimatedDrawable.start();
                llInsuranceHeader.animate().translationY(-cardWidth).setDuration(400).setInterpolator(new AccelerateDecelerateInterpolator()).start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        llInsuranceDetails.startAnimation(expandIn);
        llInsuranceDetails.setVisibility(View.VISIBLE);

    }

    private void hideViews() {

        Animation expandOut = AnimationUtils.loadAnimation(getActivity(), R.anim.expand_out);
        expandOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                insAnimatedDrawable.reverse();
                llInsuranceHeader.animate().translationY(0.0f).setDuration(500).setInterpolator(new AccelerateDecelerateInterpolator()).start();

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                llInsuranceDetails.setVisibility(View.GONE);
                ivInsurance.setImageResource(R.drawable.insurance_unselected);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        llInsuranceDetails.startAnimation(expandOut);


    }

    private void setListeners() {

        etKm.addTextChangedListener(this);
        tvDate.setOnClickListener(this);

    }

    private void initViews() {

        llInsuranceDetails = (LinearLayout)parentView.findViewById(R.id.ll_insurance_details);
        switchInsurance = (Switch)parentView.findViewById(R.id.switch_insurance);
        rbComprehensive = (RadioButton)parentView.findViewById(R.id.rb_comprehensive);
        rbthirdParty = (RadioButton)parentView.findViewById(R.id.rb_third_party);
        tvDate = (TextView)parentView.findViewById(R.id.tv_date);
        fabSubmit = (FloatingActionButton)parentView.findViewById(R.id.fab_submit);
        etKm = (EditText)parentView.findViewById(R.id.et_km);
        llInsuranceHeader = (LinearLayout)parentView.findViewById(R.id.ll_insurance_header);
        ivInsurance = (ImageView)parentView.findViewById(R.id.iv_sell_car_insurance);
        ivKilometers = (ImageView)parentView.findViewById(R.id.iv_sell_car_kilometers);


    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if(etKm.getText().toString().length()>0) {

            fabSubmit.setVisibility(View.VISIBLE);
            kmAnimatedDrawable.start();
        }
        else {

            fabSubmit.setVisibility(View.INVISIBLE);
            kmAnimatedDrawable.reverse();
        }

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
