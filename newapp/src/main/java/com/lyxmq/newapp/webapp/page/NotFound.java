package com.lyxmq.newapp.webapp.page;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

/**
 * Page to be displayed whenever a page is not found (404 error)
 *
 * @author Serge Eby
 * @version $Id: NotFound.java 5 2008-08-30 09:59:21Z serge.eby $
 */
public class NotFound {

    @Property
    @Inject
    @Path("context:images/404.jpg")
    private Asset notFoundImage;

    @Inject
    private Messages messages;

    @Inject
    private PageRenderLinkSource resources;

    public String getNotFoundMessage() {
        String message = "Not found";//MessageUtil.convert(messages.get("404.message"));
        String url = resources.createPageRenderLink(MainMenu.class).toURI();
        return String.format(message, url);
    }


}
