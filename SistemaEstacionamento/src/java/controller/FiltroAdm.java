package controller;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.TipoUsuario;

public class FiltroAdm implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession sessao = req.getSession(false);
        TipoUsuario tp = (sessao != null) ? (TipoUsuario) sessao.getAttribute("tipo_usuario_sessao") : null;

        if (tp == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        if ("S".equals(tp.getModuloAdministrativo())) {
            chain.doFilter(request, response);
        } else {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            req.setAttribute("msg", "Você não tem permissão para acessar esta página.");
            req.getRequestDispatcher("/home/erro.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
    
    
}
