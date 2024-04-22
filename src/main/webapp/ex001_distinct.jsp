<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
<!-- Model -->
<%
	ArrayList<Integer> list = EmpDAO.selectDeptNoList();
	ArrayList<HashMap<String,Integer>> selectDeptNoCntList = EmpDAO.selectDeptNoCntList();
	ArrayList<HashMap<String,String>> selectJobCaseList = EmpDAO.selectJobCaseList();
%>

<!-- View -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>연습예제 distinct</title>
</head>
<body>
	<h1>중복을 제외한 deptno 목록을 출력하는 메서드</h1>
	<select name="deptNo">
		<option value="">:::선택:::</option>
		<%
			for(Integer i: list) {
		%>
				<option value="<%=i%>"><%=i%></option>
		<%	
			}
		%>
	</select>
	
	<h1>※ DISTINCT대신 GROUP BY를 사용해야만 하는 경우</h1>
	<select name="dept">
		<option value="">:::선택:::</option>
		<%
			for(HashMap<String, Integer> m: selectDeptNoCntList) {
		%>
				<option value='<%=m.get("deptNo")%>'>
					<%=m.get("deptNo")%>
					(<%=m.get("cnt")%>명)
				</option>
		<%	
			}
		%>
	</select>
	
	<h1>case문 활용한 조회셋 출력</h1>
	<table>
		
		<%
			for(HashMap<String, String> m : selectJobCaseList){
				%>
				<tr>
					<td><%=m.get("ename") %></td>
					<td><%=m.get("job") %></td>
					<td><%=m.get("color") %></td>
				</tr>
				
				<% 
			}
		%>
	
	</table>

	


	
</body>
</html>
