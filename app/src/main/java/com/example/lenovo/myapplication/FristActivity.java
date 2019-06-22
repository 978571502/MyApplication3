package com.example.lenovo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.myapplication.database.DBOpenHelper;
import com.example.lenovo.myapplication.fragments.FragmentInput;
import com.example.lenovo.myapplication.fragments.FragmentRecite;
import com.example.lenovo.myapplication.fragments.FragmentSelf;

public class FristActivity extends AppCompatActivity {
    TextView name;
    Button button_input,button_recite,button_self;
    FragmentInput fragmentInput;
    FragmentRecite fragmentRecite;
    FragmentSelf fragmentSelf;
    DBOpenHelper dbOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        button_input = (Button) findViewById(R.id.frist_btn_input);
        button_recite = (Button) findViewById(R.id.frist_btn_recite);
        button_self = (Button) findViewById(R.id.frist_btn_self);

        name = (TextView) findViewById(R.id.name);

        fragmentInput = new FragmentInput();
        fragmentRecite = new FragmentRecite();
        fragmentSelf = new FragmentSelf();

        dbOpenHelper = new DBOpenHelper(FristActivity.this,"tb_dict",null,1);

        //获取ID
        Bundle buddle = getIntent().getExtras();
        name.setText(buddle.getString("name"));
        //点击录入按钮
        button_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.first_fl,fragmentInput).commitAllowingStateLoss();
            }
        });

        //点击背诵按钮
        button_recite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.first_fl,fragmentRecite).commitAllowingStateLoss();

            }
        });

        //点击个人按钮
        button_self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.first_fl,fragmentSelf).commitAllowingStateLoss();
            }
        });

        //默认录入界面
        getFragmentManager().beginTransaction().add(R.id.first_fl,fragmentInput).commitAllowingStateLoss();
    }

}
