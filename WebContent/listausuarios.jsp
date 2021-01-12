<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>LISTANDO COM JSTL</title>
	</head>
	<body>
		
		<script type="text/javascript">
			function confirmarExclusao(id) {
				if(window.confirm("Tem certeza que deseja excluir o registro: "+id)){
					location.href="usucontroller.do?acao=exc&id="+id;
				}
			}
		</script>
	
		<table border="1" align="center" style="font-family:Arial, serif;">
		<c:import url="includes/menu.jsp"></c:import>
			<tr bgcolor="#CCCCCC">
				<th>Id</th>
				<th>Nome</th>
				<th>Login</th>
				<th>Senha</th>
				<th>Ação</th>
			</tr>
			
			<c:forEach items="${requestScope.lista}" var="usuario">
				<tr>
					<td>${usuario.id}</td>
					<td>${usuario.nome}</td>
					<td>${usuario.login}</td>
					<td>${usuario.senha}</td>
					<td>
						<a href="?acao=alt&id=${usuario.id}">Alterar |</a>
						<a href="javascript:confirmarExclusao(${usuario.id})">Excluir</a>
					</td>
				</tr>
			
			</c:forEach>
		
		</table>
		
	</body>
</html>