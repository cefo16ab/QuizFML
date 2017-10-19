package server.endpoints;

import com.google.gson.Gson;
import server.DBWrapper;
import server.Controllers.UserController;
import server.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


@Path("/user")


public class UserEndpoint {

    UserController controller = new UserController();


    @GET
    public Response getUsers() {

        ArrayList<User> users = controller.getUsers();

        return Response.status(200)
                .type("application/json")
                .entity(new Gson().toJson(users))
                .build();

    }

    @GET
    @Path("{id}")

    public Response getUserById(@PathParam("id") int UserId) {

        // User foundUser

        return Response
            .status(200)
            .type("application/json")
            .entity(new Gson().toJson("foundUser"))
            .build();
    }

@POST
    public Response createUser(String jsonUser) {

        try {
            controller.createUser(new Gson().fromJson(jsonUser, User.class));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return Response
                .status(200)
                .type("application/json")
                .entity("{\"userCreated\":\"true\"}")
                .build();
    }


    @POST
        public Response createAdmin(String jsonAdmin) {

        try {
            controller.createUser(new Gson().fromJson(jsonAdmin, User.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response
                .status(200)
                .type("application/json")
                .entity("{\"adminCreated\":\"true\"}")
                .build();
    @Path("/login")
    @POST
    public Response authorizeUser(String data) throws Exception {
        User u = uController.login(data);
        if (u.getUsername() != null) {
            return Response.status(200).entity(new Gson().toJson(u)).build();
        } else {
            return Response.status(400).entity("failure!").build();
        }

    }


}