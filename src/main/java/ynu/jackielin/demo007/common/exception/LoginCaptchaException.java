package ynu.jackielin.demo007.common.exception;

import javax.security.auth.login.LoginException;

public class LoginCaptchaException extends LoginException {
    public LoginCaptchaException(String message) {
        super(message);
    }
}

