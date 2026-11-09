package model;

public class Veiculo {
    private int id;
    private String placa;
    private String modelo;
    private String cor;
    private String tipo;
    private Integer clienteId;

    public Veiculo(int id) {
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

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
         if( placa == null ) {
            throw new IllegalArgumentException("placa não pode null");
        }
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if( modelo == null ) {
            throw new IllegalArgumentException("modelo não pode null");
        }
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        if( cor == null ) {
            throw new IllegalArgumentException("cor não pode null");
        }
        this.cor = cor;
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

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        if( clienteId < 0 ) {
            throw new IllegalArgumentException("clienteId não pode ser < 0");
        }
        this.clienteId = clienteId;
    }
    
    
}
