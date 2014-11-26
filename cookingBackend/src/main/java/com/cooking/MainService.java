package com.cooking;

import com.cooking.conf.DatabaseConfiguration;
import com.cooking.core.DAORecipe;
import com.cooking.core.DAOUser;
import com.cooking.core.T_Recipe;
import com.cooking.core.T_User;
import com.cooking.resources.RecipeResource;
import com.cooking.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
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

    private final HibernateBundle<DatabaseConfiguration> hibernate = new HibernateBundle<DatabaseConfiguration>(T_User.class, T_Recipe.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(DatabaseConfiguration databaseConfiguration) {
            return databaseConfiguration.getDatabase();
        }
    };

    @Override
    public void initialize(Bootstrap<DatabaseConfiguration> bootstrap){
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle("/assets/", "/assets/"));
    }

    @Override
    public void run(DatabaseConfiguration conf, Environment env){
        SessionFactory session = hibernate.getSessionFactory();
        final DAOUser users = new DAOUser(session);
        final DAORecipe recipe = new DAORecipe(session);

        env.jersey().register(new UserResource(users));
        env.jersey().register(new RecipeResource(recipe));
    }
}
