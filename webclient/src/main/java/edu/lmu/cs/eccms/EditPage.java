package edu.lmu.cs.eccms;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ResourceReference;

public class EditPage extends PageWrapper {
    private static final long serialVersionUID = 1L;
    private static final ResourceReference RESOURCE_REF = new ResourceReference(EditPage.class,
            "img/logo.png");
    public EditPage(final PageParameters parameters) {
    super(parameters);
        add(new Image("Logo", RESOURCE_REF));
    }
}
