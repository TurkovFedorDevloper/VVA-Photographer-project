package com.fotographer.vva_foto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button btnSingIn;

    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private DatabaseReference users;

    private EditText email, password;
    private LinearLayout rootMA;

    private static String EMAIL_ADMIN = "admin@admin.admin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSingIn = findViewById(R.id.signInButton);
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        email = findViewById(R.id.loginSignIn);
        password = findViewById(R.id.passWordSignIn);
        rootMA = findViewById(R.id.rootMA);
    }

    public void openPortfolio(View view) {

        Intent intentPortfolio = new Intent(this, PortfolioActivity.class);
        startActivity(intentPortfolio);
    }

    public void signInButton(View view) {

        if (TextUtils.isEmpty(email.getText().toString())) {
            Snackbar.make(rootMA, "Введите почту", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password.getText().toString())) {
            Snackbar.make(rootMA, "Введите пароль", Snackbar.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if (email.getText().toString().equals(EMAIL_ADMIN)) {
                            startActivity(new Intent(MainActivity.this, AdminActivity.class));
                        } else {
                            startActivity(new Intent(MainActivity.this, UserTestAcaunt.class));
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(rootMA, "Ошибка авторизации\n" + e.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public void toRegister(View view) {
        Intent intentToRegistration = new Intent(this, RegistrationActivity.class);
        startActivity(intentToRegistration);
    }

    public void setEmailAdmin(String emailAdmin) {
        EMAIL_ADMIN = emailAdmin;
    }
}
