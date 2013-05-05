package edu.lmu.cs.eccms;

/** Purpose    : Wicket connector for Report.html.
 *  Author     : Andrew Won
 *  Description: Connects Report.html to the Wicket web application and
 *               provides needed resources.
 */

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ResourceReference;

public class Report extends PageWrapper {
    private static final long serialVersionUID = 1L;
    private static final String LOGO_PATH = "Logo";
    private static final ResourceReference RESOURCE_REF = new ResourceReference(Report.class,
            "img/logo.png");

    public Report(final PageParameters parameters) {
        super(parameters);
        add(new Image(LOGO_PATH, RESOURCE_REF));
    }
}
