<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Vaga" %>
<%@page import="model.VagaDAO" %>
<%!
    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }

    // Deixa o texto do banco mais bonito na tela (CARRO -> Carro)
    private static String rotulo(String s) {
        if (s == null || s.isEmpty()) return "";
        if (s.equals("MANUTENCAO")) return "Manutenção";
        if (s.equals("CAMINHAO")) return "Caminhão";
        if (s.equals("PCD")) return "PcD";
        return s.substring(0, 1) + s.substring(1).toLowerCase();
    }
%>
<%
    ArrayList<Vaga> lista = new VagaDAO().getAll();
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Vagas</title>
        <link rel="stylesheet" href="<%= ctx %>/css/vaga.css">
    </head>
    <body>

        <%@include file="/home/app/modulos.jsp"%>

        <div class="pagina">
            <div class="card asfalto">

                <div class="card-topo">
                    <h1>Vagas</h1>
                    <a href="<%= ctx %>/home/app/adm/vaga_form.jsp" class="btn btn-amarelo">+ Adicionar</a>
                </div>

                <div class="tabela-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th>Número</th>
                                <th>Setor</th>
                                <th>Tipo</th>
                                <th>Status</th>
                                <th class="col-acoes">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if (lista.isEmpty()) { %>
                                <tr>
                                    <td colspan="5" class="vazio">Nenhuma vaga cadastrada.</td>
                                </tr>
                            <% } %>

                            <% for (Vaga vg : lista) { %>
                                <tr>
                                    <td><span class="placa-vaga"><%= esc(vg.getNumero()) %></span></td>
                                    <td><%= esc(vg.getSetor()) %></td>
                                    <td><%= rotulo(vg.getTipo()) %></td>
                                    <td>
                                        <span class="badge st-<%= vg.getStatus().toLowerCase() %>">
                                            <%= rotulo(vg.getStatus()) %>
                                        </span>
                                    </td>
                                    <td class="col-acoes">
                                        <a class="link-alterar"
                                           href="<%= ctx %>/home/app/adm/vaga_form.jsp?id=<%= vg.getId() %>">Alterar</a>
                                        <a class="link-excluir"
                                           href="<%= ctx %>/home?task=vaga&action=delete&id=<%= vg.getId() %>"
                                           onclick="return confirm('Deseja realmente excluir a Vaga <%= esc(vg.getNumero()) %>?')">Excluir</a>
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