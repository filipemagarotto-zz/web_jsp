package br.com.webjsp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.webjsp.entidades.Usuario;
import br.com.webjsp.jdbc.UsuarioDAO;

@WebServlet(name = "AutenticadorController", urlPatterns = { "/autcontroller.do" })
public class Autenticador extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
    public Autenticador() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sessao = request.getSession(false);
		if(sessao!=null) {
			sessao.invalidate();
		}
		response.sendRedirect("login.html");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		//controi objeto usuario para consulta
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		//buscar no banco
		UsuarioDAO dao = new UsuarioDAO();
		
		Usuario usuRetorno = dao.autenticar(usuario);
		if(usuRetorno != null) {
			
			//cricando sessao
			HttpSession sessao = request.getSession();
			sessao.setMaxInactiveInterval(3000);
			sessao.setAttribute("usulogado", usuRetorno);
			
			//encaminhando ao index
			request.getRequestDispatcher("index.jsp").forward(request, response);;
		} else {
			response.sendRedirect("login.html");
		}
		
	
	}

}


