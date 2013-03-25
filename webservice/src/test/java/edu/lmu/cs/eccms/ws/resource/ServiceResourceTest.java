package edu.lmu.cs.eccms.ws.resource;

import java.util.List;

import junit.framework.Assert;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

import edu.lmu.cs.eccms.ws.domain.Service;
import edu.lmu.cs.eccms.ws.util.DomainObjectUtils;

/**
 * Tests the service web resource.
 */
public class ServiceResourceTest extends ResourceTest {

    private Service testService = new Service(1000000L, true, "MacBook Air repair", 135, "Computer fixin.", 1199.98, null);
    private Service serviceToCreate = DomainObjectUtils.createServiceObject(false, "Washing Machine Repair", 134, "Make it as good as new!",
            199.99);
    private Service serviceToUpdate = DomainObjectUtils.createServiceObject(false, "Car Repair", 134, "All extra parts returned to you!",
            599.99);

    @Test
    public void testGetServiceByNonexistentId() {
        ClientResponse clientResponse = wr.path("services/17").get(ClientResponse.class);
        Assert.assertEquals(204, clientResponse.getStatus());
    }

    @Test
    public void testGetServiceById() {
        Service service = wr.path("services/1000000").get(ClientResponse.class).getEntity(Service.class);
        assertThat(service, is(testService));
    }

    @Test
    public void testGetServicesQueryByName() {
        List<Service> services = wr.path("services").queryParam("q", "Air").get(new GenericType<List<Service>>() {
        });

        Assert.assertEquals(1, services.size());
    }

    @Test
    public void testGetServicesQueryByDescription() {
        List<Service> services = wr.path("services").queryParam("q", "fixin").get(new GenericType<List<Service>>() {
        });

        Assert.assertEquals(1, services.size());
    }

    @Test(expected = com.sun.jersey.api.client.UniformInterfaceException.class)
    public void testGetServicesByNullQuery() {
        List<Service> services = wr.path("services").get(new GenericType<List<Service>>() {
        });

        Assert.assertEquals(1, services.size());
    }

    @Test
    public void testGetServicesQueryByNonExistingName() {
        List<Service> services = wr.path("services").queryParam("q", "Dell").get(new GenericType<List<Service>>() {
        });

        Assert.assertEquals(0, services.size());
    }

    @Test
    public void testGetServicesByActiveQuery() {
        List<Service> services = wr.path("services").queryParam("active", "true").get(new GenericType<List<Service>>() {
        });

        Assert.assertEquals(1, services.size());
    }

    @Test
    public void testCreateService() {
        ClientResponse response = wr.path("services").post(ClientResponse.class, serviceToCreate);
        Assert.assertEquals(201, response.getStatus());
    }

    @Test
    public void testCreateServiceWithSpecifiedIdProduces400() {
        serviceToCreate.setId(500L);

        ClientResponse response = wr.path("services").post(ClientResponse.class, serviceToCreate);
        Assert.assertEquals(400, response.getStatus());
    }

    @Test
    public void testUpdateServiceProduces204() {
        serviceToUpdate.setId(1000000L);

        ClientResponse response = wr.path("services/1000000").put(ClientResponse.class, serviceToUpdate);
        Assert.assertEquals(204, response.getStatus());

        Service updatedService = wr.path("services/1000000").get(ClientResponse.class).getEntity(Service.class);
        assertThat(updatedService, is(serviceToUpdate));
    }

    @Test
    public void testUpdateServiceWithInconsistentIdProduces400() {
        ClientResponse response = wr.path("services/1000").put(ClientResponse.class, serviceToUpdate);
        Assert.assertEquals(400, response.getStatus());
    }
}
