package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import framework.config.AppConfig;
import framework.dao.DataAccessObject;
import framework.dao.DataBaseConnections;
import java.sql.Statement;

public class VeiculoDAO extends DataAccessObject <Veiculo> {

    @Override
    public void insert(Veiculo t) throws Exception {
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "INSERT INTO veiculo (placa, modelo, cor, tipo, cliente_id) values (?, ?, ?, ?, ?)";
        
        PreparedStatement preparedStatement = connection.prepareStatement(dml);
        
        preparedStatement.setString( 1, t.getPlaca());
        preparedStatement.setString( 2, t.getModelo());
        preparedStatement.setString( 3, t.getCor());
        preparedStatement.setString( 4, t.getTipo());
        
        preparedStatement.setObject( 5, t.getClienteId());
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        preparedStatement.execute();
        
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
    }

    @Override
    public void update(Veiculo t) throws Exception {
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "UPDATE veiculo SET id = ?, placa = ?, modelo = ?, cor = ?, tipo = ?, cliente_id = ? WHERE id = ?";
        
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
    public void delete(Veiculo t) throws Exception {
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "DELETE FROM veiculo WHERE id = ?";
        
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
    public Veiculo getUnique(Object... values) throws Exception {
        
        Veiculo resultado = null;
        
        String dql = "SELECT * FROM veiculo WHERE id = ?";
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        PreparedStatement preparedStatement = connection.prepareStatement(dql);
        
        preparedStatement.setInt( 1,  (int) values[0] );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        boolean status = resultSet.next();
        
        if( status == true ) {
            
            resultado = new Veiculo( (int) resultSet.getObject(1) );
            
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
    public ArrayList<Veiculo> getAll() throws Exception {
        
        ArrayList<Veiculo> resultado = new ArrayList<>();
        
        String dql = "SELECT * FROM veiculo";
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(dql);
        
        while( resultSet.next() ) {
            
            Veiculo veiculo = new Veiculo( (int) resultSet.getObject( 1 ) );
            
            veiculo.setPlaca((String) resultSet.getObject( 2 ) );
            veiculo.setModelo((String) resultSet.getObject( 3 ) );
            veiculo.setCor((String) resultSet.getObject(4) );
            veiculo.setTipo((String) resultSet.getObject(5) );
            
            if( resultSet.getObject(6) == null )  {
                veiculo.setClienteId(null );
            } else {
                veiculo.setClienteId( (int) resultSet.getObject(6) );
            }
            
            resultado.add(veiculo);
            
        }
        
        resultSet.close();
        statement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
        return resultado;
        
    }

}