package edu.lmu.cs.eccms.ws.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import edu.lmu.cs.eccms.ws.domain.Service;
import edu.lmu.cs.eccms.ws.util.DomainObjectUtils;

public class ServiceDaoTest extends edu.lmu.cs.eccms.ws.util.ApplicationContextTest {

    private ServiceDao serviceDao;
    private Service testService = new Service(1000000L, true, "MacBook Air repair", 135, "Computer fixin.", 1199.98, null);
    private Service serviceToCreate1 = DomainObjectUtils.createServiceObject(true, "Andy", 123, "This is me", 0.99);
    private Service serviceToCreate2 = DomainObjectUtils.createServiceObject(null, "SamV", 8675309, "What did I break?", 0.53);

    @Before
    public void getRequiredBeans() {
        serviceDao = (ServiceDao) applicationContext.getBean("serviceDao");
    }

    @Test
    public void testCreateService() {
        serviceDao.createService(this.serviceToCreate1);
        serviceDao.createService(this.serviceToCreate2);

        // The created services should have auto-generated IDs of 1 and 2, respectively.
        Assert.assertEquals(Long.valueOf(1L), this.serviceToCreate1.getId());
        Assert.assertEquals(Long.valueOf(2L), this.serviceToCreate2.getId());

        Service createdService1 = serviceDao.getServiceById(1L);
        Service createdService2 = serviceDao.getServiceById(2L);
        Assert.assertTrue(this.serviceToCreate1.equals(createdService1));
        Assert.assertTrue(this.serviceToCreate2.equals(createdService2));
    }

    @Test
    public void testGetServiceById() {
        Service service = serviceDao.getServiceById(1000000L);

        Assert.assertTrue(this.testService.equals(service));
    }

    @Test
    public void testGetServicesByTitle() {
        List<Service> services = serviceDao.getServices("fixin", null, 0, 10);
        Assert.assertEquals(1, services.size());
        Assert.assertTrue(testService.equals(services.get(0)));
    }

    @Test
    public void testCreateAndUpdateService() {
        Service serviceToCreate = DomainObjectUtils.createServiceObject(true, "Tom", 893, "Click", 0.94);
        Service serviceToReplaceWith = DomainObjectUtils.createServiceObject(false, "Ray", 899, "Clack", 0.49);

        serviceDao.createService(serviceToCreate);

        Long createdServiceId = serviceToCreate.getId();
        serviceToReplaceWith.setId(createdServiceId);

        // Reload the service that was just created with a new service with the same ID.
        serviceDao.createOrUpdateService(serviceToReplaceWith);
        Service createdService = serviceDao.getServiceById(createdServiceId);

        Assert.assertTrue(serviceToReplaceWith.equals(createdService));
        Assert.assertNotSame(createdService, serviceToCreate);
    }

}
