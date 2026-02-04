package uce.edu.web.api.interfaces;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.aplication.UsuarioService;
import uce.edu.web.api.aplication.representation.UsuarioRepresentation;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    private UsuarioService usuarioService;

    @GET
    public List<UsuarioRepresentation> listarTodos() {
        return this.usuarioService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public UsuarioRepresentation consultarPorId(@PathParam("id") Long id) {
        return this.usuarioService.consultarPorId(id);
    }

    @POST
    public Response crear(UsuarioRepresentation usuarioRep) {
        this.usuarioService.crear(usuarioRep);
        return Response.status(Response.Status.CREATED).entity(usuarioRep).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, UsuarioRepresentation usuarioRep) {
        this.usuarioService.actualizar(id, usuarioRep);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        this.usuarioService.eliminar(id);
        return Response.noContent().build();
    }
}
