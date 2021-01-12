package br.com.webjsp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.webjsp.entidades.Usuario;

public class UsuarioDAO {

	Connection con = Conexao.conectar();
	
	public void cadastrar(Usuario usuario) {
		
		String sql = "INSERT INTO usuario(nome, login, senha) VALUES (?, ?, ?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("O erro foi: " + e);
		}
	}
	
	public void editar(Usuario usuario) {
		
		String sql = "UPDATE usuario SET nome = ?, login = ?, senha = ? WHERE id = ? ";
		
		PreparedStatement preparador;
		try {
			preparador = con.prepareStatement(sql);
			
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			preparador.setInt(4, usuario.getId());	
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void salvar(Usuario usuario) {
		
		if(usuario != null && usuario.getId() != 0) {
			editar(usuario);
		} else {
			cadastrar(usuario);
		}
		
	}
	
	public void excluir(Usuario usuario) {
		
		String sql = "DELETE FROM usuario WHERE id = ?";
		
		PreparedStatement preparador;
		try {
			preparador = con.prepareStatement(sql);
			
			preparador.setInt(1, usuario.getId());
			
			preparador.execute();
			System.out.println("Excluido com sucesso.");
			preparador.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Usuario> mostrarTodos() {
		
		String sql = "SELECT * FROM usuario;";
		
		PreparedStatement preparador;
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			preparador = con.prepareStatement(sql);
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				
				lista.add(usuario);
			}
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}

	public Usuario buscarPorId(int id) {
		String sql = "SELECT * from usuario WHERE id = ?";
		
		Usuario retorno = null;
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()) {
				retorno = new Usuario();
				retorno.setId(resultado.getInt("id"));
				retorno.setNome(resultado.getString("nome"));
				retorno.setLogin(resultado.getString("login"));
				retorno.setSenha(resultado.getString("senha"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
		
	}

	/**
	 * Busca por login e senha de usuário
	 * @param usuario Objeto com login e senha a ser consultado no banco
	 * @return Null quando não encontra no banco ou um ponteiro a um objeto usuario completo quando encontra
	 */
	public Usuario autenticar(Usuario usuario) {
		
		String sql = "SELECT * FROM usuario WHERE login = ? and senha = ?";
		
		Usuario usuarioRetorno = null;
		
		PreparedStatement preparador;
		try {
			preparador = con.prepareStatement(sql);
			preparador.setString(1, usuario.getLogin());
			preparador.setString(2, usuario.getSenha());
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()) {
				usuarioRetorno = new Usuario();
				usuarioRetorno.setId(resultado.getInt("id"));
				usuarioRetorno.setNome(resultado.getString("nome"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarioRetorno;
	}
	
}







