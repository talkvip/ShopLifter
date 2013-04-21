package edu.lmu.cs.eccms.ws.domain;

/** Purpose    : Domain object within web service providing physical
 *               measurements to correspond to an Item.
 *  Author     : Andrew Won
 *  Description: Dimension is a set of physical measurements corresponding to an
 *               Item.
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

import com.google.common.base.Objects;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

@Embeddable
@XmlRootElement
public class Dimension {

    private Integer count;
    private Double height;
    private Double width;
    private Double depth;
    private Double weight;

    public Dimension() {
        // No-args constructor
    }

    public Dimension(Integer count, Double height, Double width, Double depth, Double weight) {
        this.count = count;
        this.height = height;
        this.width = width;
        this.depth = depth;
        this. weight = weight;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Dimension) {
            Dimension d = Dimension.class.cast(obj);
            return (Objects.equal(count, d.count) &&
                    Objects.equal(height, d.height) &&
                    Objects.equal(width, d.width) &&
                    Objects.equal(depth, d.depth) &&
                    Objects.equal(weight, d.weight));
        }
        return false;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("count", this.count)
                .add("height", this.height)
                .add("width", this.width)
                .add("depth", this.depth)
                .add("weight", this.weight)
                .toString();
    }
}
