package com.example.robinpan.demoandroid.Activities.Base;

import android.arch.lifecycle.ReportFragment;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;

import com.example.robinpan.demoandroid.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import butterknife.ButterKnife;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ReportFragment.class, ButterKnife.class})
public class BaseActivityTest {

    @Mock
    private AppCompatDelegate mockAppCompatDelegate;

    @Mock
    private BaseActivityPresenter mockBaseActivityPresenter;

    private BaseActivity spyBaseActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        spyBaseActivity = spy(baseActivityInstance());
        doReturn(mockBaseActivityPresenter).when(spyBaseActivity).getPresenter();
    }

    @Test
    public void onCreate() {
        mockStatic(ReportFragment.class);
        mockStatic(ButterKnife.class);
        doReturn(mockAppCompatDelegate).when(spyBaseActivity).getDelegate();
        doReturn(R.layout.unit_test_ready_activity_layout).when(spyBaseActivity).getLayoutResID();

        spyBaseActivity.onCreate(null);

        verify(spyBaseActivity).getLayoutResID();
        verify(spyBaseActivity).configViews();
        verify(spyBaseActivity).setContentView(R.layout.unit_test_ready_activity_layout);
    }

    @Test
    public void getLayoutResID() {
        assertEquals(R.layout.unit_test_ready_activity_layout, spyBaseActivity.getLayoutResID());
    }


    public BaseActivity baseActivityInstance() {
        return new BaseActivity() {
            @LayoutRes
            @Override
            public int getLayoutResID() {
                return R.layout.unit_test_ready_activity_layout;
            }

            @Override
            public void configViews() {

            }

            @Override
            public void inject() {

            }

            @NonNull
            @Override
            public BaseActivityPresenter getPresenter() {
                return null;
            }
        };
    }
}