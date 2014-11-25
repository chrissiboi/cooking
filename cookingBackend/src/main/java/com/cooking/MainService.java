package com.cooking;

import com.cooking.conf.DatabaseConfiguration;
import com.cooking.core.DAORecipe;
import com.cooking.core.DAOUser;
import com.cooking.core.T_Recipe;
import com.cooking.core.T_User;
import com.cooking.resources.HomeResource;
import com.cooking.resources.RecipeResource;
import com.cooking.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jersey.sessions.Session;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.hibernate.SessionFactory;

/**
 * Created by Chris on 03.11.2014.
 */
public class MainService extends Application<DatabaseConfiguration> {

    public static void main(String[] args) throws Exception {
        new MainService().run(args);
    }

    private final HibernateBundle<DatabaseConfiguration> hibernateUsers = new HibernateBundle<DatabaseConfiguration>(T_User.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(DatabaseConfiguration databaseConfiguration) {
            return databaseConfiguration.getDatabase();
        }
    };

    private final HibernateBundle<DatabaseConfiguration> hibernateRecipes = new HibernateBundle<DatabaseConfiguration>(T_Recipe.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(DatabaseConfiguration configuration) {
            return configuration.getDatabase();
        }
    };


    @Override
    public void initialize(Bootstrap<DatabaseConfiguration> bootstrap){
        bootstrap.addBundle(hibernateUsers);
        bootstrap.addBundle(hibernateRecipes);
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle("/assets/", "/assets/"));
    }

    @Override
    public void run(DatabaseConfiguration conf, Environment env){
        SessionFactory session = hibernateUsers.getSessionFactory();
        final DAOUser users = new DAOUser(session);
        final DAORecipe recipe = new DAORecipe(hibernateRecipes.getSessionFactory());

        env.jersey().register(new UserResource(users));
        env.jersey().register(new RecipeResource(recipe));
    }
}
