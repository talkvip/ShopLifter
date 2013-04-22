package edu.lmu.cs.eccms.ws.util;

/** Purpose    : Exception class to carry an HTTP response code.
 *  Author     : Dr. John Dionisio
 *  Description: A simplified exception class useful in JAX-RS applications.
 *               Allows responses to be build with an HTTP response code, the
 *               content type text/plain and a single message.
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

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@SuppressWarnings("serial")
public class ServiceException extends WebApplicationException {

    public ServiceException(int status, String message) {
        super(Response.status(status)
                .header("Content-type", "text/plain")
                .entity(status + " " + message)
                .build());
    }

    /**
     * Convenience constructor so that we can use the Status enum.
     */
    public ServiceException(Response.Status status, String message) {
        this(status.getStatusCode(), message);
    }
}
