package edu.lmu.cs.eccms.ws.domain;

/** Purpose    : Domain object within web service representing a role
 *               corresponding to a user.
 *  Author     : Andrew Won
 *  Description: UserRole corresponds to a user and determines the user's
 *               accessibility to features of the web service.
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

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import edu.lmu.cs.eccms.ws.types.Role;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
public class UserRole implements Serializable {

    private Long id;
    private User user;
    private Role role;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // For compatibility with Tomcat's JDBCRealm, we join via the user login and
    // not its ID.
    @ManyToOne
    @JoinColumn(name = "login", referencedColumnName = "login")
    @XmlTransient
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // For compatibility with Tomcat's JDBCRealm, we store the role *name* and
    // not its ID.
    @XmlTransient
    public String getRolename() {
        return role.name().toLowerCase();
    }

    public void setRolename(String rolename) {
        this.role = Role.valueOf(rolename.toUpperCase());
    }

    @Transient
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
