package authentication;

import com.digitalxyncing.communication.Authenticator;

/**
 * Used to password-protect sessions. The {@link SessionAuthenticator#isAuthenticated(String, int, String)} method will
 * be called when a connection request is made to the host.
 */
public class SessionAuthenticator implements Authenticator {

    private String password;

    public SessionAuthenticator(String password) {
        this.password = password;
    }

    @Override
    public boolean isAuthenticated(String ip, int port, String token) {
        return token.equals(password);
    }

}
