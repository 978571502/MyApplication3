package com.example.lenovo.myapplication.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.myapplication.Explain;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.WordsBase;

/**
 * Created by Administrator on 2019/4/27.
 */

public class FragmentSelf extends Fragment {

    TextView textView_wordsbase,textView_explain;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_self,null);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView_wordsbase = view.findViewById(R.id.self_tv_words);
        textView_explain = view.findViewById(R.id.self_tv_explain);
        //点击单词库
        textView_wordsbase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WordsBase.class);
                startActivity(intent);
            }
        });
        //点击软件说明
        textView_explain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Explain.class);
                startActivity(intent);
            }
        });
    }
}
