package com.cooking.conf;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by Chris on 12.11.2014.
 */
public class Main {

    public static void main(String args[]) throws Exception{

        String webappDirLocation = "src/main/resources";

        Server server = new Server(8080);
        WebAppContext context = new WebAppContext();

        context.setContextPath("/");ö
        context.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
        context.setResourceBase(webappDirLocation);

        context.setParentLoaderPriority(true);

        server.setHandler(context);
        server.start();
        server.join();
    }
}
