package edu.lmu.cs.eccms.ws.service;

/** Purpose    : Utility for the web service services.
 *  Author     : Andrew Won
 *  Description: A base class for the services, supplying a logger.
 */

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History:
 *  -----------------
 *
 *   Ver      Date         Modified by:  Description of change/modification
 *  -----  -------------   ------------  ---------------------------------------
 *  1.0.0  21-April-2013   A. Won        Initial version/release
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import org.apache.log4j.Logger;

public class AbstractService {

    protected Logger logger = Logger.getLogger(getClass());

    /**
     * Returns the service-level logger.
     */
    protected Logger getLogger() {
        return logger;
    }

}
