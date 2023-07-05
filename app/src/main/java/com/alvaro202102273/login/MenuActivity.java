package com.alvaro202102273.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
private Button _mahasiswabutton, _ForexButton;
private Intent _tampilmahasiswaintent, _tampilforexintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        _ForexButton = (Button) findViewById(R.id.ForexButton);
        _ForexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _tampilforexintent = new Intent(getApplicationContext(), ForexActivity.class);
                startActivity(_tampilforexintent);
            }
        });
        _mahasiswabutton = (Button) findViewById(R.id.mahasiswabutton);
        _mahasiswabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _tampilmahasiswaintent = new Intent(getApplicationContext(), MahasiswaActivity.class);
                startActivity(_tampilmahasiswaintent);
            }
        });
    }
}