package edu.lmu.cs.eccms;

/** Purpose    : LoginPanel customizes the default Wicket sign-in panel markup.
 *  Author     : Andrew Won
 *  Description: LoginPanel customizes the default Wicket sign-in panel markup.
 */

import org.apache.wicket.authentication.panel.SignInPanel;

@SuppressWarnings("serial")
public class LoginPanel extends SignInPanel {

    public LoginPanel(final String id) {
        super(id);
    }

    public LoginPanel(final String id, final boolean includeRememberMe) {
        super(id, includeRememberMe);
    }

}
