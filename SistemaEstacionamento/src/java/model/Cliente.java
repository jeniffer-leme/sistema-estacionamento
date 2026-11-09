package model;

public class Cliente {
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    public Cliente(int id) {
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
        if( nome == null ) {
            throw new IllegalArgumentException("nome não pode null");
        }
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if( cpf == null ) {
            throw new IllegalArgumentException("CPF não pode null");
        }
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if( telefone == null ) {
            throw new IllegalArgumentException("telefone não pode null");
        }
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if( email == null ) {
            throw new IllegalArgumentException("email não pode null");
        }
        this.email = email;
    }
    
    
}
