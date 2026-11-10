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
        
        preparedStatement.setObject(1, t.getVeiculoId());
        preparedStatement.setObject( 2, t.getVagaId());
        preparedStatement.setObject( 3, t.getUsuarioId());
        
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
        
        preparedStatement.setString( 1, t.getPlaca());
        preparedStatement.setString( 2, t.getModelo());
        preparedStatement.setString( 3, t.getCor());
        preparedStatement.setString( 4, t.getTipo());
        
        preparedStatement.setObject( 5, t.getClienteId());
        
        preparedStatement.setInt( 6, t.getId() );
        
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
            
            resultado.setPlaca((String) resultSet.getObject(2) );
            resultado.setModelo((String) resultSet.getObject(3) );
            resultado.setCor((String) resultSet.getObject(4) );
            resultado.setTipo((String) resultSet.getObject(5) );
            
            if( resultSet.getObject(6) == null )  {
                resultado.setClienteId(null );
            } else {
                resultado.setClienteId( (int) resultSet.getObject(6) );
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
            
            movimentacao.setPlaca((String) resultSet.getObject( 2 ) );
            movimentacao.setModelo((String) resultSet.getObject( 3 ) );
            movimentacao.setCor((String) resultSet.getObject(4) );
            movimentacao.setTipo((String) resultSet.getObject(5) );
            
            if( resultSet.getObject(6) == null )  {
                movimentacao.setClienteId(null );
            } else {
                movimentacao.setClienteId( (int) resultSet.getObject(6) );
            }
            
            resultado.add(movimentacao);
            
        }
        
        resultSet.close();
        statement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
        return resultado;
        
    }

}