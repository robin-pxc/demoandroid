package com.example.robinpan.demoandroid.Activities.MainActivity;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.example.robinpan.demoandroid.Activities.Base.BaseActivity;
import com.example.robinpan.demoandroid.R;
import com.example.robinpan.demoandroid.dagger.Components.DaggerAppComponent;

import javax.inject.Inject;


public class MainActivity extends BaseActivity implements MainActivityContract.View {

    @Inject
    MainActivityPresenter mainActivityPresenter;

    @LayoutRes
    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void configViews() {
        setActionBarTitle(getString(R.string.main_toolbar_title));
    }

    @Override
    public void inject() {
        DaggerAppComponent.create().inject(this);
    }

    public void setActionBarTitle(String actionBarTitle) {
        getSupportActionBar().setTitle(actionBarTitle);
    }

    @NonNull
    @Override
    public MainActivityPresenter getPresenter() {
        return mainActivityPresenter;
    }
}
