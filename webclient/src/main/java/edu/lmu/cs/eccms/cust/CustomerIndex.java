package edu.lmu.cs.eccms.cust;

/** Purpose    : Wicket connector for CustomerIndex.html.
 *  Author     : Andrew Won
 *  Description: Connects CustomerIndex.html, the main page for customers, to
 *               the Wicket web application and provides needed resources.
 */

import org.apache.wicket.PageParameters;

import edu.lmu.cs.eccms.PageWrapper;

public class CustomerIndex extends PageWrapper {
    private static final long serialVersionUID = 1L;

    public CustomerIndex (final PageParameters parameters) {
        super(parameters);
    }
}
