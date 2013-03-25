package edu.lmu.cs.eccms.ws.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ItemTest {

    @Test
    public void fieldsSetByFullConstructorCanBeRead() {
        Dimension d = new Dimension();
        ItemHistory ih = new ItemHistory();
        Item i = new Item(5L, true, "Some car", 867, "A motor vehicle.",
                5309.0, d, ih);
        assertThat(i.getId(), is(5L));
        assertThat(i.getActive(), is(true));
        assertThat(i.getName(), is("Some car"));
        assertThat(i.getSku(), is(867));
        assertThat(i.getDescription(), is("A motor vehicle."));
        assertThat(i.getPrice(), is(5309.0));
        assertThat(i.getDimension(), is(d));
        assertThat(i.getItemHistory(), is(ih));
    }

    @Test
    public void fieldsSetByEmptyConstructorAreNull() {
        Item i = new Item();
        assertNull(i.getId());
        assertThat(i.getActive(), is(false));
        assertNull(i.getName());
        assertNull(i.getSku());
        assertNull(i.getDescription());
        assertNull(i.getPrice());
    }

    @Test
    public void fieldsSetBySettersCanBeRead() {
        Dimension d = new Dimension(10, 2.0, 3.0, 4.0, 63.5);
        ItemHistory ih = new ItemHistory();
        Item i = new Item();
        i.setId(5L);
        i.setActive(true);
        i.setName("Some car");
        i.setSku(867);
        i.setDescription("A motor vehicle.");
        i.setPrice(5309.0);
        i.setDimension(d);
        i.setItemHistory(ih);
        assertThat(i.getId(), is(5L));
        assertThat(i.getActive(), is(true));
        assertThat(i.getName(), is("Some car"));
        assertThat(i.getSku(), is(867));
        assertThat(i.getDescription(), is("A motor vehicle."));
        assertThat(i.getPrice(), is(5309.0));
        assertThat(i.getDimension(), is(d));
        assertThat(i.getItemHistory(), is(ih));
    }

    @Test
    public void toStringProducesExpectedString() {
        Dimension d = new Dimension(10, 2.0, 3.0, 4.0, 63.5);
        ItemHistory ih = new ItemHistory();
        Item item1 = new Item(5L, true, "Some car", 867, "A motor vehicle.",
                5309.0, d, ih);


        String expected1 = "Item{id=5, active=true, name=Some car, sku=867, description=A motor vehicle., price=5309.0, dimension=Dimension{count=10, height=2.0, width=3.0, depth=4.0, weight=63.5}}";
        Item item2 = new Item();
        item2.setId(5L);
        item2.setActive(true);
        item2.setName("Some car");
        item2.setSku(867);
        item2.setDescription("A motor vehicle.");
        item2.setPrice(5309.0);
        item2.setDimension(d);
        item2.setItemHistory(ih);
        String expected2 = "Item{id=5, active=true, name=Some car, sku=867, description=A motor vehicle., price=5309.0, dimension=Dimension{count=10, height=2.0, width=3.0, depth=4.0, weight=63.5}}";
        assertThat(item1.toString(), is(expected1));
        assertThat(item2.toString(), is(expected2));
    }

    @Test
    public void hashCodeProducesId() {
        assertThat(new Item(5L, true, "Some car", 867, "A motor vehicle.",
                5309.0, new Dimension(), new ItemHistory()).hashCode(), is(5));
    }

    @Test
    public void equalsUsesId() {
        Item item1 = new Item();
        item1.setId(5L);
        Item item2 = new Item();
        item2.setId(5L);
        Item item3 = new Item();
        item3.setId(500L);
        assertThat(item1, equalTo(item2));
        assertThat(item2, not(equalTo(item3)));
        assertThat(item1, not(equalTo(item3)));
    }

}
