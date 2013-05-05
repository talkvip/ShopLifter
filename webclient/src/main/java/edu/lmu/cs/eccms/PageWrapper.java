package edu.lmu.cs.eccms;

/** Purpose    : Adds navbar header and footer to all logged-in ShopLifter views.
 *  Author     : Andrew Won
 *  Description: As a superclass to other pages, adds a navigation bar to the top
 *               and footer to the bottom of each page that extends this class. Also,
 *               Wicket connector for PageWrapper.html.
 */

import org.apache.wicket.PageParameters;

public class PageWrapper extends ClientPage {
    private static final long serialVersionUID = 1L;

    public PageWrapper(final PageParameters parameters) {
        super(parameters);
    }
}
