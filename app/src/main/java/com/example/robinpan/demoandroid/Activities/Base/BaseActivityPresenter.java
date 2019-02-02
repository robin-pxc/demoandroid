package com.example.robinpan.demoandroid.Activities.Base;


import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class BaseActivityPresenter<View extends BaseView> {

    @Nullable
    public View view;

    public BaseActivityPresenter() {

    }

    public void setView(@Nullable View view) {
        this.view = view;
    }

    protected void setup(@Nullable Bundle arguments) {
    }

    public void onCreate(@Nullable Bundle arguments, @Nullable View view) {
        setup(arguments);
        setView(view);
    }
}
