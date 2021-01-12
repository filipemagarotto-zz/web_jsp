package br.com.webjsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.webjsp.entidades.Usuario;
import br.com.webjsp.jdbc.UsuarioDAO;

@WebServlet("/usucontroller.do")
public class UsuarioController 	extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UsuarioController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		String acao = request.getParameter("acao");
		UsuarioDAO dao = new UsuarioDAO();
		
		
		if(acao!=null && acao.equals("exc")) {
			//Captura parâmetro da tela
			String id = request.getParameter("id");
			Usuario usuario = new Usuario();
			usuario.setId(Integer.parseInt(id));
			dao.excluir(usuario);	
			//redirecionamento pelo cliente (browser)
			response.sendRedirect("usucontroller.do?acao=lis");
		}
		
		
		if(acao!=null && acao.equals("alt")) {
			//Captura parâmetro da tela
			String id = request.getParameter("id");
			Usuario usuario = dao.buscarPorId(Integer.parseInt(id));
			request.setAttribute("usuario", usuario);
			RequestDispatcher saida = request.getRequestDispatcher("frmusuario.jsp");
			saida.forward(request, response);
			
		}
		
		
		if(acao!=null && acao.equals("cad")) {
			//Cria novo objeto usuario
			Usuario usuario = new Usuario();
			usuario.setId(0);
			usuario.setNome("");
			usuario.setLogin("");
			usuario.setSenha("");
			request.setAttribute("usuario", usuario);
			RequestDispatcher saida = request.getRequestDispatcher("frmusuario.jsp");
			saida.forward(request, response);
		}
		
		
		if(acao!=null && acao.equals("lis")) {
			//1 - obter a lista
			List<Usuario> lista = dao.mostrarTodos();
				
			//2 - Engavetar no requesta a lista
			request.setAttribute("lista", lista);
				
			//3 - Encaminhar para o JSP
			RequestDispatcher saida = request.getRequestDispatcher("listausuarios.jsp");
			saida.forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("txtid");
		String nome = request.getParameter("txtnome");
		String login = request.getParameter("txtlogin");
		String senha = request.getParameter("txtsenha");
		
		Usuario usuario = new Usuario();
		
		if(id!=null && id!="" && id!="0") {
			usuario.setId(Integer.parseInt(id));
		}
		
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		UsuarioDAO dao = new UsuarioDAO();
		dao.salvar(usuario);
		response.sendRedirect("usucontroller.do?acao=lis");
		
		PrintWriter saida = response.getWriter();
		saida.println("Salvo com sucesso.");

	}

}
