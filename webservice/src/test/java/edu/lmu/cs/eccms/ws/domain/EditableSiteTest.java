package edu.lmu.cs.eccms.ws.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class EditableSiteTest {

    @Test
    public void fieldsSetByFullConstructorCanBeRead() {
        EditableSite i = new EditableSite(5L, "ID0", "860", "100", "200", "350", "Test site here.");
        assertThat(i.getId(), is(5L));
        assertThat(i.getName(), is("ID0"));
        assertThat(i.getTop(), is("860"));
        assertThat(i.getCssleft(), is("100"));
        assertThat(i.getHeight(), is("200"));
        assertThat(i.getWidth(), is("350"));
        assertThat(i.getData(), is("Test site here."));
    }

    @Test
    public void fieldsSetByEmptyConstructorAreNull() {
        EditableSite i = new EditableSite();
        assertNull(i.getId());
        assertNull(i.getName());
        assertNull(i.getTop());
        assertNull(i.getCssleft());
        assertNull(i.getHeight());
        assertNull(i.getWidth());
        assertNull(i.getData());
    }

    @Test
    public void fieldsSetBySettersCanBeRead() {
        EditableSite i = new EditableSite();
        i.setId(5L);
        i.setName("Some div");
        i.setTop("867");
        i.setCssleft("789");
        i.setWidth("456");
        i.setHeight("123");
        i.setData("This is where stuff goes!");
        assertThat(i.getId(), is(5L));
        assertThat(i.getName(), is("Some div"));
        assertThat(i.getTop(), is("867"));
        assertThat(i.getCssleft(), is("789"));
        assertThat(i.getWidth(), is("456"));
        assertThat(i.getHeight(), is("123"));
        assertThat(i.getData(), is("This is where stuff goes!"));
    }

    @Test
    public void toStringProducesExpectedString() {
        EditableSite site1 = new EditableSite(5L, "ID1", "100", "12", "72", "35", "Hello World!");


        String expected1 = "EditableSite{id=5, name=ID1, top=100, cssleft=12, width=35, height=72, data=Hello World!}";
        EditableSite site2 = new EditableSite();
        site2.setId(5L);
        site2.setName("ID2");
        site2.setTop("100");
        site2.setCssleft("101");
        site2.setWidth("42");
        site2.setHeight("76");
        site2.setData("Welcome to a new world!");
        String expected2 = "EditableSite{id=5, name=ID2, top=100, cssleft=101, width=42, height=76, data=Welcome to a new world!}";
        assertThat(site1.toString(), is(expected1));
        assertThat(site2.toString(), is(expected2));
    }

}
