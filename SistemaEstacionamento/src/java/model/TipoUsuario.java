package model;

public class TipoUsuario {
    
    private int id;
    private String nome;
    private String moduloAdministrativo;
    private String moduloAtendimento;
    private String moduloOperacao;

    public TipoUsuario(int id) {
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
            throw new IllegalArgumentException("nome não pode ser null");
        }
        this.nome = nome;
    }

    public String getModuloAdministrativo() {
        return moduloAdministrativo;
    }

    public void setModuloAdministrativo(String moduloAdministrativo) {
        if( moduloAdministrativo == null ) {
            throw new IllegalArgumentException("moduloAdministrativo não pode ser null");
        }
        this.moduloAdministrativo = moduloAdministrativo;
    }

    public String getModuloAtendimento() {
        return moduloAtendimento;
    }

    public void setModuloAtendimento(String moduloAtendimento) {
        if( moduloAtendimento == null ) {
            throw new IllegalArgumentException("moduloAtendimento não pode ser null");
        }
        this.moduloAtendimento = moduloAtendimento;
    }

    public String getModuloOperacao() {
        return moduloOperacao;
    }

    public void setModuloOperacao(String moduloOperacao) {
        if( moduloOperacao == null ) {
            throw new IllegalArgumentException("moduloOperacao não pode ser null");
        }
        this.moduloOperacao = moduloOperacao;
    }
}