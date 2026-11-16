<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Usuario" %>
<%@page import="model.UsuarioDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Usuário</title>
    </head>
    <body>
        
        <%
            String action = "new";
            Usuario us = null;
            String id = request.getParameter("id");
            if( id != null ) {
                us = new UsuarioDAO().getUnique( Integer.parseInt( id ) );
                
                if( us != null ) action = "update";
            }
        %>
        
        <%@include file="/home/app/modulos.jsp"%>
        
        <h1>Cadastro Usuário</h1>
        
        <form action="/estacionamento/home?task=usuario&action=<%= action %>" method="post">
            
            <label for="id">Id:</label>
            <input type="text" pattern="[0-9]*" id="id" name="id" value="<%= us != null ? us.getId() : "" %>" required <%= us != null ? "readonly" : "" %>> <br/>
            
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" value="<%= us != null ? us.getEmail(): "" %>"><br/>
            
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" value="<%= us != null ? us.getNome() : "" %>"><br/>
            
            <label for="senha">Senha:</label>
            <input type="password" id="senha" name="senha" value="<%= us != null ? us.getSenha() : "" %>" required><br/>
            
            <label for="tipo_usuario_id">Tipo Usuário Id:</label>
            <input type="text" pattern="[0-9]*" id="tipo_usuario_id" name="tipo_usuario_id" value="<%= us != null ? us.getTipoUsuarioId() : "" %>" required> <br/>
            
    </body>
</html>
