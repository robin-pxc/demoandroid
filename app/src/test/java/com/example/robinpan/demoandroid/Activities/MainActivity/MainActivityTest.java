package com.example.robinpan.demoandroid.Activities.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;

import com.example.robinpan.demoandroid.R;
import com.example.robinpan.demoandroid.dagger.Components.DaggerAppComponent;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DaggerAppComponent.class, Intent.class})
public class MainActivityTest {
    @Mock
    private DaggerAppComponent mockDaggerAppComponent;

    @Mock
    private ActionBar mockSupportActionbar;

    @Mock
    private MainActivityPresenter mockPresenter;

    private MainActivity spyMainActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(DaggerAppComponent.class);
        spyMainActivity = spy(new MainActivity());

        spyMainActivity.mainActivityPresenter = mockPresenter;
    }

    @Test
    public void testLaunch() {
        //Todo: update this test
        MainActivity.launch(spyMainActivity, "username");
        verify(spyMainActivity).startActivity(any(Intent.class));
    }

    @Test
    public void testGetLayoutResID() {
        assertEquals(R.layout.activity_main, spyMainActivity.getLayoutResID());
    }

    @Test
    public void testConfigViews() {
        String actionBarTitleText = "Perficient";
        doReturn(actionBarTitleText).when(spyMainActivity).getString(R.string.main_toolbar_title);
        doNothing().when(spyMainActivity).setActionBarTitle(actionBarTitleText);

        spyMainActivity.configViews();

        verify(spyMainActivity).setActionBarTitle(actionBarTitleText);
    }

    @Test
    public void testInject() {
        PowerMockito.when(DaggerAppComponent.create()).thenReturn(mockDaggerAppComponent);

        spyMainActivity.inject();

        verify(mockDaggerAppComponent).inject(spyMainActivity);
    }

    @Test
    public void testSetActionBarTitle() {
        String actionBarTitleText = "title123";
        doReturn(mockSupportActionbar).when(spyMainActivity).getSupportActionBar();

        spyMainActivity.setActionBarTitle(actionBarTitleText);

        verify(mockSupportActionbar).setTitle(actionBarTitleText);
    }

    @Test
    public void getPresenter() {
        assertEquals(mockPresenter, spyMainActivity.getPresenter());
    }
}