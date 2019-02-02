package com.example.robinpan.demoandroid.Activities.Base;

import android.os.Bundle;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class BaseActivityPresenterTest {

    @Mock
    private BaseView mockView;

    @Mock
    private Bundle mockArguments;

    private BaseActivityPresenter spyPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        spyPresenter = spy(BaseActivityPresenter.class);
    }

    @Test
    public void testSetView() {
        spyPresenter.setView(mockView);

        assertEquals(mockView, spyPresenter.view);
    }

    @Test
    public void testOnCreate() {
        spyPresenter.onCreate(mockArguments, mockView);

        verify(spyPresenter).setView(mockView);
        verify(spyPresenter).setup(mockArguments);
    }
}