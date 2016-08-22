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
import com.example.yarik.calculator.model.LongestSubList;

public class FragmentLongestSubList extends Fragment {

    private String inputString;
    private EditText etLongestSubList;
    private TextView tvAnswerLongestSubList;
    private LongestSubList longestSubList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_longest_sub_list, container, false);

        etLongestSubList = (EditText) rootView.findViewById(R.id.etLongestSubList);
        Button btnLongestSubList = (Button) rootView.findViewById(R.id.btnLongestSubList);
        tvAnswerLongestSubList = (TextView) rootView.findViewById(R.id.tvAnswerLongestSubList);

        longestSubList = new LongestSubList();

        btnLongestSubList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inputString = etLongestSubList.getText().toString();

                char [] myCharArray = inputString.toCharArray();

                longestSubList.sortedSubList(myCharArray);

                tvAnswerLongestSubList.setText(longestSubList.convertOutArray());
            }
        });

        return rootView;
    }
}

