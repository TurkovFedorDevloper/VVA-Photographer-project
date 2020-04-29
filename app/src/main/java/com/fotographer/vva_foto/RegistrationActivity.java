package com.fotographer.vva_foto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fotographer.vva_foto.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {

    private Button btnRegister;
    private FirebaseDatabase db;

    private FirebaseAuth auth;
    private DatabaseReference users;

    private EditText name, surname, email, password, phone;
    private ConstraintLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        btnRegister = findViewById(R.id.userRegistrationButton);
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        name = findViewById(R.id.nameRegistration);
        surname = findViewById(R.id.surnaimRegistration);
        email = findViewById(R.id.emailRegistration);
        password = findViewById(R.id.passWordRegistration);
        phone = findViewById(R.id.phoneRegistration);
        root = findViewById(R.id.root_element);
        auth = FirebaseAuth.getInstance();
    }

    public void registrationUser(View view) {
        if (TextUtils.isEmpty(name.getText().toString())){
            Snackbar.make(root, "Введите имя", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(surname.getText().toString())){
            Snackbar.make(root, "Введите фамилию", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(email.getText().toString())){
            Snackbar.make(root, "Введите почту", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password.getText().toString()) || password.getText().toString().length() < 6){
            Snackbar.make(root, "Введите пароль более 6 символов", Snackbar.LENGTH_SHORT).show();

            return;
        }
        if (TextUtils.isEmpty(phone.getText().toString())){
            Snackbar.make(root, "Введите телефон", Snackbar.LENGTH_SHORT).show();
            return;
        }

        //Регистрация пользователя
        auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        User user = new User();
                        user.setName(name.getText().toString());
                        user.setSurname(surname.getText().toString());
                        user.setEmail(email.getText().toString());
                        user.setPassword(password.getText().toString());
                        user.setPhone(phone.getText().toString());

                        users.child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                .setValue(user)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Snackbar.make(root, "Регистрация прошла успешно!", Snackbar.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegistrationActivity.this, UserActivity.class));
                                    }
                                });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(root, "Поля заполнены некорректно\n" + e.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
