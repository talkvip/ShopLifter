package edu.lmu.cs.eccms.ws.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.google.common.base.Objects;

/**
 * An item is a salable, tangible product that can be displayed for sale.
 */
@Entity
@XmlRootElement
public class Item {

    private Long id;
    private Boolean active = Boolean.FALSE;
    private String name;
    private Integer sku;
    private String description;
    private Double price;
    private Dimension dimension = new Dimension();
    private ItemHistory itemHistory = new ItemHistory();

    public Item() {
        // No-args constructor
    }

    public Item(Long id, Boolean active, String name, Integer sku, String description,
            Double price, Dimension dimension, ItemHistory itemHistory) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.description = description;
        this.price = price;
        this.dimension = dimension;
        this.itemHistory = itemHistory;
        if (active != null)
            this.active = active;
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
     * Indicates whether an item is currently for sale.
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

    @XmlTransient
    public ItemHistory getItemHistory() {
        return itemHistory;
    }

    public void setItemHistory(ItemHistory itemHistory) {
        this.itemHistory = itemHistory;
    }

    public Boolean getActive() {
        return active;
    }

    @Override
    public int hashCode() {
        return this.id.intValue();
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", this.id)
                .add("active", this.active)
                .add("name", this.name)
                .add("sku", this.sku)
                .add("description", this.description)
                .add("price", this.price)
                .add("dimension", this.dimension)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            Item item = Item.class.cast(obj);
            return Objects.equal(this.id, item.getId()) && Objects.equal(this.active, item.getActive())
                    && Objects.equal(this.name, item.getName()) && Objects.equal(this.sku, item.getSku())
                    && Objects.equal(this.description, item.getDescription())
                    && Objects.equal(this.price, item.getPrice());
        }
        return false;
    }

}
