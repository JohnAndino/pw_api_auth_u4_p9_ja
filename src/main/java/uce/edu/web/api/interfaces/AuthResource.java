package uce.edu.web.api.interfaces;

import java.time.Instant;
import java.util.Set;

import io.smallrye.jwt.build.Jwt;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import uce.edu.web.api.domain.Usuario;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
   @GET
    @Path("/token")
    public TokenResponse token(@QueryParam("user") String user, 
                               @QueryParam("password") String password) {
        
        Usuario usuarioEncontrado = Usuario.findByUsername(user);
        
        if (usuarioEncontrado != null && usuarioEncontrado.password.equals(password)) {
            
            String role = usuarioEncontrado.role;

            String issuer = "matricula-auth";
            long ttl = 3600;

            Instant now = Instant.now();
            Instant exp = now.plusSeconds(ttl);

            String jwt = Jwt.issuer(issuer)
                    .subject(user)
                    .groups(Set.of(role))
                    .issuedAt(now)
                    .expiresAt(exp)
                    .sign();

            return new TokenResponse(jwt, exp.getEpochSecond(), role);
        } else {
            return null; 
        }
    }
}
