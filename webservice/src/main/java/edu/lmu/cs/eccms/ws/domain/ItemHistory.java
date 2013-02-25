package edu.lmu.cs.eccms.ws.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.OrderColumn;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import edu.lmu.cs.eccms.ws.util.DateTimeXmlAdapter;

/**
 * An ItemHistory is a history of an Item.
 */
@Embeddable
@XmlRootElement
public class ItemHistory {

    private DateTime firstSoldDate;
    private Integer unitsSold;
    private List<Double> priceHistory = new ArrayList<Double>();

    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @XmlJavaTypeAdapter(value = DateTimeXmlAdapter.class)
    public DateTime getFirstSoldDate() {
        return firstSoldDate;
    }

    public void setFirstSoldDate(DateTime firstSoldDate) {
        this.firstSoldDate = firstSoldDate;
    }

    public Integer getUnitsSold() {
        return unitsSold;
    }

    public void setUnitsSold(Integer unitsSold) {
        this.unitsSold = unitsSold;
    }

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderColumn
    public List<Double> getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(List<Double> priceHistory) {
        this.priceHistory = priceHistory;
    }

}
