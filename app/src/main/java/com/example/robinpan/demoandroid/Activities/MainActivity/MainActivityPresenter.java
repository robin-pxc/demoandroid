package com.example.robinpan.demoandroid.Activities.MainActivity;

import com.example.robinpan.demoandroid.Activities.Base.BaseActivityPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MainActivityPresenter extends BaseActivityPresenter<MainActivityContract.View> {

    @Inject
    MainActivityPresenter() {

    }
}
