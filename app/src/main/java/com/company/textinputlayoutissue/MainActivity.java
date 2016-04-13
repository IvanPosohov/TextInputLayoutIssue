package com.company.textinputlayoutissue;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout emailTextInputLayout;
    private TextInputEditText emailEditText;
    private TextInputLayout passwordTextInputLayout;
    private TextInputEditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailTextInputLayout = (TextInputLayout) findViewById(R.id.emailTextInputLayout);
        emailEditText = (TextInputEditText) findViewById(R.id.emailEditText);
        emailEditText.addTextChangedListener(emailTextWatcher);
        passwordTextInputLayout = (TextInputLayout) findViewById(R.id.passwordTextInputLayout);
        passwordEditText = (TextInputEditText) findViewById(R.id.passwordEditText);
        passwordEditText.setOnEditorActionListener(onEditorActionListener);
        passwordEditText.addTextChangedListener(passwordTextWatcher);
        findViewById(R.id.loginButton).setOnClickListener(onClickListener);
    }

    private final TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == getResources().getInteger(R.integer.action_sign_in)) {
                login();
                return true;
            }
            return false;
        }
    };

    private final TextWatcher emailTextWatcher = new SimpleTextWatcher() {
        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            emailTextInputLayout.setError(null);
        }
    };

    private final TextWatcher passwordTextWatcher = new SimpleTextWatcher() {
        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            passwordTextInputLayout.setError(null);
        }
    };

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View _view) {
            switch (_view.getId()) {
                case R.id.loginButton:
                    login();
                    break;
            }
        }
    };

    private void login() {
        String email = emailEditText.getText().toString().trim();
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTextInputLayout.setError("Please enter a valid email");
            return;
        }
        String password = passwordEditText.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            passwordTextInputLayout.setError("Please enter password");
            return;
        }
        Toast.makeText(this, "Hooray!", Toast.LENGTH_SHORT).show();
    }
}
