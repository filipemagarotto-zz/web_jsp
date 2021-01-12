<%@page import="br.com.webjsp.entidades.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		
		<%
		Usuario usuario = (Usuario)request.getAttribute("usuario");
		%>
		
		<form action="usucontroller.do" method="post">
			
			<h1>Cadastro de usuários</h1>
			
			<label>Id: </label>
			<input type="text" readonly="readonly" name="txtid" value="<%=usuario.getId()%>"/>
			
			<label>Nome: </label>
			<input type="text" name="txtnome" value="<%=usuario.getNome()%>"/>
			
			<label>Login: </label>
			<input type="text" name="txtlogin" value="<%=usuario.getLogin()%>"/>
			
			<label>Senha: </label>
			<input type="password" name="txtsenha" value=""<%=usuario.getSenha()%>/>
			
			<input type="submit" value="Salvar Usuário">
			
		</form>

	</body>
</html>