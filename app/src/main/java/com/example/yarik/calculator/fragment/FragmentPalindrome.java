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
import com.example.yarik.calculator.model.Palindrome;

public class FragmentPalindrome extends Fragment {

    private EditText etPalindrome;
    private TextView tvPalindromeAnswer;
    private Palindrome palindrome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_palindrome, container, false);

        etPalindrome = (EditText) rootView.findViewById(R.id.etPalindrome);
        Button btnPalindrome = (Button) rootView.findViewById(R.id.btnPalindrome);
        tvPalindromeAnswer = (TextView) rootView.findViewById(R.id.tvAnswer);

        palindrome = new Palindrome();

        btnPalindrome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (palindrome.isPalindrome(Integer.parseInt(etPalindrome.getText().toString()))){
                    tvPalindromeAnswer.setText("Palindrome");
                }else{
                    tvPalindromeAnswer.setText("Not palindrome");
                }
            }
        });

        return rootView;
    }
}
