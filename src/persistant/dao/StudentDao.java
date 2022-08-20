package persistant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistant.dto.RequestStudentDto;
import persistant.dto.ResponseStudentDto;
import persistant.dto.StudentAttendCourse;



public class StudentDao {
	
	public static Connection con= null;
	
	static {
		con = DatabaseConnection.getConnection();
	}
	
	public int insertStudent(RequestStudentDto dto) {
		int result = 0;
		String sql ="insert into student(name,dob,gender,phone,education) values (?,?,?,?,?);";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,dto.getName());
			ps.setString(2, dto.getDob());
			ps.setString(3,dto.getGender());
			ps.setString(4, dto.getPhone());
			ps.setString(5, dto.getEducation());
			result=ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void updateStudentById(RequestStudentDto dto) {
		
		String sql = "update student set name=?,dob=?,gender=?,phone=?,education=? where id=?;";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,dto.getName());
			ps.setString(2, dto.getDob());
			ps.setString(3,dto.getGender());
			ps.setString(4, dto.getPhone());
			ps.setString(5, dto.getEducation());
			ps.setInt(6,Integer.parseInt(dto.getId().substring(3)));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean deleteStudentById(String dto) {
		
		boolean bol=false;
        PreparedStatement stmt=null;
        String query="delete from student where id=?";
        try {
            stmt=con.prepareStatement(query);
            stmt.setInt(1,Integer.parseInt(dto));
            int result=stmt.executeUpdate();
            if(result==1) {
                bol=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bol;
    }
	
	public List<ResponseStudentDto> getAllStudent() {
        
		 List<ResponseStudentDto> students=new ArrayList<>();
	        String query="select * from student;";

	        PreparedStatement stmt=null;
	        try {
	             stmt=con.prepareStatement(query);
	            ResultSet set=stmt.executeQuery();
	             while(set.next()) {
	            	 ResponseStudentDto dto=new ResponseStudentDto();
	            	 dto.setId("STU"+String.valueOf(set.getInt("id")));
	            	 dto.setName(set.getString("name"));
	            	 dto.setDob(set.getString("dob"));
	            	 dto.setGender(set.getString("gender"));
	            	 dto.setPhone(set.getString("phone"));
	            	 dto.setEducation(set.getString("education"));
	            	 dto.setAttend(this.findAttendCoursesById(String.valueOf(set.getInt("id"))));
	                 students.add(dto);
	             }
	        } catch (SQLException e) {	      
	            e.printStackTrace();
	        }

	        return students;
	}
    
	
	public List<ResponseStudentDto> specificStudent(RequestStudentDto req) {
	    
        ResponseStudentDto res = null;
        List<ResponseStudentDto> list = new ArrayList<>();
        String sql = "select * from student where id =? or name =?;";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,Integer.valueOf(req.getId()));
            ps.setString(2,req.getName());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
            res = new ResponseStudentDto();
            res.setId("STU"+String.valueOf(rs.getInt("id")));
            res.setName(rs.getString("name"));
            res.setDob(rs.getString("dob"));
            res.setGender(rs.getString("gender"));
            res.setPhone(rs.getString("phone"));
            res.setEducation(rs.getString("education"));
            list.add(res);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }
	
	public String getmaxId() {
		
	    String maxId=null;
	    String sql="select MAX(id) max from student;";
	    try {
	        PreparedStatement stmt=con.prepareStatement(sql);
	        ResultSet resultSet=stmt.executeQuery();

	        while(resultSet.next()) {
	            maxId="STU"+String.valueOf(resultSet.getInt("max"));//9
	        }

	    } catch (SQLException e) {

	        e.printStackTrace();
	    }
	    return maxId ==null ? "STU": maxId;  
	}
	
	public List<StudentAttendCourse> findAttendCoursesById( String studentId ){

	    List<StudentAttendCourse> attendCourses = new ArrayList<>();
	    String sql = "SELECT * FROM coursedetail atts JOIN course crs ON atts.courseid = crs.id WHERE atts.studentid =?;";

	    try {
	        PreparedStatement pre = con.prepareStatement(sql);
	        pre.setString(1,studentId); 

	        ResultSet set = pre.executeQuery();

	        while(set.next())
	        {
	            StudentAttendCourse stuRes = new StudentAttendCourse();
	            stuRes.setName(set.getString("name"));
	            stuRes.setCourseid(set.getString("courseid"));
	            stuRes.setStudentid(set.getString("studentid"));
	            attendCourses.add(stuRes);
	        }

	    } catch (SQLException e) {

	        e.printStackTrace();
	    }

	    return attendCourses;
	}
}
