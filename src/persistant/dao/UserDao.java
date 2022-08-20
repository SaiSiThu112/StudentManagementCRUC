package persistant.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistant.dto.RequestUserDto;
import persistant.dto.ResponseUserDto;


public class UserDao {
	
	public static Connection con= null;
	
	static {
		con = DatabaseConnection.getConnection();
	}
	
	public void insertUser(RequestUserDto dto) {
		
		String sql ="insert into user(name,email,password,role) values (?,?,?,?);";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,dto.getName());
			ps.setString(2, dto.getEmail());
			ps.setString(3,dto.getPassword());
			ps.setString(4, dto.getRole());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUserById(RequestUserDto dto) {
		
		String sql = "update user set name=?,email=?,password=?,role=? where id=?;";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,dto.getName());
			ps.setString(2, dto.getEmail());
			ps.setString(3,dto.getPassword());
			ps.setString(4, dto.getRole());
			ps.setInt(5,Integer.parseInt(dto.getId()));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean deleteUserById(String dto) {
		
		boolean bol=false;
        PreparedStatement stmt=null;
        String query="delete from user where id=?";
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
	
	public ResponseUserDto selectById(RequestUserDto dto) {
		ResponseUserDto resDto = new ResponseUserDto();
		String sql = "select * from user where id=?;";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				resDto.setName(rs.getString("name"));
				resDto.setEmail(rs.getString("email"));
				resDto.setPassword(rs.getString("password"));
				resDto.setRole(rs.getString("role"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resDto;
	}
	
	public ResponseUserDto selectOne(RequestUserDto dto){
       
        String sql="select * from user where id=? and password=?;";
        ResponseUserDto responseDto =null;
        try 
        {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(dto.getId()));
            ps.setString(2,dto.getPassword());
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {	
            	responseDto = new ResponseUserDto();
            	responseDto.setId("USR"+rs.getString("id"));
            	responseDto.setName(rs.getString("name"));
            	responseDto.setEmail(rs.getString("email"));
            	responseDto.setPassword(rs.getString("password"));
            	responseDto.setRole(rs.getString("role"));
            }
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return responseDto;
        
    }
	
	public List<ResponseUserDto> specificUser(RequestUserDto req) {
    
        ResponseUserDto res = null;
        List<ResponseUserDto> list = new ArrayList<>();
        String sql = "select * from user where id =? or name =?;";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,Integer.valueOf(req.getId()));
            ps.setString(2,req.getName());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
            res = new ResponseUserDto();
            res.setId("USR"+String.valueOf(rs.getInt("id")));
            res.setName(rs.getString("name"));
            res.setEmail(rs.getString("email"));
            res.setPassword(rs.getString("password"));
            res.setRole(rs.getString("role"));
            list.add(res);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }
	
	public List<ResponseUserDto> getAllUser() {
        
		List<ResponseUserDto> users=new ArrayList<>();
        String query="select * from user;";

        PreparedStatement stmt=null;
        try {
             stmt=con.prepareStatement(query);
            ResultSet set=stmt.executeQuery();
             while(set.next()) {
            	 ResponseUserDto userResponse = new ResponseUserDto();
            	 userResponse.setId("USR"+String.valueOf(set.getInt("id")));
            	 userResponse.setName(set.getString("name"));
            	 userResponse.setEmail(set.getString("email"));
            	 userResponse.setPassword(set.getString("password"));
            	 userResponse.setRole(set.getString("role"));
                 users.add(userResponse);
             }
        
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
	
}
