package com.example.robinpan.demoandroid.Activities.LoginActivity;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.widget.Button;

import com.example.robinpan.demoandroid.CommonUtils.StringUtils;
import com.example.robinpan.demoandroid.R;
import com.example.robinpan.demoandroid.dagger.Components.DaggerAppComponent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DaggerAppComponent.class})
public class LoginActivityTest {

    private LoginActivity spyLoginActivity;

    @Mock
    private ActionBar mockSupportActionBar;

    @Mock
    private DaggerAppComponent mockDaggerAppComponent;

    @Mock
    private LoginActivityPresenter mockLoginPresenter;

    @Mock
    private TextInputEditText mockLoginUsernameTextInputEditText;

    @Mock
    private TextInputEditText mockLoginPasswordTextInputEditText;

    @Mock
    private TextInputLayout mockLoginUsernameTextInputLayout;

    @Mock
    private TextInputLayout mockLoginPasswordTextInputLayout;

    @Mock
    private Button mockLoginButton;

    private LoginActivity.LoginTextWatcher spyLoginTextWatcher;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(DaggerAppComponent.class);
        spyLoginActivity = spy(new LoginActivity());

        spyLoginActivity.loginActivityPresenter = mockLoginPresenter;
        spyLoginActivity.mUsernameInputLayout = mockLoginUsernameTextInputLayout;
        spyLoginActivity.mLoginUsernameTextInputEditText = mockLoginUsernameTextInputEditText;
        spyLoginActivity.mPasswordInputLayout = mockLoginPasswordTextInputLayout;
        spyLoginActivity.mLoginPasswordTextInputEditText = mockLoginPasswordTextInputEditText;
        spyLoginActivity.mLoginButton = mockLoginButton;

        spyLoginTextWatcher = spyLoginActivity.new LoginTextWatcher(StringUtils.RegexMatcher.USERNAME_REGEX);
    }

    @Test
    public void testGetLayoutResID() {
        assertEquals(R.layout.activity_login, spyLoginActivity.getLayoutResID());
    }

    @Test
    public void testConfigViews() {
        String loginTitle = "Welcome";
        doReturn(mockSupportActionBar).when(spyLoginActivity).getSupportActionBar();
        doReturn(loginTitle).when(spyLoginActivity).getString(R.string.login_toolbar_title);

        spyLoginActivity.configViews();

        verify(mockSupportActionBar).setTitle(loginTitle);
        verify(mockLoginUsernameTextInputEditText).requestFocus();
    }

    @Test
    public void testInject() {
        PowerMockito.when(DaggerAppComponent.create()).thenReturn(mockDaggerAppComponent);

        spyLoginActivity.inject();

        verify(mockDaggerAppComponent).inject(spyLoginActivity);
    }

    @Test
    public void testGetPresenter() {
        assertEquals(mockLoginPresenter, spyLoginActivity.getPresenter());
    }

    @Test
    public void testSetUsernameError() {
        String errorMsg = "Your username should be 5 – 16 length, contains letters, numbers, dash, and underlines.";
        doReturn(errorMsg).when(spyLoginActivity).getString(R.string.login_username_error);

        spyLoginActivity.setUsernameError();

        verify(mockLoginUsernameTextInputLayout).setError(errorMsg);
    }

    @Test
    public void testRemoveUsernameError() {
        spyLoginActivity.removeUsernameError();

        verify(mockLoginUsernameTextInputLayout).setErrorEnabled(false);
    }

    @Test
    public void testSetPasswordError() {
        String errorMsg = "Your password should be 6 – 20 length, contains letters(at least 1 capital letter)and numbers (NO special characters).";

        doReturn(errorMsg).when(spyLoginActivity).getString(R.string.login_password_error);
        spyLoginActivity.setPasswordError();
        verify(mockLoginPasswordTextInputLayout).setError(errorMsg);
    }

    @Test
    public void testRemovePasswordError() {
        spyLoginActivity.removePasswordError();

        verify(mockLoginPasswordTextInputLayout).setErrorEnabled(false);
    }

    @Test
    public void testAvailableToLoginWhenConditionsMatched() {
        doReturn(false).when(mockLoginUsernameTextInputLayout).isErrorEnabled();
        doReturn(false).when(mockLoginPasswordTextInputLayout).isErrorEnabled();

        assertTrue(spyLoginActivity.availableToLogin());
    }

    @Test
    public void testAvailableToLoginWhenUsernameConditionsNotMatched() {
        doReturn(true).when(mockLoginUsernameTextInputLayout).isErrorEnabled();
        doReturn(false).when(mockLoginPasswordTextInputLayout).isErrorEnabled();

        assertFalse(spyLoginActivity.availableToLogin());
    }

    @Test
    public void testAvailableToLoginWhenPasswordConditionsNotMatched() {
        doReturn(false).when(mockLoginUsernameTextInputLayout).isErrorEnabled();
        doReturn(true).when(mockLoginPasswordTextInputLayout).isErrorEnabled();

        assertFalse(spyLoginActivity.availableToLogin());
    }

    @Test
    public void testAvailableToLoginWhenBothConditionsNotMatched() {
        doReturn(true).when(mockLoginUsernameTextInputLayout).isErrorEnabled();
        doReturn(true).when(mockLoginPasswordTextInputLayout).isErrorEnabled();

        assertFalse(spyLoginActivity.availableToLogin());
    }

    @Test
    public void testEnableLoginButton() {
        spyLoginActivity.enableLoginButton();

        verify(mockLoginButton).setEnabled(true);
    }

    @Test
    public void testDisableLoginButton() {
        spyLoginActivity.disableLoginButton();

        verify(mockLoginButton).setEnabled(false);
    }

    @Test
    public void testLoginTextWatcherBeforeTextChanged() {
        CharSequence charSequence = "abc123";
        spyLoginTextWatcher.beforeTextChanged(charSequence, 0, charSequence.length(), 0);

        // Todo: Currently empty test
    }

    @Test
    public void testLoginTextWatcherOnTextChanged() {
        CharSequence charSequence = "abc123";
        spyLoginTextWatcher.onTextChanged(charSequence, 0, charSequence.length(), 0);

        // Todo: Currently empty test
    }

    @Test
    public void testLoginTextWatcherAfterTextChanged() {
        Editable editable = new SpannableStringBuilder("123456");

        spyLoginTextWatcher.afterTextChanged(editable);

        verify(mockLoginPresenter).verifyInputText(editable.toString(), StringUtils.RegexMatcher.USERNAME_REGEX);
    }
}