package com.example.robinpan.demoandroid.Activities.MainActivity;

import com.example.robinpan.demoandroid.Activities.Base.BaseView;

public interface MainActivityContract {

    interface View extends BaseView {
        MainActivityPresenter getPresenter();
    }

    interface Presenter {

    }
}
