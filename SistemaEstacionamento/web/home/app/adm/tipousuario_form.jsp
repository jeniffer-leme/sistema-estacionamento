<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.TipoUsuario" %>
<%@page import="model.TipoUsuarioDAO" %>

<%!
    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
%>

<%
    String action = "new";
    TipoUsuario tpUs = null;
    String id = request.getParameter("id");
    if (id != null) {
        tpUs = new TipoUsuarioDAO().getUnique(Integer.parseInt(id));
        if (tpUs != null) action = "update";
    }
%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Cadastro Tipo Usuário</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/tipousuario.css">
    </head>
    <body>

        <%@include file="/home/app/modulos.jsp"%>

        <div class="pagina estreita">
            <div class="card">

                <div class="card-topo">
                    <h1>Cadastro Tipo Usuário</h1>
                </div>

                <div class="card-corpo">
                    <form action="<%= request.getContextPath() %>/home?task=tipousuario&action=<%= action %>" method="post">

                        <div class="input-group">
                            <label for="id">Id</label>
                            <input type="text" pattern="[0-9]*" inputmode="numeric" id="id" name="id" value="<%= tpUs != null ? tpUs.getId() : "" %>" required <%= tpUs != null ? "readonly" : "" %>>
                        </div>

                        <div class="input-group">
                            <label for="nome">Nome</label>
                            <input type="text" id="nome" name="nome" value="<%= tpUs != null ? esc(tpUs.getNome()) : "" %>">
                        </div>

                        <div class="input-group">
                            <label>Módulos com acesso</label>

                            <label class="check">
                                <input type="checkbox" name="modulo_administrativo" value="S" <%= (tpUs != null && "S".equals(tpUs.getModuloAdministrativo())) ? "checked" : "" %>>
                                Módulo Administrativo
                            </label>

                            <label class="check">
                                <input type="checkbox" name="modulo_atendimento" value="S" <%= (tpUs != null && "S".equals(tpUs.getModuloAtendimento())) ? "checked" : "" %>>
                                Módulo Atendimento
                            </label>

                            <label class="check">
                                <input type="checkbox" name="modulo_operacao" value="S" <%= (tpUs != null && "S".equals(tpUs.getModuloOperacao())) ? "checked" : "" %>>
                                Módulo Operação
                            </label>
                        </div>

                        <div class="acoes-form">
                            <button type="submit" class="btn btn-submit">Salvar</button>
                            <a href="<%= request.getContextPath() %>/home/app/adm/tipousuario.jsp" class="btn btn-claro">Cancelar</a>
                        </div>

                    </form>
                </div>

            </div>
        </div>

    </body>
</html> 