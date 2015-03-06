/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.first.*;
import facade.first.DBFacade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.status;

/**
 * REST Web Service
 *
 * @author Michael
 */
@Path("project")
public class RestAPI {

    private Gson gson = new GsonBuilder().create();
    private DBFacade dbf = new DBFacade();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public RestAPI() {
    }

    /**
     * Retrieves representation of an instance of REST.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getAllProjects() {
        List<Project> allProjects = dbf.getAllProjects();
        String toJson = gson.toJson(allProjects);
        return toJson;
    }

    @Path("{id}")
    @GET
    @Produces("application/json")
    public String getSpecificProject(@PathParam("id") String id) {
        try {
            Project p = dbf.findProject(Integer.parseInt(id));
            return gson.toJson(p);
        } catch (Exception e) {
            
            return Response.status(404).entity(gson.toJson("Project was not found")).type(MediaType.APPLICATION_JSON).build().getEntity().toString();
            
        }
        
    }
}
