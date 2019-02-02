package com.example.robinpan.demoandroid.Activities.LoginActivity;

import com.example.robinpan.demoandroid.Activities.Base.BaseActivityPresenter;
import com.example.robinpan.demoandroid.CommonUtils.StringUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LoginActivityPresenter extends BaseActivityPresenter<LoginActivityContract.View> {

    @Inject
    public LoginActivityPresenter() {

    }

    void verifyInputText(String inputText, String regex) {
        if (view != null) {
            switch (regex) {
                case StringUtils.RegexMatcher.USERNAME_REGEX:
                    if (!StringUtils.RegexMatcher.isMatch(inputText, regex)) {
                        view.setUsernameError();
                    } else {
                        view.removeUsernameError();
                    }
                    break;
                case StringUtils.RegexMatcher.PASSWORD_REGEX:
                    if (!StringUtils.RegexMatcher.isMatch(inputText, regex)) {
                        view.setPasswordError();
                    } else {
                        view.removePasswordError();
                    }
                    break;
            }

            if (view.availableToLogin()) {
                view.enableLoginButton();
            } else {
                view.disableLoginButton();
            }
        }
    }
}
