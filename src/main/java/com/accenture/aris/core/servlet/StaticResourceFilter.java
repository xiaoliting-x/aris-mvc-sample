package com.accenture.aris.core.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class StaticResourceFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaticResourceFilter.class);
    private static final String RESOURCES_PATH_INIT_PARAMETER_NAME = "StaticResourcePath";
    private static final String DEFAULT_RESOURCES_PATH = "/resources";
    private static final String DEFAULT_CONTENT_TYPE = "text/html";

    private String resourcePath = DEFAULT_RESOURCES_PATH;

    @Override
    public void init(FilterConfig config) throws ServletException {
        String value = config.getInitParameter(RESOURCES_PATH_INIT_PARAMETER_NAME);
        if (value != null && value.length() > 0) {
            this.resourcePath = value;
        }
        LOGGER.info("Filter initialize. Static resources path={}.", this.resourcePath);
    }

    @Override
    public void destroy() {
        LOGGER.debug("Filter destroy.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,  FilterChain chain)
            throws ServletException, IOException {

        assert(request instanceof HttpServletRequest);
        assert(response instanceof HttpServletResponse);

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;

        String checkPath =
                req.getServletPath() != null ? req.getServletPath() : null
                + req.getPathInfo() != null ? req.getPathInfo() : "" ;

        if (checkPath.startsWith(resourcePath) == false) {
            LOGGER.debug("Static resource filter: Not static resources path.");
            chain.doFilter(request, response);
            return;
        }

        // ServletContext
        ServletContext ctx = req.getSession().getServletContext();

        // set Content-Type
        String filename = checkPath.substring(checkPath.lastIndexOf("/"));
        String contentType =ctx.getMimeType(filename);
        if (contentType == null) {
            contentType = DEFAULT_CONTENT_TYPE;
        }
        res.setContentType(contentType);

        // download resource
        InputStream is = ctx.getResourceAsStream(checkPath);
        if (is == null) {
            if (response instanceof HttpServletResponse) {
                ((HttpServletResponse)response).sendError(
                    HttpServletResponse.SC_NOT_FOUND, 
                    "Resource=[" + checkPath + "] is not found.");
                return;
            } else {
                throw new IllegalStateException("ServletResponse instance is not HttpServletRequeset.");
            }
        }
        FileCopyUtils.copy(is, response.getOutputStream());

        LOGGER.debug("Static resource filter: Return path={}. Content-Type={}.", checkPath, contentType);
    }
}
