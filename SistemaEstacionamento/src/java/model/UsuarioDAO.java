package model;

import framework.config.AppConfig;
import framework.dao.DataAccessObject;
import framework.dao.DataBaseConnections;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDAO extends DataAccessObject <Usuario> {

    @Override
    public void insert(Usuario t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "INSERT INTO usuario (nome, email, senha, perfil) values (?, ?, ?, ?)";
        
        PreparedStatement preparedStatement = connection.prepareStatement(dml);
        
        preparedStatement.setString( 1, t.getNome() );
        preparedStatement.setString( 2, t.getEmail());
        preparedStatement.setString( 3, t.getSenha() );
        preparedStatement.setString(4, t.getPerfil());
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        preparedStatement.execute();
        
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
    }

    @Override
    public void update(Usuario t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "UPDATE usuario SET nome = ?, email = ?, senha = ?, perfil = ? WHERE id = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(dml);
        
        preparedStatement.setString( 1, t.getNome() );
        preparedStatement.setString( 2, t.getEmail());
        preparedStatement.setString( 3, t.getSenha() );
        preparedStatement.setString(4, t.getPerfil());
        
        preparedStatement.setInt( 5, t.getId() );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        preparedStatement.execute();
        
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
    }

    @Override
    public void delete(Usuario t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "DELETE FROM usuario WHERE id = ?";
        
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
    public Usuario getUnique(Object... values) throws Exception {
        Usuario resultado = null;
        
        String dql = "SELECT * FROM usuario WHERE id = ?";
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        PreparedStatement preparedStatement = connection.prepareStatement(dql);
        
        preparedStatement.setInt( 1,  (int) values[0] );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        boolean status = resultSet.next();
        
        if( status == true ) {
            
            resultado = new Usuario( (int) resultSet.getObject(1) );
            
            resultado.setNome( (String) resultSet.getObject(2) );
            resultado.setEmail((String) resultSet.getObject(3) );
            resultado.setSenhaHash( (String) resultSet.getObject(4) );
            resultado.setPerfil((String) resultSet.getObject(5) );
        }
        
        resultSet.close();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
        return resultado;
    }

    @Override
    public ArrayList<Usuario> getAll() throws Exception {
        ArrayList<Usuario> resultado = new ArrayList<>();
        
        String dql = "SELECT * FROM usuario";
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(dql);
        
        while( resultSet.next() ) {
            
            Usuario usuario = new Usuario( (int) resultSet.getObject( 1 ) );
            
            usuario.setNome( (String) resultSet.getObject( 2 ) );
            usuario.setEmail((String) resultSet.getObject( 3 ) );
            usuario.setSenhaHash( (String) resultSet.getObject( 4 ) );
            usuario.setPerfil((String) resultSet.getObject(5) );
            
            resultado.add(usuario);
            
        }
        
        resultSet.close();
        statement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
        return resultado;
    }
    
    
}
