package edu.lmu.cs.eccms.ws.resource;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;

import edu.lmu.cs.eccms.ws.domain.Dimension;
import edu.lmu.cs.eccms.ws.domain.Item;
import edu.lmu.cs.eccms.ws.domain.ItemHistory;
import edu.lmu.cs.eccms.ws.domain.User;
import edu.lmu.cs.eccms.ws.domain.UserRole;

/**
 * A provider which ensures that JSON content is generated using the <em>natural</em> style, rather than the default
 * <em>mapped</em> style.
 */
@Provider
public class NaturalJsonContextResolver implements ContextResolver<JAXBContext> {

    private final JAXBContext context;

    private final Class<?>[] types = {

            // Domain classes requiring JSON serialization.
            Item.class, ItemHistory.class, Dimension.class, User.class, UserRole.class};

    private final List<Class<?>> typeList = Arrays.asList(types);

    /**
     * Creates the context to use the natural JSON style.
     */
    public NaturalJsonContextResolver() throws Exception {
        context = new JSONJAXBContext(JSONConfiguration.natural().build(), types);
    }

    @Override
    public JAXBContext getContext(Class<?> objectType) {
        return (typeList.contains(objectType)) ? context : null;
    }
}
