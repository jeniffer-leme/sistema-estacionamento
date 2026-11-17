<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Vaga" %>
<%@page import="model.VagaDAO" %>

<%!
    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }

    private static String rotulo(String s) {
        if (s == null || s.isEmpty()) return "";
        if (s.equals("MANUTENCAO")) return "Manutenção";
        if (s.equals("CAMINHAO")) return "Caminhão";
        if (s.equals("PCD")) return "PcD";
        return s.substring(0, 1) + s.substring(1).toLowerCase();
    }
%>

<%
    String action = "new";
    Vaga vg = null;
    String id = request.getParameter("id");
    if (id != null) {
        vg = new VagaDAO().getUnique(Integer.parseInt(id));
        if (vg != null) action = "update";
    }

    String[] tipos = {"CARRO", "MOTO", "CAMINHAO", "PCD"};
    String[] statusLista = {"LIVRE", "OCUPADA", "MANUTENCAO"};

    String tipoAtual = (vg != null) ? vg.getTipo() : "";
    String statusAtual = (vg != null) ? vg.getStatus() : "LIVRE";
%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Cadastro Vaga</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/vaga.css">
    </head>
    <body>

        <%@include file="/home/app/modulos.jsp"%>

        <div class="pagina estreita">
            <div class="card asfalto">

                <div class="card-topo">
                    <h1>Cadastro Vaga</h1>
                </div>

                <div class="card-corpo">
                    <form action="<%= request.getContextPath() %>/home?task=vaga&action=<%= action %>" method="post">

                        <div class="input-group">
                            <label for="id">Id</label>
                            <input type="text" pattern="[0-9]*" id="id" name="id" value="<%= vg != null ? vg.getId() : "" %>" required <%= vg != null ? "readonly" : "" %>>
                        </div>

                        <div class="input-group">
                            <label for="numero">Número da vaga</label>
                            <input type="text" id="numero" name="numero" maxlength="10" value="<%= vg != null ? esc(vg.getNumero()) : "" %>" required>
                        </div>

                        <div class="input-group">
                            <label for="setor">Setor</label>
                            <input type="text" id="setor" name="setor" maxlength="30" value="<%= vg != null ? esc(vg.getSetor()) : "" %>" required>
                        </div>

                        <div class="input-group">
                            <label for="tipo">Tipo</label>
                            <select id="tipo" name="tipo">
                                <% for (String t : tipos) { %>
                                    <option value="<%= t %>" <%= t.equals(tipoAtual) ? "selected" : "" %>><%= rotulo(t) %></option>
                                <% } %>
                            </select>
                        </div>

                        <div class="input-group">
                            <label for="status">Status</label>
                            <select id="status" name="status">
                                <% for (String s : statusLista) { %>
                                    <option value="<%= s %>" <%= s.equals(statusAtual) ? "selected" : "" %>><%= rotulo(s) %></option>
                                <% } %>
                            </select>
                        </div>

                        <div class="acoes-form">
                            <button type="submit" class="btn btn-submit">Salvar</button>
                            <a href="<%= request.getContextPath() %>/home/app/adm/vaga.jsp" class="btn btn-claro">Cancelar</a>
                        </div>

                    </form>
                </div>

            </div>
        </div>

    </body>
</html>