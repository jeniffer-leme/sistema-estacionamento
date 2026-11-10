package model;

public class Vaga {
    private int id;
    private String numero;
    private String setor;
    private String tipo;
    private String status;

    public Vaga(int id) {
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if( numero == null ) {
            throw new IllegalArgumentException("numero não pode null");
        }
        this.numero = numero;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        if( setor == null ) {
            throw new IllegalArgumentException("setor não pode null");
        }
        this.setor = setor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if( tipo == null ) {
            throw new IllegalArgumentException("tipo não pode null");
        }
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if( status == null ) {
            throw new IllegalArgumentException("status não pode null");
        }
        this.status = status;
    }
    
    
}
