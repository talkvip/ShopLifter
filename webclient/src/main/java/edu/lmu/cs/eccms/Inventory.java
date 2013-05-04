package edu.lmu.cs.eccms;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ResourceReference;

public class Inventory extends PageWrapper {
    private static final long serialVersionUID = 1L;
    private static final ResourceReference LOGO_REF = new ResourceReference(Inventory.class, "img/logo.png");
    private static final ResourceReference TRASH_ICON = new ResourceReference(Inventory.class, "img/trash_can.png");
    private static final ResourceReference EDIT_ICON = new ResourceReference(Inventory.class, "img/edit_icon.png");

    public Inventory(final PageParameters parameters) {
        super(parameters);
        add(new Image("Logo", LOGO_REF));
        add(new Image("Trash", TRASH_ICON));
        add(new Image("Edit", EDIT_ICON));
    }
}
