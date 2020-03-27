package com.m7amdelbana.instagram.view.auth.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.m7amdelbana.instagram.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUsername;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnRegister;

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
    }

    @Override
    public void onClick(View v) {
        String username = edtUsername.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
    }
}
