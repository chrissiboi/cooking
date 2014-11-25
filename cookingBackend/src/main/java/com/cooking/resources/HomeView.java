package com.cooking.resources;

import io.dropwizard.views.View;

/**
 * Created by Chris on 04.11.2014.
 */
public class HomeView extends View {

    protected HomeView(String pageName) {
        super(pageName);
    }

    protected HomeView() {
        super("home.ftl");
    }

}
