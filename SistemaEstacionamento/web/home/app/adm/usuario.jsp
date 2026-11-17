<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Usuario" %>
<%@page import="model.UsuarioDAO" %>
<%!
    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
%>

<%
    ArrayList<Usuario> lista = new UsuarioDAO().getAll();
    String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Usuários</title>
        <link rel="stylesheet" href="<%= ctx %>/css/usuario.css">
    </head>
    <body>

        <%@include file="/home/app/modulos.jsp"%>

        <div class="pagina">
            <div class="card">

                <div class="card-topo">
                    <h1>Usuários</h1>
                    <a href="<%= ctx %>/home/app/adm/usuario_form.jsp" class="btn btn-amarelo">+ Adicionar</a>
                </div>

                <div class="tabela-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nome</th>
                                <th>Email</th>
                                <th>Tipo Usuário Id</th>
                                <th class="col-acoes">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Usuario us : lista) { %>
                                <tr>
                                    <td><%= us.getId() %></td>
                                    <td><%= esc(us.getNome()) %></td>
                                    <td><%= esc(us.getEmail()) %></td>
                                    <td><%= us.getTipoUsuarioId() %></td>
                                    <td class="col-acoes">
                                        <a class="link-alterar"
                                           href="<%= ctx %>/home/app/adm/usuario_form.jsp?id=<%= us.getId() %>">Alterar</a>
                                        <a class="link-excluir"
                                           href="<%= ctx %>/home?task=usuario&action=delete&id=<%= us.getId() %>"
                                           onclick="return confirm('Deseja realmente excluir o Usuário <%= us.getId() %>?')">Excluir</a>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>

    </body>
</html>