package edu.lmu.cs.eccms;

/** Purpose    : Wicket connector for LogoutPage.html.
 *  Author     : Andrew Won
 *  Description: Connects LogoutPage.html to the Wicket web application and
 *               provides needed resources.
 */

import org.apache.wicket.PageParameters;
import org.apache.wicket.authentication.pages.SignOutPage;

public class LogoutPage extends SignOutPage {

    public LogoutPage(final PageParameters pageParameters) {
        super(pageParameters);
    }

}
