<%@page import="model.Usuario"%>
<%@page import="model.TipoUsuario"%>

<%
    String nomeUsuarioSessao = "";
    if( ( session != null ) && ( session.getAttribute("usuario_sessao") != null ) ) {
        Usuario usuarioSessao = (Usuario) session.getAttribute("usuario_sessao");
        nomeUsuarioSessao = usuarioSessao.getId() + " " + usuarioSessao.getNome();
    }

    TipoUsuario tipoUsuarioSessao = null;
    if( ( session != null ) && ( session.getAttribute("tipo_usuario_sessao") != null ) ) {
        tipoUsuarioSessao = (TipoUsuario) session.getAttribute("tipo_usuario_sessao");
    }

    boolean ehAdministrador = ( tipoUsuarioSessao != null ) && "S".equals( tipoUsuarioSessao.getModuloAdministrativo() );

    String ctx = request.getContextPath();
    String uri = request.getRequestURI();
%>

<link rel="stylesheet" href="<%= ctx %>/css/modulos.css">

<nav class="menu-barra">
    <div class="menu-conteudo">

        <a class="menu-marca" href="<%= ctx %>/home/app/menu.jsp">
            <span class="menu-p">P</span>Estacionamento
        </a>

        <a class="menu-link <%= uri.endsWith("/menu.jsp") ? "ativo" : "" %>"
           href="<%= ctx %>/home/app/menu.jsp">Home</a>

        <% if( ehAdministrador ) { %>
            <a class="menu-link <%= uri.contains("/tipousuario") ? "ativo" : "" %>"
               href="<%= ctx %>/home/app/adm/tipousuario.jsp">Tipos Usuário</a>

            <a class="menu-link <%= uri.contains("/usuario") ? "ativo" : "" %>"
               href="<%= ctx %>/home/app/adm/usuario.jsp">Usuários</a>

            <a class="menu-link <%= uri.contains("/vaga") ? "ativo" : "" %>"
               href="<%= ctx %>/home/app/adm/vaga.jsp">Vagas</a>
        <% } %>

        <span class="menu-espaco"></span>

        <span class="menu-usuario"><%= nomeUsuarioSessao %></span>
        <a class="menu-sair" href="<%= ctx %>/home?task=logout">Sair</a>

    </div>
</nav>