package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Emp;

public class EmpDAO {
	
	public static ArrayList<HashMap<String, Object>> selectEmpSelfManager() throws Exception{
		
		ArrayList<HashMap<String, Object>> selectEmpSelfManager = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		
		String sql = "select e1.empno empNo, e1.ename empName, e1.grade empGrade, nvl(e2.ename, '없음') mgrName, nvl(e2.grade, 0) mgrGrade from emp e1 left outer join emp e2 on e1.mgr = e2.empno order by e1.empno asc";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<>();
			m.put("empNo", rs.getInt("empNo"));
			m.put("empName", rs.getString("empName"));
			m.put("empGrade", rs.getInt("empGrade"));
			m.put("mgrName", rs.getString("mgrName"));
			m.put("mgrGrade", rs.getInt("mgrGrade"));
		
			selectEmpSelfManager.add(m);
		}
		
		conn.close();
		
		
		return selectEmpSelfManager;
		
	}
	
	
	
		public static ArrayList<HashMap<String, Integer>> selectEmpSalStates() throws Exception{
			
			ArrayList<HashMap<String, Integer>> selectEmpSalStates = new ArrayList<>();
			
			Connection conn = DBHelper.getConnection();
			
			String sql = "select grade, count(*) count, sum(sal) sum, avg(sal) avg, max(sal) max, min(sal) min from emp group by grade order by grade asc ";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				HashMap<String, Integer> m = new HashMap<>();
				m.put("grade", rs.getInt("grade"));
				m.put("count", rs.getInt("count"));
				m.put("sum", rs.getInt("sum"));
				m.put("avg", rs.getInt("avg"));
				m.put("max", rs.getInt("max"));
				m.put("min", rs.getInt("min"));
				selectEmpSalStates.add(m);
			}
			
			conn.close();
			
			
			return selectEmpSalStates;
			
		}
	
	
		public static ArrayList<Emp> selectEmpListSort(String col, String sort )throws Exception{
			
			//매개값 디버깅
			System.out.println("col : "+col);
			System.out.println("sort : "+sort);
			
			ArrayList<Emp> selectEmpListSort = new ArrayList<>();
	
			Connection conn = DBHelper.getConnection();
						
			String sql = "SELECT empno, ename"
					+ " FROM emp";
			
			if(col !=null && sort != null) {
				sql = sql + " ORDER BY "+col+" "+sort;
			}
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Emp e = new Emp();
				e.setEmpNo(rs.getInt("empno"));
				e.setEname(rs.getString("ename"));
				selectEmpListSort.add(e);
			}
			
			conn.close();
			
			return selectEmpListSort;	
		}
	
		
	
		
		
		
		
	
	
	
	
	// q004WhereIn.jsp
		public static ArrayList<Emp> selectEmpListByGrade
					(ArrayList<Integer> ckList) throws Exception {
			ArrayList<Emp> list = new ArrayList<>();
			Connection conn = DBHelper.getConnection();
			String sql = "SELECT ename, grade"
					+ " FROM emp"
					+ " WHERE grade IN ";
			PreparedStatement stmt = null;
			if(ckList.size() == 1) {
				sql = sql + "(?)";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, ckList.get(0));
			} else if(ckList.size() == 2) {
				sql = sql + "(?,?)";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, ckList.get(0));
				stmt.setInt(2, ckList.get(1));
			} else if(ckList.size() == 3) {
				sql = sql + "(?,?,?)";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, ckList.get(0));
				stmt.setInt(2, ckList.get(1));
				stmt.setInt(3, ckList.get(2));
			} else if(ckList.size() == 4) {
				sql = sql + "(?,?,?,?)";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, ckList.get(0));
				stmt.setInt(2, ckList.get(1));
				stmt.setInt(3, ckList.get(2));
				stmt.setInt(4, ckList.get(3));
			} else if(ckList.size() == 5) {
				sql = sql + "(?,?,?,?,?)";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, ckList.get(0));
				stmt.setInt(2, ckList.get(1));
				stmt.setInt(3, ckList.get(2));
				stmt.setInt(4, ckList.get(3));
				stmt.setInt(5, ckList.get(4));
			}
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Emp e = new Emp();
				e.setEname(rs.getString("ename"));
				e.setGrade(rs.getInt("grade"));
				list.add(e);
			}
			return list;
		}
	
	
	
	
public static ArrayList<HashMap<String, String>> selectJobCaseList()throws Exception {
		ArrayList<HashMap<String,String>> selectJobCaseList = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		
	      String sql = "SELECT ename, job, CASE"
	              + " WHEN job = 'PRESIDENT' THEN '빨강'"
	              + " WHEN job = 'MANAGER' THEN '주황'"
	              + " WHEN job = 'ANALYST' THEN '노랑'"
	              + " WHEN job = 'CLERK' THEN '초록'"
	              + " ELSE '파랑' END color"
	              + " FROM emp"
	              + " ORDER BY CASE"
	              + " WHEN color = '빨강' THEN 1"
	              + " WHEN color = '주황' THEN 2"
	              + " WHEN color = '노랑' THEN 3"
	              + " WHEN color = '초록' THEN 4"
	              + " ELSE 5 END ASC";

		
	
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			HashMap<String, String> m = new HashMap<>();
			m.put("ename", rs.getString("ename"));
			m.put("job", rs.getString("job"));
			m.put("color", rs.getString("color"));
			selectJobCaseList.add(m);
		}
		
		return selectJobCaseList;
	}
	
	
	
	
	//DeptNo 뒤에 부서별 인원수를 같이 조회하는 메서드
	public static ArrayList<HashMap<String, Integer>> selectDeptNoCntList() throws Exception{
		ArrayList<HashMap<String, Integer>> selectDeptNoCntList = new ArrayList<>();
		
		String sql = "select deptno deptNo, count(*) cnt from emp where deptno is not null group by deptno order by deptno";
		
		Connection conn = DBHelper.getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Integer> m = new HashMap<>();
			m.put("deptNo", rs.getInt("deptNo"));
			m.put("cnt", rs.getInt("cnt"));
			
			selectDeptNoCntList.add(m);
		}
		conn.close();
		
		return selectDeptNoCntList;
	}
	
	

	//중복을 제외한 deptno 목록을 출력하는 메서드
	
	public static ArrayList<Integer> selectDeptNoList() throws Exception{
		ArrayList<Integer> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		String sql = "select distinct deptno deptNo from emp where deptno is not null order by deptno";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Integer i = rs.getInt("deptNo"); //auto boxting
			list.add(i);
		}
		conn.close();
		
		return list;
	}
	
	
	
	
	// 조인으로 Map을 사용하는 겨우
	public static ArrayList<HashMap<String, Object>> selectEmpAndDeptList()
													throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT"
				+ " emp.empno empNo, emp.ename ename, emp.deptno deptNo,"
				+ " dept.dname dname"
				+ " FROM emp INNER JOIN dept"
				+ " ON emp.deptno = dept.deptno";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<>();
			m.put("empNo", rs.getInt("empNo"));
			m.put("ename", rs.getString("ename"));
			m.put("deptNo", rs.getInt("deptNo"));
			m.put("dname", rs.getString("dname"));
			list.add(m);
		}
		return list;
	}
	
	// VO 사용
	public static ArrayList<Emp> selectEmpList() throws Exception {
		ArrayList<Emp> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT"
				+ " empno empNo, ename, sal"
				+ " FROM emp";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Emp e = new Emp();
			e.setEmpNo(rs.getInt("empNo"));
			e.setEname(rs.getString("ename"));
			e.setSal(rs.getDouble("sal"));
			list.add(e);
		}
		
		return list;
	}
}
