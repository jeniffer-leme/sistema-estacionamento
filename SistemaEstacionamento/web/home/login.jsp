<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    //converter caracteres especiais da string em HTML
    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
%>
<%
    String msg = (String) request.getAttribute("msg");

    String id = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {
                id = cookie.getValue();
            }
        }
    }
%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Login | Sistema</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css">
    </head>
    <body>

        <div class="login-card">

            <div class="login-topo">
                <div class="logo">E</div>
                <h1>Acesso ao Sistema</h1>
            </div>

            <div class="login-corpo">

                <% if (msg != null) { %>
                    <div class="alert" role="alert"><%= esc(msg) %></div>
                <% } %>

                <form action="<%= request.getContextPath() %>/home?task=login" method="post">

                    <div class="input-group">
                        <label for="id">ID do usuário</label>
                        <input type="text" pattern="[0-9]*" inputmode="numeric" id="id" name="id"
                               value="<%= esc(id) %>" placeholder="Digite apenas números" required>
                    </div>

                    <div class="input-group">
                        <label for="senha">Senha</label>
                        <div class="senha-campo">
                            <input type="password" id="senha" name="senha"
                                   placeholder="Digite sua senha" required>
                            <button type="button" class="btn-ver" id="btnVer">Mostrar</button>
                        </div>
                    </div>
                    
                    <button type="submit" class="btn-submit">Entrar</button>

                </form>
            </div>
        </div>
            <script>
                var campo = document.getElementById("senha");
                var botao = document.getElementById("btnVer");

                botao.addEventListener("click", function () {
                    var oculta = campo.type === "password";
                    campo.type = oculta ? "text" : "password";
                    botao.textContent = oculta ? "Ocultar" : "Mostrar";
                });
            </script>
    </body>
</html>