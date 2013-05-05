package edu.lmu.cs.eccms;

/** Purpose    : Wicket connector for AboutPage.html.
 *  Author     : Andrew Won
 *  Description: Connects AboutPage.html to the Wicket web application and
 *               provides needed resources.
 */

import org.apache.wicket.PageParameters;

public class AboutPage extends PageWrapper {
    private static final long serialVersionUID = 1L;

    public AboutPage (final PageParameters parameters) {
        super(parameters);
    }
}
