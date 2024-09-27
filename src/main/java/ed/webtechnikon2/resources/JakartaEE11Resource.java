package ed.webtechnikon2.resources;

import ed.webtechnikon2.modeles.Admin;
import ed.webtechnikon2.modeles.User;
import ed.webtechnikon2.services.ManagerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author
 */
@Path("jakartaee11")
public class JakartaEE11Resource {

    private static Map<Integer, User> users = new HashMap<>();
    private static Map<Integer, Admin> admins = new HashMap<>();
    private final ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();

    static {
        users.put(1, new User(1, "John Doe", 123));
        admins.put(1, new Admin(1, "Jane Doe", 123));
    }

    @Inject
    private ManagerService managerService;

    @GET
    public Response ping() {
        return Response
                .ok("ping Jakarta EE")
                .build();
    }

    /**
     *
     * @param asyncResponse
     * @param id
     * @param updatedUser
     */
    // Update an existing user by ID
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(@jakarta.ws.rs.container.Suspended
            final jakarta.ws.rs.container.AsyncResponse asyncResponse, @PathParam(value = "id")
            final int id, final User updatedUser) {
        // the return value is lost , the resoutce does not return response but void
        executorService.submit(() -> {
            asyncResponse.resume(doUpdateUser(id, updatedUser));
        });
    }

    private Response doUpdateUser(@PathParam("id") int id, User updatedUser) {
        User user = users.get(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User not found with ID: " + id).build();
        }
        user.setUsername(updatedUser.getUsername());
        users.put(id, user);
        return Response.ok(user).build();
    }

}

