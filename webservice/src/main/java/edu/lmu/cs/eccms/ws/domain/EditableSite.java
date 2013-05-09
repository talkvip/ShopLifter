package edu.lmu.cs.eccms.ws.domain;

/** Purpose    : Domain object within web service representing a website page.
 *  Author     : Andrew Won
 *  Description: Domain object within web service representing a website page.
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class EditableSite {

    private Long id;
    private String name;
    private Double top;
    private Double left;
    private Double height;
    private Double width;
    private String data;

    public EditableSite() {
        // No-args constructor
    }

    public EditableSite(Long id, String name, Double top, Double left, Double height, Double width, String data) {
        this.id = id;
        this.top = top;
        this.left = left;
        this.height = height;
        this.width = width;
        this.data = data;
        this.name = name;
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

    public Double getTop() {
        return top;
    }

    public void setTop(Double top) {
        this.top = top;
    }

    public Double getLeft() {
        return left;
    }

    public void setLeft(Double left) {
        this.left = left;
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

    @Lob
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Lob
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
