package persistant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistant.dto.RequestCourseDto;
import persistant.dto.RequestStudentDto;
import persistant.dto.RequestUserDto;
import persistant.dto.ResponseCourseDto;
import persistant.dto.StudentAttendCourse;

public class CourseDao {
	
	public static Connection con= null;
	
	static {
		con = DatabaseConnection.getConnection();
	}
	
   public void insertCourse(RequestCourseDto dto) {
		
		String sql ="insert into course (id,name) value (?,?);";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,Integer.parseInt(dto.getId().substring(3)));
			ps.setString(2,dto.getName());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}   
   
   public List<ResponseCourseDto> getAllCourse() {
       
	   List<ResponseCourseDto> course=new ArrayList<>();
       String query="select * from course;";

       PreparedStatement stmt=null;
       try {
            stmt=con.prepareStatement(query);
            ResultSet set=stmt.executeQuery();
            while(set.next()) {
           	ResponseCourseDto courseResponse = new ResponseCourseDto();
           	courseResponse.setId("COU"+String.valueOf(set.getInt("id")));
           	courseResponse.setName(set.getString("name"));
            course.add(courseResponse);
            }
       
       } catch (SQLException e) {
           e.printStackTrace();
       }

       return course;
   }
   
   public String getmaxId() {
	   
		String maxId=null;
		
		String sql="select MAX(id) max from course;";
		try {
			PreparedStatement stmt=con.prepareStatement(sql);
			ResultSet resultSet=stmt.executeQuery();
			
			while(resultSet.next()) {
				maxId="COU"+String.valueOf(resultSet.getInt("max"));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	return maxId ==null ? "COU": maxId;	
	}
	
	public Integer InsertCourseDetail(RequestStudentDto dto) {
		int result=0;
		try {
			for(String stu : dto.getAttend()) {
				PreparedStatement stmt=con.prepareStatement("insert into coursedetail(studentid,courseid)values(?,?);");
				stmt.setInt(1, Integer.parseInt(dto.getId().substring(3)));//STU1			
				stmt.setInt(2,Integer.parseInt(stu.substring(3)));//Course ArrayList 			
				result=stmt.executeUpdate();				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		 
	}
	
	public void updateCoruse(RequestStudentDto dto) {
		
		String sql = "update coursedetail set courseid=? where studentid=?;";
		try {	
			for(String stu:dto.getAttend() ) {
				PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1,Integer.parseInt(stu.substring(3)));
                ps.setInt(2, Integer.parseInt(dto.getId().substring(3)));
                ps.executeUpdate();
				}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int deleteCourse(StudentAttendCourse dto) {
		
		int result = 0;
		String sql = "delete form coursedetail where studentid=?;";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,Integer.parseInt(dto.getStudentid()));
			result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
}
