package com.fotographer.vva_foto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.fotographer.vva_foto.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TestFireBaseGetDataUser extends AppCompatActivity {
    User userIntent;
    TextView eMail, name, surName, phone;
    String mailIntent, nameIntent, surNameIntent, phoneIntent, userRes;
    private DatabaseReference users;
    private FirebaseDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fire_base_get_data_user);

        eMail = findViewById(R.id.emailTestFB);
        name = findViewById(R.id.nameTestFB);
        surName = findViewById(R.id.surnameTestFB);
        phone = findViewById(R.id.telephoneNamberTestFB);

        userRes = getIntent().getStringExtra("userRes");

        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot user : dataSnapshot.getChildren()) {

                    if (user.getKey().equals(userRes)) {
                        userIntent = user.getValue(User.class);

                        assert userIntent != null;
                        mailIntent = userIntent.getEmail();
                        nameIntent = userIntent.getName();
                        surNameIntent = userIntent.getSurname();
                        phoneIntent = userIntent.getPhone();

                        eMail.setText(mailIntent);
                        name.setText(nameIntent);
                        surName.setText(surNameIntent);
                        phone.setText(phoneIntent);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
