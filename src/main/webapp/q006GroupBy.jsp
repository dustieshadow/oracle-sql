<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="dao.*" %>
<%@ page import ="java.util.*" %>
<%@ page import="java.net.*" %>



<!-- controller -->
<%
	ArrayList<HashMap<String,Integer>> selectEmpSalStates = EmpDAO.selectEmpSalStates();
%>


<!-- view -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
</head>
<body>
	<h1>등급별 Sal 통계</h1>
	<table border = "1">
		<tr>
			<th>&#128075;</th>
			<th>grade</th>
			<th>count</th>
			<th>sum</th>
			<th>avg</th>
			<th>max</th>
			<th>min</th>
		</tr>
		
			<%
				for(HashMap<String, Integer> m : selectEmpSalStates){
					%>
						<tr>
							<td>
								<%
									for(int i = 0; i < m.get("grade"); i = i+1){
										%>
									&#128075;	
										
								<%	}
								%>
							</td>
							<td><%=m.get("grade") %></td>
							<td><%=m.get("count") %></td>
							<td><%=m.get("sum") %></td>
							<td><%=m.get("avg") %></td>
							<td><%=m.get("max") %></td>
							<td><%=m.get("min") %></td>
						</tr>
					<%} %>
		
	
	</table>
</body>
</html>