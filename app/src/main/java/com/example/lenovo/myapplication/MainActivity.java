package com.example.lenovo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lenovo.myapplication.R;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.main_et_username);

        button = (Button) findViewById(R.id.main_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.lenovo.myapplication.FristActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",editText.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



    }
}
