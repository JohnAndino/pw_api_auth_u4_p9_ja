package uce.edu.web.api.infraestructure;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.domain.Usuario;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepositoryBase<Usuario, Long> {

}
