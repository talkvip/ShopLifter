package edu.lmu.cs.eccms.ws.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ServiceTest {

    @Test
    public void fieldsSetByFullConstructorCanBeRead() {
        ServiceHistory sh = new ServiceHistory();
        Service s = new Service(5L, true, "Some car service", 867, "A motor vehicle service.",
                5309.0, sh);
        assertThat(s.getId(), is(5L));
        assertThat(s.getActive(), is(true));
        assertThat(s.getName(), is("Some car service"));
        assertThat(s.getSku(), is(867));
        assertThat(s.getDescription(), is("A motor vehicle service."));
        assertThat(s.getPrice(), is(5309.0));
        assertThat(s.getServiceHistory(), is(sh));
    }

    @Test
    public void fieldsSetByEmptyConstructorAreNull() {
        Service s = new Service();
        assertNull(s.getId());
        assertThat(s.getActive(), is(false));
        assertNull(s.getName());
        assertNull(s.getSku());
        assertNull(s.getDescription());
        assertNull(s.getPrice());
    }

    @Test
    public void fieldsSetBySettersCanBeRead() {
        ServiceHistory sh = new ServiceHistory();
        Service s = new Service();
        s.setId(5L);
        s.setActive(true);
        s.setName("Some car service");
        s.setSku(867);
        s.setDescription("A motor vehicle service.");
        s.setPrice(5309.0);
        s.setServiceHistory(sh);
        assertThat(s.getId(), is(5L));
        assertThat(s.getActive(), is(true));
        assertThat(s.getName(), is("Some car service"));
        assertThat(s.getSku(), is(867));
        assertThat(s.getDescription(), is("A motor vehicle service."));
        assertThat(s.getPrice(), is(5309.0));
        assertThat(s.getServiceHistory(), is(sh));
    }

    @Test
    public void toStringProducesExpectedString() {
        ServiceHistory sh = new ServiceHistory();
        Service service1 = new Service(5L, true, "Some car service", 867, "A motor vehicle service.",
                5309.0, sh);


        String expected1 = "Service{id=5, active=true, name=Some car service, sku=867, description=A motor vehicle service., price=5309.0}";
        Service service2 = new Service();
        service2.setId(5L);
        service2.setActive(true);
        service2.setName("Some car service");
        service2.setSku(867);
        service2.setDescription("A motor vehicle service.");
        service2.setPrice(5309.0);
        service2.setServiceHistory(sh);
        String expected2 = "Service{id=5, active=true, name=Some car service, sku=867, description=A motor vehicle service., price=5309.0}";
        assertThat(service1.toString(), is(expected1));
        assertThat(service2.toString(), is(expected2));
    }

    @Test
    public void hashCodeProducesId() {
        assertThat(new Service(5L, true, "Some car service", 867, "A motor vehicle service.",
                5309.0, new ServiceHistory()).hashCode(), is(5));
    }

    @Test
    public void equalsUsesId() {
        Service service1 = new Service();
        service1.setId(5L);
        Service service2 = new Service();
        service2.setId(5L);
        Service service3 = new Service();
        service3.setId(500L);
        assertThat(service1, equalTo(service2));
        assertThat(service2, not(equalTo(service3)));
        assertThat(service1, not(equalTo(service3)));
    }

}
