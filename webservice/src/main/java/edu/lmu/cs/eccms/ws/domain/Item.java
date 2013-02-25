package edu.lmu.cs.eccms.ws.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * An item is a salable, tangible product that can be displayed for sale.
 */
@Entity
@XmlRootElement
public class Item {

    private Long id;
    private Boolean active = Boolean.TRUE;
    private String name;
    private Integer sku;
    private String description;
    private Double price;
    private Dimension dimension = new Dimension();
    //private ItemHistory itemHistory = new ItemHistory();

    public Item () {
        // No-args constructor
    }

    public Item (Boolean active, String name, Integer sku, String description,
            Double price) {
        this.active = active;
        this.name = name;
        this.sku = sku;
        this.description = description;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Indicates whether a student is still in the program or institution under
     * which this installation of Headmaster is running. Better approach than
     * having to delete the student.
     */
    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Lob
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Lob
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // This keeps the record from going down the wire; it will have to be sent
    // out separately.
    @XmlTransient
    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
/*
    @XmlTransient
    public ItemHistory getItemHistory() {
        return itemHistory;
    }

    public void setItemHistory(ItemHistory itemHistory) {
        this.itemHistory = itemHistory;
    }
*/
    public Boolean getActive() {
        return active;
    }
}
