package model;

import framework.util.Hash;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String perfil;

    public Usuario(int id) {
        setId(id);
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if( id < 0 ) {
            throw new IllegalArgumentException("id não pode ser < 0");
        }
        
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws NoSuchAlgorithmException,  UnsupportedEncodingException{
        if( senha == null ) {
            throw new IllegalArgumentException("senha não pode ser null");
        }
        
        String aux = Integer.toString( getId() ) + senha;
        String hash = Hash.stringToHash( aux, "SHA-256" );
        this.senha = hash;
    }
    
    public void setSenhaHash(String senha) {
        
        if( senha == null ) {
            throw new IllegalArgumentException("senha não pode ser null");
        }
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    
    
}
