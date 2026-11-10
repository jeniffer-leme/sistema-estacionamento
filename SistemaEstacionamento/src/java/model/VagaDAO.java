package model;

import framework.config.AppConfig;
import framework.dao.DataAccessObject;
import framework.dao.DataBaseConnections;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class VagaDAO extends DataAccessObject <Vaga> {

    @Override
    public void insert(Vaga t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "INSERT INTO vaga (numero, setor, tipo, status) values (?, ?, ?, ?)";
        
        PreparedStatement preparedStatement = connection.prepareStatement(dml);
        
        preparedStatement.setString( 1, t.getNumero());
        preparedStatement.setString( 2, t.getSetor());
        preparedStatement.setString(3, t.getTipo());
        preparedStatement.setString( 4, t.getStatus());
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        preparedStatement.execute();
        
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
    }

    @Override
    public void update(Vaga t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "UPDATE vaga SET numero = ?, setor = ?, tipo = ?, status = ? WHERE id = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(dml);
        
        preparedStatement.setString( 1, t.getNumero());
        preparedStatement.setString( 2, t.getSetor());
        preparedStatement.setString(3, t.getTipo());
        preparedStatement.setString( 4, t.getStatus());
        
        preparedStatement.setInt( 5, t.getId() );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        preparedStatement.execute();
        
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
    }

    @Override
    public void delete(Vaga t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "DELETE FROM vaga WHERE id = ?";
        
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
    public Vaga getUnique(Object... values) throws Exception {
        Vaga resultado = null;
        
        String dql = "SELECT * FROM vaga WHERE id = ?";
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        PreparedStatement preparedStatement = connection.prepareStatement(dql);
        
        preparedStatement.setInt( 1,  (int) values[0] );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        boolean status = resultSet.next();
        
        if( status == true ) {
            
            resultado = new Vaga( (int) resultSet.getObject(1) );
            
            resultado.setNumero((String) resultSet.getObject(2) );
            resultado.setSetor((String) resultSet.getObject(3) );
            resultado.setTipo((String) resultSet.getObject(4) );
            resultado.setStatus((String) resultSet.getObject(5) );
        }
        
        resultSet.close();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
        return resultado;
    }

    @Override
    public ArrayList<Vaga> getAll() throws Exception {
        ArrayList<Vaga> resultado = new ArrayList<>();
        
        String dql = "SELECT * FROM vaga";
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(dql);
        
        while( resultSet.next() ) {
            
            Vaga vaga = new Vaga( (int) resultSet.getObject( 1 ) );
            
            vaga.setNumero((String) resultSet.getObject( 2 ) );
            vaga.setSetor((String) resultSet.getObject( 3 ) );
            vaga.setTipo((String) resultSet.getObject( 4 ) );
            vaga.setStatus((String) resultSet.getObject(5) );
            
            resultado.add(vaga);
            
        }
        
        resultSet.close();
        statement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
        return resultado;
    }
    
    
}
