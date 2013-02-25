package edu.lmu.cs.eccms.ws.domain;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A Dimension is a set of measurements corresponding to an Item.
 */
@Embeddable
@XmlRootElement
public class Dimension {

    private Integer count;
    private Double height;
    private Double width;
    private Double depth;
    private Double weight;

    public Dimension () {
        // No-args constructor
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

}
