package com.dav.mybatis.common.custom;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.dav.mybatis.common.util.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomizationBean.
 */
@Component
public class CustomizationBean implements EmbeddedServletContainerCustomizer {

    /* (non-Javadoc)
     * @see org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer#customize(org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer)
     */
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, Constants.STR_PATH_NOT_FOUND));
        container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, Constants.STR_PATH_INTERNAL_SERVER_ERROR));
    }
}