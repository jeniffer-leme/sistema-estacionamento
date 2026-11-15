<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    
    //POR ENQUANTO ERRO TEM SOMENTE EM LOGIN
    //VERIFICAR SE PRECISA EM OUTROS ARQUIVOS ANTES DE FINALIZAR
    
    // Escapa texto para exibir com segurança dentro do HTML
    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
%>
<%
    String msg = (String) request.getAttribute("msg");
    if (msg == null || msg.isEmpty()) {
        msg = "Ocorreu um erro inesperado. Tente novamente.";
    }
%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Erro | Sistema</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css">
    </head>
    <body>

        <div class="login-card">

            <div class="login-topo">
                <div class="logo">!</div>
                <h1>Algo deu errado</h1>
            </div>

            <div class="login-corpo">
                <p class="erro-texto"><%= esc(msg) %></p>
                <a href="<%= request.getContextPath() %>/" class="btn-submit">Voltar ao login</a>
            </div>

        </div>

    </body>
</html>