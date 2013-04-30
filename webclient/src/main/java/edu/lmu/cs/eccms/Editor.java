package edu.lmu.cs.eccms;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ResourceReference;

public class Editor extends PageWrapper {
    private static final long serialVersionUID = 1L;
    private static final ResourceReference RESOURCE_REF = new ResourceReference(Editor.class,
            "img/logo.png");
    public Editor(final PageParameters parameters) {
    super(parameters);
        add(new Image("Logo", RESOURCE_REF));

        // TODO Add your page's components here

    }
}
