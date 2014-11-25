package com.cooking.core;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Chris on 25.11.2014.
 */
public interface TableEntity<Entity> {

    public Entity findById(Long id);

    public List<Entity> findByAttribute(String attributename, Object attributeValue);

    public Response save(Entity entity);

    public Response delete(Entity entity);

}
