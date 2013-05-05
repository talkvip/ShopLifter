package edu.lmu.cs.eccms;

/** Purpose    : Wicket connector for Editor.html.
 *  Author     : Andrew Won
 *  Description: Connects Editor.html to the Wicket web application and
 *               provides needed resources.
 */

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ResourceReference;

public class Editor extends PageWrapper {
    private static final long serialVersionUID = 1L;
    private static final String LOGO_PATH = "Logo";
    private static final ResourceReference LOGO_REF = new ResourceReference(Editor.class,
            "img/logo.png");

    public Editor(final PageParameters parameters) {
        super(parameters);
        add(new Image(LOGO_PATH, LOGO_REF));
    }
}
