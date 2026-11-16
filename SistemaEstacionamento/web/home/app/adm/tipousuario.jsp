<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.TipoUsuario" %>
<%@page import="model.TipoUsuarioDAO" %>

<%!
    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }

    // Mostra Sim ou Não com cor
    private static String badge(String valor) {
        return "S".equals(valor)
                ? "<span class=\"badge sim\">Sim</span>"
                : "<span class=\"badge nao\">Não</span>";
    }
%>

<%
    ArrayList<TipoUsuario> lista = new TipoUsuarioDAO().getAll();
    String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Tipo Usuários</title>
        <link rel="stylesheet" href="<%= ctx %>/css/tipousuario.css">
    </head>
    <body>

        <%@include file="/home/app/modulos.jsp"%>

        <div class="pagina">
            <div class="card">

                <div class="card-topo">
                    <h1>Tipo Usuários</h1>
                    <a href="<%= ctx %>/home/app/adm/tipousuario_form.jsp" class="btn btn-amarelo">+ Adicionar</a>
                </div>

                <div class="tabela-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nome</th>
                                <th>Administrativo</th>
                                <th>Atendimento</th>
                                <th>Operação</th>
                                <th class="col-acoes">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if (lista.isEmpty()) { %>
                                <tr>
                                    <td colspan="6" class="vazio">Nenhum tipo de usuário cadastrado.</td>
                                </tr>
                            <% } %>

                            <% for (TipoUsuario usTp : lista) { %>
                                <tr>
                                    <td><%= usTp.getId() %></td>
                                    <td><%= esc(usTp.getNome()) %></td>
                                    <td><%= badge(usTp.getModuloAdministrativo()) %></td>
                                    <td><%= badge(usTp.getModuloAtendimento()) %></td>
                                    <td><%= badge(usTp.getModuloOperacao()) %></td>
                                    <td class="col-acoes">
                                        <a class="link-alterar"
                                           href="<%= ctx %>/home/app/adm/tipousuario_form.jsp?id=<%= usTp.getId() %>">Alterar</a>
                                        <a class="link-excluir"
                                           href="<%= ctx %>/home?task=tipousuario&action=delete&id=<%= usTp.getId() %>"
                                           onclick="return confirm('Deseja realmente excluir o Tipo Usuário <%= usTp.getId() %>?')">Excluir</a>
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