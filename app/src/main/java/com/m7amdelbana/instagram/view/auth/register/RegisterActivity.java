package com.m7amdelbana.instagram.view.auth.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.m7amdelbana.instagram.R;
import com.m7amdelbana.instagram.models.User;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUsername;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnRegister;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupView();
    }

    private void setupView() {
        edtUsername = findViewById(R.id.username_editText);
        edtEmail = findViewById(R.id.email_editText);
        edtPassword = findViewById(R.id.password_editText);
        btnRegister = findViewById(R.id.sign_up_button);

        btnRegister.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        String username = edtUsername.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        // TODO: Validation
        User user = new User();
        user.setName(username);
        user.setEmail(email);
        user.setPassword(password);
        createUser(user);
    }

    private void createUser(final User user) {
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            saveUserToDB(firebaseUser.getUid(), user);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void saveUserToDB(String id, User user) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users").child(id);
        user.setPassword(null);
        myRef.setValue(user);

        Toast.makeText(RegisterActivity.this, "Success!",
                Toast.LENGTH_SHORT).show();
        finish();
    }
}
