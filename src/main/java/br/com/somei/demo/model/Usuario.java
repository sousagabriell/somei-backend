package br.com.somei.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String foto;
    private String email;
    private String telefone;
    private String genero;
    private String password;
    private String tipoUsuario;
    private boolean tipoMei;

    // Construtores, getters e setters aqui...

    // Construtor padrão
    public Usuario() {
    }

    // Construtor com todos os campos
    public Usuario(String name, String foto, String email, String telefone, String genero, String password, String tipoUsuario, boolean tipoMei) {
        this.name = name;
        this.foto = foto;
        this.email = email;
        this.telefone = telefone;
        this.genero = genero;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.tipoMei = tipoMei;
    }

    // Getters e setters aqui...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isTipoMei() {
        return tipoMei;
    }

    public void setTipoMei(boolean tipoMei) {
        this.tipoMei = tipoMei;
    }
}