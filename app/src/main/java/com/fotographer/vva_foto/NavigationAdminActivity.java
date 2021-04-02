package com.fotographer.vva_foto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NavigationAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_admin);

    }

    public void openPortfolio(View view) {

        Intent intentPortfolio = new Intent(this, PortfolioActivity.class);
        startActivity(intentPortfolio);
    }

    public void openAddPhotos (View view) {
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }

    public void openCalendarView(View view) {
        Intent intent = new Intent(this, AddDateCalendarAdmin.class);
        startActivity(intent);
    }
}
