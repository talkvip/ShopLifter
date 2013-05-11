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

import com.google.common.base.Objects;

@Entity
@XmlRootElement
public class EditableSite {

    private Long id;
    private String name;
    private String top;
    private String cssleft;
    private String height;
    private String width;
    private String data;

    public EditableSite() {
        // No-args constructor
    }

    public EditableSite(Long id, String name, String top, String cssleft, String height, String width, String data) {
        this.id = id;
        this.top = top;
        this.cssleft = cssleft;
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

    @Lob
    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    @Lob
    public String getCssleft() {
        return cssleft;
    }

    public void setCssleft(String cssleft) {
        this.cssleft = cssleft;
    }

    @Lob
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Lob
    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
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

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", this.id)
                .add("name", this.name)
                .add("top", this.top)
                .add("cssleft", this.cssleft)
                .add("width", this.width)
                .add("height", this.height)
                .add("data", this.data)
                .toString();
    }
}
