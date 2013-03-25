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
 * A service is offered by the business owner and can be compiled into a 
 * proposal document through customer selections.
 */
@Entity
@XmlRootElement
public class Service {

    private Long id;
    private Boolean active = Boolean.FALSE;
    private String name;
    private Integer sku;
    private String description;
    private Double price;
    private ServiceHistory serviceHistory = new ServiceHistory();

    public Service() {
        // No-args constructor
    }

    public Service(Long id, Boolean active, String name, Integer sku, String description,
            Double price, Dimension dimension, ServiceHistory serviceHistory) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.description = description;
        this.price = price;
        this.serviceHistory = serviceHistory;
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
     * Indicates whether a service is currently offered.
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
    public ServiceHistory getServiceHistory() {
        return serviceHistory;
    }

    public void setServiceHistory(ServiceHistory serviceHistory) {
        this.serviceHistory = serviceHistory;
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
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Service) {
            Service item = Service.class.cast(obj);
            return Objects.equal(this.id, item.getId()) && Objects.equal(this.active, item.getActive())
                    && Objects.equal(this.name, item.getName()) && Objects.equal(this.sku, item.getSku())
                    && Objects.equal(this.description, item.getDescription())
                    && Objects.equal(this.price, item.getPrice());
        }
        return false;
    }

}
