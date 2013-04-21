package edu.lmu.cs.eccms.ws.dao;

/** Purpose    : Interface for Database Interaction through Data-Access Object
 *               for domain item User.
 *  Author     : Andrew Won
 *  Description: This file provides an interface for database access
 *               implementations which may be database specific.  The dao, or
 *               Data-Access Object is the single point where the service
 *               interacts with any database or persistent data store.
 */

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History:
 *  -----------------
 *
 *   Ver      Date       Modified by:  Description of change/modification
 *  -----  -----------   ------------  ------------------------------------------
 *  1.0.0  21-April-2013  A. Won    Initial version/release
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import edu.lmu.cs.eccms.ws.domain.User;

/**
 * Dao interface for user domain objects.
 */
public interface UserDao {

    /**
     * Returns the user with the given login name (password not included).
     */
    User getUserByLogin(String login);

    /**
     * Returns the user with the given id.
     */
    User getUserById(Long id);

    /**
     * Creates a new service user.
     */
    User createUser(User user);

    /**
     * Creates a new service user or updates an existing one.
     */
    User createOrUpdateUser(User user);

}
