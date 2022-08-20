package persistant.dto;

import java.util.List;

public class ResponseStudentDto {
	
	private String id;
	private String name;
	private String dob;
	private String gender;
	private String phone;
	private String education;
	private List<StudentAttendCourse> attend;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public List<StudentAttendCourse> getAttend() {
		return attend;
	}
	public void setAttend(List<StudentAttendCourse> attend) {
		this.attend = attend;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
		
}
