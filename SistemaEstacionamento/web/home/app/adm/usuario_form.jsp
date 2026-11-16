<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    String action = "new";
    Usuario us = null;
    String id = request.getParameter("id");
    if (id != null) {
        us = new UsuarioDAO().getUnique(Integer.parseInt(id));
        if (us != null) action = "update";
    }
%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Cadastro Usuário</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/usuario.css">
    </head>
    <body>

        <%@include file="/home/app/modulos.jsp"%>

        <div class="pagina estreita">
            <div class="card">

                <div class="card-topo">
                    <h1>Cadastro Usuário</h1>
                </div>

                <div class="card-corpo">
                    <form action="<%= request.getContextPath() %>/home?task=usuario&action=<%= action %>" method="post">

                        <div class="input-group">
                            <label for="id">Id</label>
                            <input type="text" pattern="[0-9]*" id="id" name="id"
                                   value="<%= us != null ? us.getId() : "" %>"
                                   required <%= us != null ? "readonly" : "" %>>
                        </div>

                        <div class="input-group">
                            <label for="email">Email</label>
                            <input type="text" id="email" name="email"
                                   value="<%= us != null ? esc(us.getEmail()) : "" %>">
                        </div>

                        <div class="input-group">
                            <label for="nome">Nome</label>
                            <input type="text" id="nome" name="nome"
                                   value="<%= us != null ? esc(us.getNome()) : "" %>">
                        </div>

                        <div class="input-group">
                            <label for="senha">Senha</label>
                            <input type="password" id="senha" name="senha"
                                   value="<%= us != null ? esc(us.getSenha()) : "" %>" required>
                        </div>

                        <div class="input-group">
                            <label for="tipo_usuario_id">Tipo Usuário Id</label>
                            <input type="text" pattern="[0-9]*" id="tipo_usuario_id" name="tipo_usuario_id"
                                   value="<%= us != null ? us.getTipoUsuarioId() : "" %>" required>
                        </div>

                        <div class="acoes-form">
                            <button type="submit" class="btn btn-submit">Salvar</button>
                            <a href="<%= request.getContextPath() %>/home/app/adm/usuario.jsp" class="btn btn-claro">Cancelar</a>
                        </div>

                    </form>
                </div>

            </div>
        </div>

    </body>
</html>