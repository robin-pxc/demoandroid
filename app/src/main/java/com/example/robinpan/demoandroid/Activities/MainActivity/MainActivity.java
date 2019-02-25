package com.example.robinpan.demoandroid.Activities.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.example.robinpan.demoandroid.Activities.Base.BaseActivity;
import com.example.robinpan.demoandroid.R;
import com.example.robinpan.demoandroid.dagger.Components.DaggerAppComponent;

import javax.inject.Inject;

import butterknife.BindView;

import static com.example.robinpan.demoandroid.constants.AppConstants.Intent.MAIN_ACTIVITY_USERNAME_KEY;


public class MainActivity extends BaseActivity implements MainActivityContract.View {

    public static void launch(Context context, String username) {
        Intent mainActivityIntent = new Intent(context, MainActivity.class);
        mainActivityIntent.putExtra(MAIN_ACTIVITY_USERNAME_KEY, username);
        mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mainActivityIntent);
    }

    @Inject
    MainActivityPresenter mainActivityPresenter;

    @BindView(R.id.main_activity_drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    @LayoutRes
    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void configViews() {
        setActionBarTitle(getString(R.string.main_toolbar_title));
        setNavigationMenu();
    }

    void setNavigationMenu() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        break;
                    case R.id.nav_books:
                        break;
                    case R.id.nav_weather:
                        break;
                    case R.id.sign_out:
                        break;
                }

                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return false;
            }
        });
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
