package com.example.robinpan.demoandroid.Activities.Base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity<Presenter extends BaseActivityPresenter> extends AppCompatActivity implements BaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        setContentView(getLayoutResID());
        ButterKnife.bind(this);
        configViews();
        getPresenter().onCreate(savedInstanceState,this);
    }

    @LayoutRes
    public abstract int getLayoutResID();
    public abstract void configViews();
    public abstract void inject();

    @NonNull
    public abstract Presenter getPresenter();
}
