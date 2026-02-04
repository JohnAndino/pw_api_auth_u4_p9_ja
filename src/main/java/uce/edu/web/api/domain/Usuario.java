package uce.edu.web.api.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "usuario_seq", sequenceName = "usuarios_secuencia", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    public Long id;

    public String username;
    public String password;
    public String role;

    public static Usuario findByUsername(String username) {
        return find("username", username).firstResult();
    }
}