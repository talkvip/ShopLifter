package edu.lmu.cs.eccms;

/** Purpose    : Wicket connector for LoginPage.html; also sets header requiring
 *               login.
 *  Author     : Andrew Won
 *  Description: Connects LoginPage.html to the Wicket web application and
 *               provides needed resources.
 */

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebResponse;

public class LoginPage extends WebPage {

    /**
     * @param parameters
     */
    public LoginPage(final PageParameters parameters) {
        add(new LoginPanel("loginPanel"));
    }

    @Override
    protected void setHeaders(WebResponse response) {
        super.setHeaders(response);

        // Supply an explicit header indicating signed-in state.
        response.setHeader("X-Headmaster-Sign-In-Required", "yes");
    }

}
