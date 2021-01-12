<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Formulário com JSP</title>
	</head>
	<body>
		
		<c:import url="includes/menu.jsp"></c:import>
		<form action="usucontroller.do" method="post">
			
			<h1>Cadastro de usuários</h1>
			<c:import url="includes/menu.jsp"></c:import>
			
			<label>Id: </label>
			<input type="text" readonly="readonly" name="txtid" value="${requestScope.usuario.id}"/>
			
			<label>Nome: </label>
			<input type="text" name="txtnome" value="${requestScope.usuario.nome}"/>
			
			<label>Login: </label>
			<input type="text" name="txtlogin" value="${requestScope.usuario.login}"/>
			
			<label>Senha: </label>
			<input type="password" name="txtsenha" value="${requestScope.usuario.senha}"/>
			
			<input type="submit" value="Salvar Usuário">
			
		</form>

	</body>
</html>