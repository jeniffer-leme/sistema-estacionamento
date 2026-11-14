package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import framework.config.AppConfig;
import framework.dao.DataAccessObject;
import framework.dao.DataBaseConnections;
import java.sql.Statement;

public class MovimentacaoDAO extends DataAccessObject <Movimentacao> {

    @Override
    public void insert(Movimentacao t) throws Exception {
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "INSERT INTO movimentacao (veiculo_id, vaga_id, usuario_id, data_entrada, data_saida, valor) values (?, ?, ?, ?, ?, ?)";
        
        PreparedStatement preparedStatement = connection.prepareStatement(dml);
        
        preparedStatement.setInt(1, t.getVeiculoId());
        preparedStatement.setInt( 2, t.getVagaId());
        preparedStatement.setInt( 3, t.getUsuarioId());
        
        preparedStatement.setString( 4, t.getDataEntrada());
        preparedStatement.setString( 5, t.getDataSaida());
        
        preparedStatement.setDouble(6, t.getValor());
        
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        preparedStatement.execute();
        
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
    }

    @Override
    public void update(Movimentacao t) throws Exception {
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "UPDATE movimentacao SET veiculo_id = ?, vaga_id = ?, usuario_id = ?, data_entrada = ?, data_saida = ?, valor = ? WHERE id = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(dml);
        
        preparedStatement.setInt(1, t.getVeiculoId());
        preparedStatement.setInt( 2, t.getVagaId());
        preparedStatement.setInt( 3, t.getUsuarioId());
        
        preparedStatement.setString( 4, t.getDataEntrada());
        preparedStatement.setString( 5, t.getDataSaida());
        
        preparedStatement.setDouble(6, t.getValor());
        
        preparedStatement.setInt(7, t.getId());
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        preparedStatement.execute();
        
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
    }

    @Override
    public void delete(Movimentacao t) throws Exception {
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "DELETE FROM movimentacao WHERE id = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(dml);
        
        preparedStatement.setInt( 1, t.getId() );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        preparedStatement.execute();
        
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
    }

    @Override
    public Movimentacao getUnique(Object... values) throws Exception {
        
        Movimentacao resultado = null;
        
        String dql = "SELECT * FROM movimentacao WHERE id = ?";
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        PreparedStatement preparedStatement = connection.prepareStatement(dql);
        
        preparedStatement.setInt( 1,  (int) values[0] );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        boolean status = resultSet.next();
        
        if( status == true ) {
            
            resultado = new Movimentacao( (int) resultSet.getObject(1) );
            
            resultado.setVeiculoId((int) resultSet.getObject(2) );
            resultado.setVagaId((int) resultSet.getObject(3) );
            resultado.setUsuarioId((int) resultSet.getObject(4) );
            
            resultado.setDataEntrada((String) resultSet.getObject(5) );
            
            if( resultSet.getObject(6) != null ) { // date para string
                resultado.setDataSaida(resultSet.getObject(6).toString() ); // 'nascimento date' na tabela (tratar da mesma forma para outros campos não 'varchar', como 'hour'
            }
            
            if( resultSet.getObject(7) != null ) {
                float aux = (float) resultSet.getObject(7);
                resultado.setValor( aux );
            }
            
        }
        
        resultSet.close();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
        return resultado;
        
    }

    @Override
    public ArrayList<Movimentacao> getAll() throws Exception {
        
        ArrayList<Movimentacao> resultado = new ArrayList<>();
        
        String dql = "SELECT * FROM movimentacao";
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(dql);
        
        while( resultSet.next() ) {
            
            Movimentacao movimentacao = new Movimentacao( (int) resultSet.getObject( 1 ) );
            
            movimentacao.setVeiculoId((int) resultSet.getObject(2) );
            movimentacao.setVagaId((int) resultSet.getObject(3) );
            movimentacao.setUsuarioId((int) resultSet.getObject(4) );
            
            movimentacao.setDataEntrada((String) resultSet.getObject( 5 ) );
            if( resultSet.getObject(6) != null ) {
                movimentacao.setDataSaida(resultSet.getObject(6).toString() ); 
            }
            
            if( resultSet.getObject(7) != null ) {
                float aux = (float) resultSet.getObject(7);
                movimentacao.setValor( aux );
            }
            
            
            resultado.add(movimentacao);
            
        }
        
        resultSet.close();
        statement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
        return resultado;
        
    }

}