package edu.lmu.cs.eccms.ws.types;

/** Purpose    : The Role enumeration defines roles for users to login to the
 *               web service.
 *  Author     : Andrew Won
 *  Description: This role enumeration provides different classifications for
 *               a user to log into the web service with.
 */

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History:
 *  -----------------
 *
 *   Ver      Date        Modified by:  Description of change/modification
 *  -----  -------------  ------------  ----------------------------------------
 *  1.0.0  21-April-2013  A. Won        Initial version/release
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public enum Role {
    HEADMASTER, FACULTY, STAFF, STUDENT
}
