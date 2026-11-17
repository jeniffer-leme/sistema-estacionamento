<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Menu</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/menu.css">
    </head>
    <body>

        <%@include file="/home/app/modulos.jsp"%>

        <div class="pagina">

            <div class="boas-vindas">
                <h1>Olá, <%= nomeUsuarioSessao %></h1>
                <p>Escolha uma opção para começar.</p>
            </div>

            <% if( ehAdministrador ) { %>
                <div class="atalhos">

                    <a class="atalho azul" href="<%= menuCtx %>/home/app/adm/tipousuario.jsp">
                        <strong>Tipos Usuário</strong>
                        <span>Perfis e módulos de acesso</span>
                    </a>

                    <a class="atalho verde" href="<%= menuCtx %>/home/app/adm/usuario.jsp">
                        <strong>Usuários</strong>
                        <span>Quem pode entrar no sistema</span>
                    </a>

                    <a class="atalho asfalto" href="<%= menuCtx %>/home/app/adm/vaga.jsp">
                        <strong>Vagas</strong>
                        <span>Setores, tipos e status</span>
                    </a>

                </div>
            <% } else { %>
                <p class="sem-modulo">Nenhum módulo disponível para o seu perfil.</p>
            <% } %>

        </div>

    </body>
</html>