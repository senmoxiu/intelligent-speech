package cs.db;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;


//import cs.bean.Student;

public class DbUtil {

    private Connection conn;

    public DbUtil(String url, String username, String password){
        try {
          Class.forName("com.mysql.jdbc.Driver");
          conn = DriverManager.getConnection(url, username, password);
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      } catch (SQLException e) {
          e.printStackTrace();
      }
    }

    
    public Connection initConnection() {
        return conn;
    }
    
    public void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void closeStatement(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, String>> genericQuery(String sql, Object[] o){
        List<Map<String, String>> list = null;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            if (o != null) {
                for(int i = 1; i <= o.length; i++) {
                    ps.setObject(i, o[i-1]);
                }
            }
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            list = new ArrayList<Map<String,String>>();
            while(rs.next()) {
                Map<String, String> m = new HashMap<String, String>();
                for(int i = 1; i <= rsmd.getColumnCount(); i++) {
                    m.put(rsmd.getColumnName(i), rs.getString(i));
                }
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(ps);
        }
        return list;
    }
    
    public  <T> List<T> genericQuery(String sql, Object[] o, Class<T> c){
        List<T> list = null;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            if (o != null) {
                for(int i = 1; i <= o.length; i++) {
                    ps.setObject(i, o[i-1]);
                }
            }
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            list = new ArrayList<T>();
            while(rs.next()) {
                T t = c.getDeclaredConstructor().newInstance();
                for(int i = 1; i <= rsmd.getColumnCount(); i++) {
//                    String columnName = rsmd.getColumnName(i);
                	String columnName = rsmd.getColumnLabel(i);
                   
                    String property = "";
                    boolean upcase = false;
                    for(int k = 0; k < columnName.length(); k++) {
                        if (columnName.charAt(k) == '_') {
                            upcase = true;
                            continue;
                        }
                        if (upcase) {
                            property += (""+columnName.charAt(k)).toUpperCase();
                        }else {
                            property += columnName.charAt(k);
                        }
                        upcase = false;
                    }
                    String methodName = "set" + property.substring(0, 1).toUpperCase()
                            + property.substring(1, property.length());
                    Method method = c.getDeclaredMethod(methodName, 
                            c.getDeclaredField(property).getType());
                    Object obj = rs.getObject(i);
                    method.invoke(t, obj);
                }
                list.add(t);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            closeStatement(ps);
        }
        return list;
    }
    
    public int genericDML(String sql, Object[] o) {
        PreparedStatement ps = null;
        int result = 0;
        try {
            ps = conn.prepareStatement(sql);
            if (o != null) {
                for(int i = 1; i <= o.length; i++) {
                    ps.setObject(i, o[i-1]);
                }
            }
            result = ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(ps);
        }
        return result;
    }

    public Connection getConn(){
        return  conn;
    }
    
    public static void main(String[] args) {
    	String url = "jdbc:mysql://localhost:3307/gao?useUnicode=true&characterEncoding=utf8&useSSL=false";
    	String username = "root";
    	String password = "123456";
		DbUtil dbUtil = new DbUtil(url, username, password);
		System.out.println(dbUtil.getConn());
//		dbUtil.genericDML("insert into student(`name`) values (?)", new Object[] {"学生" + rnd.nextInt(1000)});
//		dbUtil.genericDML("update student set `name`=? where id=?", new Object[] {"学生" + rnd.nextInt(1000), "1"});
		
//		List<Student> list = dbUtil.genericQuery("select id, student_name from student", null, Student.class);
//		if (list != null) {
//			for(Student student: list) {
//				System.out.println(student.getId() + ", " + student.getStudentName());
//			}
//		}
	}

}

