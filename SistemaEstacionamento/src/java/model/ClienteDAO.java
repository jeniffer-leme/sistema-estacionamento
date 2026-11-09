package model;

import framework.config.AppConfig;
import framework.dao.DataAccessObject;
import framework.dao.DataBaseConnections;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteDAO extends DataAccessObject <Cliente> {

    @Override
    public void insert(Cliente t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "INSERT INTO cliente (nome, cpf, telefone, email) values (?, ?, ?, ?, ?)";
        
        PreparedStatement preparedStatement = connection.prepareStatement(dml);
        
        preparedStatement.setString( 1, t.getNome() );
        preparedStatement.setString( 2, t.getCpf());
        preparedStatement.setString(3, t.getTelefone());
        preparedStatement.setString( 4, t.getEmail());
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        preparedStatement.execute();
        
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
    }

    @Override
    public void update(Cliente t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "UPDATE cliente SET nome = ?, cpf = ?, telefone = ?, email = ? WHERE id = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(dml);
        
        preparedStatement.setString( 1, t.getNome() );
        preparedStatement.setString( 2, t.getCpf());
        preparedStatement.setString(3, t.getTelefone());
        preparedStatement.setString( 4, t.getEmail());
        
        preparedStatement.setInt( 5, t.getId() );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        preparedStatement.execute();
        
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
    }

    @Override
    public void delete(Cliente t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "DELETE FROM cliente WHERE id = ?";
        
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
    public Cliente getUnique(Object... values) throws Exception {
        Cliente resultado = null;
        
        String dql = "SELECT * FROM cliente WHERE id = ?";
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        PreparedStatement preparedStatement = connection.prepareStatement(dql);
        
        preparedStatement.setInt( 1,  (int) values[0] );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        boolean status = resultSet.next();
        
        if( status == true ) {
            
            resultado = new Cliente( (int) resultSet.getObject(1) );
            
            resultado.setNome( (String) resultSet.getObject(2) );
            resultado.setCpf((String) resultSet.getObject(3) );
            resultado.setTelefone((String) resultSet.getObject(4) );
            resultado.setEmail((String) resultSet.getObject(5) );
        }
        
        resultSet.close();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
        return resultado;
    }

    @Override
    public ArrayList<Cliente> getAll() throws Exception {
        ArrayList<Cliente> resultado = new ArrayList<>();
        
        String dql = "SELECT * FROM cliente";
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(dql);
        
        while( resultSet.next() ) {
            
            Cliente cliente = new Cliente( (int) resultSet.getObject( 1 ) );
            
            cliente.setNome( (String) resultSet.getObject( 2 ) );
            cliente.setCpf((String) resultSet.getObject( 3 ) );
            cliente.setTelefone((String) resultSet.getObject( 4 ) );
            cliente.setEmail((String) resultSet.getObject(5) );
            
            resultado.add(cliente);
            
        }
        
        resultSet.close();
        statement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
        return resultado;
    }
    
    
}
