package org.openapitools.api;

import org.openapitools.model.PetFood;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/food")
public class PetFoodApi {

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePetFood(PetFood petFood) {
        // Implementation code goes here
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPetFood(PetFood petFood) {
        // Implementation code goes here
        return Response.ok().build();
    }

    @GET
    @Path("/findByStatus")
    public Response findPetFoodByStatus(@QueryParam("status") String status) {
        // Implementation code goes here
        return Response.ok().build();
    }

    @GET
    @Path("/findFoodByName")
    public Response findPetFoodByName(@QueryParam("name") String name) {
        // Implementation code goes here
        return Response.ok().build();
    }

    @GET
    @Path("/{foodId}")
    public Response getPetFoodById(@PathParam("foodId") Long foodId) {
        // Implementation code goes here
        return Response.ok().build();
    }

    @POST
    @Path("/{foodId}")
    public Response updatePetFoodWithForm(
            @PathParam("foodId") Long foodId,
            @FormParam("name") String name,
            @FormParam("status") String status) {
        // Implementation code goes here
        return Response.ok().build();
    }

    @DELETE
    @Path("/{foodId}")
    public Response deletePet(@PathParam("foodId") Long foodId) {
        // Implementation code goes here
        return Response.ok().build();
    }
}
