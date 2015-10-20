package com.example.arsh.sample;

import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.Switch;
import android.widget.TextView;

import java.lang.ref.WeakReference;
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
    ImageView ivInsurance,ivKilometers;
    RevealDrawable customDrawable;
    ValueAnimator animator;
    Drawable d1;
    Drawable d2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sell_car_insurance,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        animator = ValueAnimator.ofFloat(0.0f,1.0f);
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//               d1 = d1.getConstantState().newDrawable();
//                d2 = d2.getConstantState().newDrawable();
                customDrawable = new RevealDrawable(d1, d2, 1);
                ivInsurance.setImageDrawable(customDrawable);
                customDrawable.setLevel((int) (animation.getAnimatedFraction() * 5000.0f));


            }
        });


        d1 = getResources().getDrawable(R.drawable.insurance_unselected);
        d2 = getResources().getDrawable(R.drawable.insurance_selected);


        customDrawable = new RevealDrawable(d1, d2, 1);





        parentView = view ;
        initViews();
        setListeners();

        ivInsurance.setImageDrawable(customDrawable);
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





        final Animation expandIn = AnimationUtils.loadAnimation(getActivity(), R.anim.expand_in);


        expandIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

//                customDrawable = new RevealDrawable(d1,d2,1);
                animator.start();
                llInsuranceHeader.animate().translationY(0.0f).setDuration(400).setInterpolator(new AccelerateDecelerateInterpolator()).start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

//                animator.cancel();
//                ivInsurance.setImageResource(R.drawable.insurance_selected);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

//        llInsuranceHeader.startAnimation(animation);
        llInsuranceDetails.startAnimation(expandIn);
        llInsuranceDetails.setVisibility(View.VISIBLE);
//                llKilometerValue.setVisibility(View.VISIBLE);

//        ivKilometers.setImageResource(R.drawable.kilometers_selected);
//        llInsuranceHeader.animate().translationY(-100.0f).setDuration(400).setInterpolator(new AccelerateDecelerateInterpolator()).start();

    }

    private void hideViews() {

        final float cardWidth = ((LinearLayout)llInsuranceHeader.getParent()).getHeight()/2 - llInsuranceHeader.getHeight()/2;

        Animation expandOut = AnimationUtils.loadAnimation(getActivity(), R.anim.expand_out);
        expandOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

//                customDrawable = new RevealDrawable(d1,d2,1);
                animator.reverse();
                llInsuranceHeader.animate().translationY(cardWidth).setDuration(500).setInterpolator(new AccelerateDecelerateInterpolator()).start();

            }

            @Override
            public void onAnimationEnd(Animation animation) {

//                animator.cancel();
                llInsuranceDetails.setVisibility(View.GONE);
                ivInsurance.setImageResource(R.drawable.insurance_unselected);
//                ivKilometers.setImageResource(R.drawable.kilometers_unselected);
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
        llKilometerValue = (LinearLayout)parentView.findViewById(R.id.ll_km_value);
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
            ivKilometers.setImageResource(R.drawable.kilometers_selected);
        }
        else {

            fabSubmit.setVisibility(View.INVISIBLE);
            ivKilometers.setImageResource(R.drawable.kilometers_unselected);
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
