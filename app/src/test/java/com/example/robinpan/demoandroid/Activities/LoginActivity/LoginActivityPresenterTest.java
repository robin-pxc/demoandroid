package com.example.robinpan.demoandroid.Activities.LoginActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.example.robinpan.demoandroid.CommonUtils.StringUtils.RegexMatcher.PASSWORD_REGEX;
import static com.example.robinpan.demoandroid.CommonUtils.StringUtils.RegexMatcher.USERNAME_REGEX;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LoginActivityPresenterTest {

    @Mock
    private LoginActivityContract.View mockView;

    private LoginActivityPresenter spyLoginActivityPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        spyLoginActivityPresenter = spy(new LoginActivityPresenter());
        spyLoginActivityPresenter.view = mockView;
    }

    @Test
    public void testVerifyInputTextUsernameSuccess() {
        String correctUsernameText = "name1234s";

        spyLoginActivityPresenter.verifyInputText(correctUsernameText, USERNAME_REGEX);

        verify(spyLoginActivityPresenter.view).removeUsernameError();
    }

    @Test
    public void testVerifyInputTextUsernameFailure() {
        String wrongUsername1 = "ashdkahjksahdkjhasjkdhkajshdkj";
        String wrongUsername2 = "as34";
        String wrongUsername3 = "asdhkj213$$";

        spyLoginActivityPresenter.verifyInputText(wrongUsername1, USERNAME_REGEX);
        spyLoginActivityPresenter.verifyInputText(wrongUsername2, USERNAME_REGEX);
        spyLoginActivityPresenter.verifyInputText(wrongUsername3, USERNAME_REGEX);

        verify(spyLoginActivityPresenter.view, times(3)).setUsernameError();
    }

    @Test
    public void testVerifyInputTextPasswordSuccess() {
        String correctPasswordText = "Asd9094";

        spyLoginActivityPresenter.verifyInputText(correctPasswordText, PASSWORD_REGEX);

        verify(spyLoginActivityPresenter.view).removePasswordError();
    }

    @Test
    public void testVerifyInputTextPasswordFailure() {
        String wrongPwdText1 = "Abc12";
        String wrongPwdText2 = "Abcdefg123456789012345678901234567890";
        String wrongPwdText3 = "Abc123%^&*^";

        spyLoginActivityPresenter.verifyInputText(wrongPwdText1, PASSWORD_REGEX);
        spyLoginActivityPresenter.verifyInputText(wrongPwdText2, PASSWORD_REGEX);
        spyLoginActivityPresenter.verifyInputText(wrongPwdText3, PASSWORD_REGEX);

        verify(spyLoginActivityPresenter.view, times(3)).setPasswordError();
    }

    @Test
    public void testVerifyInputTextEnableLoginFunction() {
        String inputText = "qwe123";
        doReturn(true).when(spyLoginActivityPresenter.view).availableToLogin();

        spyLoginActivityPresenter.verifyInputText(inputText, USERNAME_REGEX);

        verify(spyLoginActivityPresenter.view).enableLoginButton();
    }

    @Test
    public void testVerifyInputTextDisableLoginFunction() {
        String inputText = "qwe123";
        doReturn(false).when(spyLoginActivityPresenter.view).availableToLogin();

        spyLoginActivityPresenter.verifyInputText(inputText, USERNAME_REGEX);

        verify(spyLoginActivityPresenter.view).disableLoginButton();
    }
}