package com.example.yarik.calculator.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yarik.calculator.R;
import com.example.yarik.calculator.model.FactorialDigitSum;

public class FragmentFactorialDigitSum extends Fragment {

    private EditText etFactorialDigitSum;
    private TextView tvAnswerFactorialDigitSum;
    private FactorialDigitSum factorialDigitSum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_factorial_digit_sum, container, false);

        etFactorialDigitSum = (EditText) rootView.findViewById(R.id.etFactorialDigitSum);
        Button btnFactorialDigitSum = (Button) rootView.findViewById(R.id.btnFactorialDigitSum);
        tvAnswerFactorialDigitSum = (TextView) rootView.findViewById(R.id.tvAnswerFactorialDigitSum);

        factorialDigitSum = new FactorialDigitSum();

        btnFactorialDigitSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Integer digitSum = factorialDigitSum.calculate(Integer.parseInt(etFactorialDigitSum.getText().toString()));
                tvAnswerFactorialDigitSum.setText(digitSum.toString());
            }
        });

        return rootView;
    }
}
