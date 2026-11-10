package model;

public class Movimentacao {
    private int id;
    private Integer veiculoId;
    private Integer vagaId;
    private Integer usuarioId;
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

    public Integer getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Integer veiculoId) {
        if( veiculoId < 0 ) {
            throw new IllegalArgumentException("O id do veículo não pode ser < 0");
        }
        this.veiculoId = veiculoId;
    }

    public Integer getVagaId() {
        return vagaId;
    }

    public void setVagaId(Integer vagaId) {
        if( vagaId < 0 ) {
            throw new IllegalArgumentException("O id da vaga não pode ser < 0");
        }
        this.vagaId = vagaId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
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
        if (dataSaida == null) {
            throw new IllegalArgumentException("A data de saída não pode ser nula.");
        }
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
