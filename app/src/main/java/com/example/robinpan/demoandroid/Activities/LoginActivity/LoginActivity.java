package com.example.robinpan.demoandroid.Activities.LoginActivity;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

import com.example.robinpan.demoandroid.Activities.Base.BaseActivity;
import com.example.robinpan.demoandroid.Activities.Base.BaseActivityPresenter;
import com.example.robinpan.demoandroid.Activities.MainActivity.MainActivity;
import com.example.robinpan.demoandroid.R;
import com.example.robinpan.demoandroid.dagger.Components.DaggerAppComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.robinpan.demoandroid.CommonUtils.StringUtils.RegexMatcher.PASSWORD_REGEX;
import static com.example.robinpan.demoandroid.CommonUtils.StringUtils.RegexMatcher.USERNAME_REGEX;

public class LoginActivity extends BaseActivity implements LoginActivityContract.View {

    @BindView(R.id.login_username_text_input_edit_text)
    TextInputEditText mLoginUsernameTextInputEditText;

    @BindView(R.id.login_password_text_input_edit_text)
    TextInputEditText mLoginPasswordTextInputEditText;

    @BindView(R.id.login_username_input_layout)
    TextInputLayout mUsernameInputLayout;

    @BindView(R.id.login_password_input_layout)
    TextInputLayout mPasswordInputLayout;

    @BindView(R.id.login_button)
    Button mLoginButton;

    @Inject
    LoginActivityPresenter loginActivityPresenter;

    @LayoutRes
    @Override
    public int getLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    public void configViews() {
        getSupportActionBar().setTitle(getString(R.string.login_toolbar_title));
        mLoginUsernameTextInputEditText.requestFocus();
        mLoginUsernameTextInputEditText.addTextChangedListener(new LoginTextWatcher(USERNAME_REGEX));
        mLoginPasswordTextInputEditText.addTextChangedListener(new LoginTextWatcher(PASSWORD_REGEX));
    }

    @Override
    public void inject() {
        DaggerAppComponent.create().inject(this);
    }

    @NonNull
    @Override
    public BaseActivityPresenter getPresenter() {
        return loginActivityPresenter;
    }

    @Override
    public void setUsernameError() {
        mUsernameInputLayout.setError(getString(R.string.login_username_error));
    }

    @Override
    public void removeUsernameError() {
        mUsernameInputLayout.setErrorEnabled(false);
    }

    @Override
    public void setPasswordError() {
        mPasswordInputLayout.setError(getString(R.string.login_password_error));
    }

    @Override
    public void removePasswordError() {
        mPasswordInputLayout.setErrorEnabled(false);
    }

    @Override
    public boolean availableToLogin() {
        return !(mUsernameInputLayout.isErrorEnabled() || mPasswordInputLayout.isErrorEnabled());
    }

    @Override
    public void enableLoginButton() {
        mLoginButton.setEnabled(true);
    }

    @Override
    public void disableLoginButton() {
        mLoginButton.setEnabled(false);
    }

    @OnClick(R.id.login_button)
    public void onLoginButtonClick() {
        MainActivity.launch(this, mLoginUsernameTextInputEditText.getText().toString());
    }

    class LoginTextWatcher implements TextWatcher {
        String regex;

        public LoginTextWatcher(String regex) {
            this.regex = regex;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String newText = s.toString();
            loginActivityPresenter.verifyInputText(newText, regex);
        }
    }

}
