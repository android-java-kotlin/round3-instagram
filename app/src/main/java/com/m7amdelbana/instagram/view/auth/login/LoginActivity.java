package com.m7amdelbana.instagram.view.auth.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.m7amdelbana.instagram.R;
import com.m7amdelbana.instagram.view.auth.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupView();
    }

    private void setupView() {
        edtEmail = findViewById(R.id.email_editText);
        edtPassword = findViewById(R.id.password_editText);
        btnLogin = findViewById(R.id.sign_in_button);
        tvSignUp = findViewById(R.id.sign_up_textView);

        btnLogin.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                break;
            case R.id.sign_up_textView:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
}
