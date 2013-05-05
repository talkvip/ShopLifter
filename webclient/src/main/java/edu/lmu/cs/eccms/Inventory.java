package edu.lmu.cs.eccms;

/** Purpose    : Wicket connector for Inventory.html.
 *  Author     : Andrew Won
 *  Description: Connects Inventory.html to the Wicket web application and
 *               provides needed resources.
 */

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ResourceReference;

public class Inventory extends PageWrapper {
    private static final long serialVersionUID = 1L;
    private static final String LOGO_PATH = "Logo";
    private static final String TRASH_PATH = "Trash";
    private static final String EDIT_PATH = "Edit";
    private static final ResourceReference LOGO_REF = new ResourceReference(Inventory.class, "img/logo.png");
    private static final ResourceReference TRASH_ICON = new ResourceReference(Inventory.class, "img/trash_can.png");
    private static final ResourceReference EDIT_ICON = new ResourceReference(Inventory.class, "img/edit_icon.png");

    public Inventory(final PageParameters parameters) {
        super(parameters);
        add(new Image(LOGO_PATH, LOGO_REF));
        add(new Image(TRASH_PATH, TRASH_ICON));
        add(new Image(EDIT_PATH, EDIT_ICON));
    }
}
