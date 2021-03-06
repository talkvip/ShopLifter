package edu.lmu.cs.eccms.ws.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * A servlet filter enabling cross-site requests to this web service.
 */
public class CorsFilter extends OncePerRequestFilter {

    private static final String MAX_AGE_IN_SECONDS = "1800";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", "http://localhost:9090");
        response.addHeader("Access-Control-Expose-Headers", "Location");

        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.addHeader("Access-Control-Max-Age", MAX_AGE_IN_SECONDS);
        }

        filterChain.doFilter(request, response);
    }
}
