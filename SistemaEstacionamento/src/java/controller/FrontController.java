package controller;

import framework.log.ExceptionLogTrack;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.TipoUsuario;
import model.TipoUsuarioDAO;
import model.Usuario;
import model.UsuarioDAO;
import model.Vaga;
import model.VagaDAO;

public class FrontController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String task = req.getParameter("task");
        
        try {
        
            switch (task) {
                
                case "login": doDefault(req, resp); break;
                
                case "logout": doGetLogout(req, resp); break;

                case "tipousuario": doGetTipoUsuario(req, resp); break;
                
                case "usuario": doGetUsuario(req, resp); break;
                
                case "vaga": doGetVaga(req, resp); break;

                case null:
                default:
                    doDefault(req, resp);

            }
            
        } catch(Exception ex) {
            ExceptionLogTrack.getInstance().addLog(ex);
            throw new ServletException( ex );
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String task = req.getParameter("task");
        
        try {
        
            switch (task) {

                case "login": doPostLogin(req, resp); break;
                
                case "tipousuario": doPostTipoUsuario(req, resp); break;
                
                case "usuario": doPostUsuario(req, resp); break;
                
                case "vaga": doPostVaga(req, resp); break;

                case null:
                default:
                    doDefault(req, resp);

            }
            
        } catch(Exception ex) {
            ExceptionLogTrack.getInstance().addLog(ex);
            throw new ServletException( ex );
        }
        
    }

    private void doDefault(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        req.getRequestDispatcher("/home/login.jsp").forward(req, resp);
        
    }
    
    
    //VER QUAIS VÃO SER REMOVIDOS NO LOGOUT
    private void doGetLogout(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        HttpSession sessao = req.getSession(false);
        if( sessao != null ) {
            sessao.removeAttribute("tipo_usuario_sessao");
            sessao.removeAttribute("usuario_sessao");
            sessao.invalidate();
        }
                
        req.getRequestDispatcher("/home/login.jsp").forward(req, resp);
        
        
    }
    
    private void doGetTipoUsuario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        String action = req.getParameter("action");
        
        if( ( action != null ) && ( action.equals("delete") ) ) {
            
            int id = Integer.parseInt( req.getParameter("id") );
            
            TipoUsuario usTp = new TipoUsuario(id); // bean
            
            TipoUsuarioDAO dao = new TipoUsuarioDAO(); // dao
            
            dao.delete(usTp);
            
        }
        
        req.getRequestDispatcher("/home/app/adm/tipousuario.jsp").forward(req, resp);
        
        
    }
    
    private void doGetUsuario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        String action = req.getParameter("action");
        
        if( ( action != null ) && ( action.equals("delete") ) ) {
            
            int id = Integer.parseInt( req.getParameter("id") );
            
            Usuario us = new Usuario(id); // bean
            
            UsuarioDAO dao = new UsuarioDAO(); // dao
            
            dao.delete(us);
            
        }
        
        req.getRequestDispatcher("/home/app/adm/usuario.jsp").forward(req, resp);
        
        
    }
    
    private void doGetVaga(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        String action = req.getParameter("action");
        
        if( ( action != null ) && ( action.equals("delete") ) ) {
            
            int id = Integer.parseInt( req.getParameter("id") );
            
            Vaga vg = new Vaga(id); // bean
            
            VagaDAO dao = new VagaDAO(); // dao
            
            dao.delete(vg);
            
        }
        
        req.getRequestDispatcher("/home/app/adm/vaga.jsp").forward(req, resp);
        
        
    }
    
    
    private void doPostLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        int id = Integer.parseInt( req.getParameter("id") );
        String senha = req.getParameter("senha");
        
        UsuarioDAO dao = new UsuarioDAO(); // dao
        
        Usuario us = dao.getUnique(id);
        
        Usuario usTry = new Usuario(id);
        usTry.setSenha(senha);
        
        Cookie cookieId = new Cookie( "id", String.valueOf(id) );
        cookieId.setMaxAge( 60 * 2 ); // segundos
        resp.addCookie(cookieId);
        
        if( ( us != null ) && ( us.getSenha().equals( usTry.getSenha() ) )  ) {
            
            TipoUsuario usTp = new TipoUsuarioDAO().getUnique( us.getTipoUsuarioId() );
            
            HttpSession sessao = req.getSession(false);
            if( sessao != null ) {
                sessao.invalidate();
            }
            
            sessao = req.getSession(true);
            sessao.setMaxInactiveInterval( 60 * 10 ); // segundos
            sessao.setAttribute("tipo_usuario_sessao", usTp);
            sessao.setAttribute("usuario_sessao", us);
            
            req.getRequestDispatcher("/home/app/menu.jsp").forward(req, resp);            
            
        } else {
            req.setAttribute("msg", "id e/ou senha incorreto(s)!");
            req.getRequestDispatcher("/home/login.jsp").forward(req, resp);
        }
        
    }
    
    private void doPostTipoUsuario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        String action = req.getParameter("action"); // new || update
        
        int id = Integer.parseInt( req.getParameter("id") );
        String nome = req.getParameter("nome");
        
        String moduloAdministrativo = req.getParameter("modulo_administrativo") != null ? "S" : "N";
        String moduloAtendimento = req.getParameter("modulo_atendimento") != null ? "S" : "N";
        String moduloOperacao = req.getParameter("modulo_operacao") != null ? "S" : "N";
        
        TipoUsuario usTp = new TipoUsuario(id); // bean
        usTp.setNome(nome);
        usTp.setModuloAdministrativo(moduloAdministrativo);
        usTp.setModuloAtendimento(moduloAtendimento);
        usTp.setModuloOperacao(moduloOperacao);
        
        TipoUsuarioDAO dao = new TipoUsuarioDAO(); // dao
        
        if( action.equals("new") ) dao.insert( usTp );
        
        if( action.equals("update") ) dao.update( usTp );
        
        req.getRequestDispatcher("/home/app/adm/tipousuario.jsp").forward(req, resp);
        
    }
    
    private void doPostUsuario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        String action = req.getParameter("action"); // new || update
        
        int id = Integer.parseInt( req.getParameter("id") );
        String nome = req.getParameter("nome"); // sem o tratamento do vazio como null
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        int tipoUsuarioId = Integer.parseInt( req.getParameter("tipo_usuario_id") );
        
        Usuario us = new Usuario(id); // bean
        us.setNome(nome);
        us.setEmail(email);
        
        if( senha.length() > 20 ) {
            us.setSenhaHash(senha);
        } else {
            us.setSenha(senha);
        }
        
        us.setTipoUsuarioId(tipoUsuarioId);
        
        UsuarioDAO dao = new UsuarioDAO(); // dao
        
        if( action.equals("new") ) dao.insert( us );
        
        if( action.equals("update") ) dao.update( us );
        
        req.getRequestDispatcher("/home/app/adm/usuario.jsp").forward(req, resp);
        
    }
    
    private void doPostVaga(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        String action = req.getParameter("action"); // new || update
        
        int id = Integer.parseInt( req.getParameter("id") );
        String numero = req.getParameter("numero"); // sem o tratamento do vazio como null
        String setor = req.getParameter("setor");
        String tipo = req.getParameter("tipo");
        String status = req.getParameter("status");
        
        Vaga vg = new Vaga(id); // bean
        vg.setNumero(numero);
        vg.setSetor(setor);
        vg.setTipo(tipo);
        vg.setStatus(status);
        
        
        VagaDAO dao = new VagaDAO(); // dao
        
        if( action.equals("new") ) dao.insert( vg );
        
        if( action.equals("update") ) dao.update( vg );
        
        req.getRequestDispatcher("/home/app/adm/vaga.jsp").forward(req, resp);
        
    }
    
    
}
