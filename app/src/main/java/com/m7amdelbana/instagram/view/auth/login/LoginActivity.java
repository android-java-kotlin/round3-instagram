package com.m7amdelbana.instagram.view.auth.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.m7amdelbana.instagram.R;
import com.m7amdelbana.instagram.models.User;
import com.m7amdelbana.instagram.utils.Utilities;
import com.m7amdelbana.instagram.view.auth.register.RegisterActivity;
import com.m7amdelbana.instagram.view.main.MainActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private TextView tvSignUp;

    private FirebaseAuth mAuth;
    private FirebaseAnalytics mFirebaseAnalytics;

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

        mAuth = FirebaseAuth.getInstance();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                // TODO: Validation
                signIn(email, password);

                break;
            case R.id.sign_up_textView:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    private void signIn(final String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();

                            logUserEvent(email);

                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Wrong email and password!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void logUserEvent(String email) {
        Bundle bundle = new Bundle();
        bundle.putString("user_email", email);
        bundle.putString("user_login_date", Utilities.getCurrentDate());
        bundle.putString("type", "login");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle);
    }
}
