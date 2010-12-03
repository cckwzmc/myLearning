package com.example.newapp.components;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;

/**
 * Layout component for pages of application newapp.
 */
@Import(stylesheet={"context:layout/layout.css"})
public class Layout
{
    /** The page title, for the <title> element and the <h1> element. */
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;

    @Property
    private String pageName;

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String sidebarTitle;

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private Block sidebar;

    @Inject
    private ComponentResources resources;

    public String getClassForPageName()
    {
      String retstr= resources.getPageName().equalsIgnoreCase(pageName)
             ? "current_page_item"
             : null;
      return retstr;
    }

    public String[] getPageNames()
    {
      return new String[] { "Index", "About", "Contact" };
    }
}
