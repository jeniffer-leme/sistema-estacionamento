package model;

public class Movimentacao {
    private int id;
    private int veiculoId;
    private int vagaId;
    private int usuarioId;
    private String dataEntrada;
    private String dataSaida;
    private double valor;

    public Movimentacao(int id) {
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

    public int getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(int veiculoId) {
        if( veiculoId < 0 ) {
            throw new IllegalArgumentException("O id do veículo não pode ser < 0");
        }
        this.veiculoId = veiculoId;
    }

    public int getVagaId() {
        return vagaId;
    }

    public void setVagaId(int vagaId) {
        if( vagaId < 0 ) {
            throw new IllegalArgumentException("O id da vaga não pode ser < 0");
        }
        this.vagaId = vagaId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        if( usuarioId < 0 ) {
            throw new IllegalArgumentException("O id do usuário não pode ser < 0");
        }
        this.usuarioId = usuarioId;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        if (dataEntrada == null) {
            throw new IllegalArgumentException("A data de entrada não pode ser nula.");
        }
        this.dataEntrada = dataEntrada;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if( valor < 0.0 ) {
            throw new IllegalArgumentException("valor não pode ser < 0.0");
        }
        this.valor = valor;
    }
    
    
}
