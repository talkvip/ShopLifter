package edu.lmu.cs.eccms.ws.domain;

/** Purpose    : Domain object within web service representing a saleable Item
 *               for the eCommerce user.
 *  Author     : Andrew Won
 *  Description: Item is a saleable good offered by the eCommerce user and,
 *               if active, purchaseable by customers.
 */

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History:
 *  -----------------
 *
 *   Ver      Date        Modified by:  Description of change/modification
 *  -----  -----------    ------------  ----------------------------------------
 *  1.0.0  21-April-2013  A. Won        Initial version/release
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.google.common.base.Objects;

@Entity
@XmlRootElement
public class Item {

    private Long id;
    private Boolean active = Boolean.FALSE;
    private String name;
    private Integer sku;
    private String description;
    private Double price;
    private Integer quantity;
    private Dimension dimension = new Dimension();
    private ItemHistory itemHistory = new ItemHistory();

    public Item() {
        // No-args constructor
    }

    public Item(Long id, Boolean active, String name, Integer sku, Integer quantity,
            String description, Double price, Dimension dimension, ItemHistory itemHistory) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
                .add("quantity", this.quantity)
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
                    && Objects.equal(this.quantity, item.getQuantity()) && Objects.equal(this.description, item.getDescription())
                    && Objects.equal(this.price, item.getPrice());
        }
        return false;
    }

}
