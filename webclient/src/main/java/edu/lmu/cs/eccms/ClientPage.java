package edu.lmu.cs.eccms;

/** Purpose    : Superclass for all logged-in ShopLifter views.
 *  Author     : Andrew Won
 *  Description: Eccms is the Wicket application object for the ShopLifter web client.
 */

import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AuthorizeInstantiation(Roles.USER)
public abstract class ClientPage extends WebPage {

    public ClientPage(final PageParameters pageParameters) {
        super(pageParameters);
    }

    /**
     * Relay call to the application. Mainly a shortcut, so we do not have to
     * code out ((Eccms)getApplication()).getServiceRoot() all the time.
     */
    protected String getServiceRoot() {
        return ((Eccms)getApplication()).getServiceRoot();
    }

    /**
     * Returns the application's logger.  This is not stored as an
     * instance variable because Wicket serializes web page classes and this
     * logger is not serializable.
     */
    protected Logger getLogger() {
        return LoggerFactory.getLogger(getClass());
    }

}
