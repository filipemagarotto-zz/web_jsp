<%@page import="br.com.webjsp.entidades.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		
		<table border="1" align="center" style="font-family:Arial, serif;">
			<tr bgcolor="#CCCCCC">
				<th>Id</th>
				<th>Nome</th>
				<th>Login</th>
				<th>Senha</th>
				<th>Ação</th>
			</tr>
			<%
			//scriptlet
			List<Usuario> lista = (List<Usuario>)request.getAttribute("lista");
			for(Usuario usuario : lista) {
			%>
			
			<tr>
				<td><%=usuario.getId()%></td>
				<td><%=usuario.getNome()%></td>
				<td><%=usuario.getLogin()%></td>
				<td><%=usuario.getSenha()%></td>
				<td><a href="?acao=alt&id=<%=usuario.getId()%>">Alterar |</a>
				<a href="?acao=exc&id=<%=usuario.getId()%>">Excluir</a></td>
			</tr>
			
			<%}%>
		
		</table>
		
	</body>
</html>