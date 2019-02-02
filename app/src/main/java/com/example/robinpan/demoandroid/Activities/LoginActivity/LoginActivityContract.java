package com.example.robinpan.demoandroid.Activities.LoginActivity;

import com.example.robinpan.demoandroid.Activities.Base.BaseView;

public interface LoginActivityContract {

    interface View extends BaseView {
        void setUsernameError();

        void removeUsernameError();

        void setPasswordError();

        void removePasswordError();

        boolean availableToLogin();

        void enableLoginButton();

        void disableLoginButton();
    }
}
