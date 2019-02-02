package com.example.robinpan.demoandroid.dagger.Components;

import com.example.robinpan.demoandroid.Activities.LoginActivity.LoginActivity;
import com.example.robinpan.demoandroid.Activities.MainActivity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component
public interface AppComponent {
    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);
}
