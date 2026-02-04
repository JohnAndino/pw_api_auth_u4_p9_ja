package uce.edu.web.api.aplication;

import java.util.ArrayList;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.aplication.representation.UsuarioRepresentation;
import uce.edu.web.api.domain.Usuario;
import uce.edu.web.api.infraestructure.UsuarioRepository;

@ApplicationScoped
public class UsuarioService {

    @Inject
    private UsuarioRepository usuarioRepository;

    public List<UsuarioRepresentation> listarTodos() {
        List<UsuarioRepresentation> list = new ArrayList<>();
        for (Usuario usr : this.usuarioRepository.listAll()) {
            list.add(this.mapperToRepresentation(usr));
        }
        return list;
    }

    public UsuarioRepresentation consultarPorId(Long id) {
        Usuario usr = this.usuarioRepository.findById(id);
        if (usr == null) {
            return null;
        }
        return this.mapperToRepresentation(usr);
    }

    @Transactional
    public void crear(UsuarioRepresentation representation) {
        Usuario nuevo = this.mapperToEntity(representation);
        this.usuarioRepository.persist(nuevo);
    }

    @Transactional
    public void actualizar(Long id, UsuarioRepresentation representation) {
        Usuario usr = this.usuarioRepository.findById(id);
        if (usr != null) {
            usr.username = representation.getUsername();
            usr.password = representation.getPassword();
            usr.role = representation.getRole();
        }
    }

    @Transactional
    public void eliminar(Long id) {
        this.usuarioRepository.deleteById(id);
    }

    private UsuarioRepresentation mapperToRepresentation(Usuario entity) {
        UsuarioRepresentation rep = new UsuarioRepresentation();
        rep.setId(entity.id);
        rep.setUsername(entity.username);
        rep.setRole(entity.role);
        rep.setPassword(entity.password);
        return rep;
    }

    private Usuario mapperToEntity(UsuarioRepresentation rep) {
        Usuario entity = new Usuario();
        entity.id = rep.getId();
        entity.username = rep.getUsername();
        entity.password = rep.getPassword();
        entity.role = rep.getRole();
        return entity;
    }
}