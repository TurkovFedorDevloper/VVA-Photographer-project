package com.fotographer.vva_foto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonOpenPortfolio(View view) {
        Intent intentPortfolio = new Intent(this, PortfolioActivity.class);
        startActivity(intentPortfolio);
    }

    public void buttonSignIn(View view) {
        Intent intentSignIn = new Intent(this, SignInActivity.class);
        startActivity(intentSignIn);
    }

    public void buttonToRegister(View view) {
        Intent intentToRegistration = new Intent(this, RegistrationActivity.class);
        startActivity(intentToRegistration);
    }
}
