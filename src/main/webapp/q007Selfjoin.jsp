<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="dao.*" %>
<%@ page import ="java.util.*" %>
<%@ page import="java.net.*" %>



<!-- controller -->
<%
	ArrayList<HashMap<String, Object>> selectEmpSelfManager = EmpDAO.selectEmpSelfManager();
%>


<!-- view -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
</head>
<body>
	<h1>emp 직급체계</h1>
	<table border = "1">
		<tr>
			<th>empNo</th>
			<th>empName</th>
			<th>empGrade</th>
			<th>mgrName</th>
			<th>mgrGrade</th>
		
		</tr>
		
			<%
				for(HashMap<String, Object> m : selectEmpSelfManager){
					%>
						<tr>
							<td><%=m.get("empNo") %></td>
							<td><%=m.get("empName") %></td>
							<td>
								<%
									for(int i = 0; i < (Integer)(m.get("empGrade")); i = i+1){
										%>
									&#128075;	
										
								<%	}
								%>
							</td>
							<td><%=m.get("mgrName") %></td>
							<td>
								<%
									for(int i = 0; i < (Integer)(m.get("mgrGrade")); i = i+1){
										%>
										&#128585;	
										
								<%	}
								%>
							</td>
							
							
					
						</tr>
					<%} %>
		
	
	</table>
</body>
</html>