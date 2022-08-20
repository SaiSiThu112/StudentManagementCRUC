<%@page import="persistant.dao.StudentDao"%>
<%@page import="persistant.dto.ResponseCourseDto"%>
<%@page import="java.util.List"%>
<%@page import="persistant.dao.CourseDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="test.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>

<title>Course Registration</title>
</head>

<%

StudentDao coudao=new StudentDao();
String COUcount=coudao.getmaxId();
int count=Integer.parseInt(COUcount.substring(3))+1;

CourseDao cudao=new CourseDao();
List<ResponseCourseDto> daocu=cudao.getAllCourse();
request.getServletContext().setAttribute("clist", daocu);

%>

<body>
	<div id="testheader">
		<div class="container">
			<div class=row>
				<div class="col-md-5 ">
					<a href="home.jsp"><h3>Student Registration</h3></a>
				</div>
				<div class="col-md-6">
					<p>User : ${sessionScope.userName.name}</p>
					<p>
						Current Date :
						<%= LocalDate.now() %>
					</p>
				</div>
				<div class="col-md-1">
					<input type="button" class="btn-basic" id="lgnout-button"
						value="Log Out" onclick="location.href='LogOutController'">
				</div>
			</div>
		</div>

	</div>

	<div class="container">
		<div class="sidenav">

			<button class="dropdown-btn">
				Class Management <i class="fa fa-caret-down"></i>
			</button>

			<div class="dropdown-container">
				<a href="course_registration.jsp">Course Registration </a> <a href="student_registration.jsp">Student
					Registration </a> <a href="DisplayAttendController">Student Search </a>
			</div>
			<a href="user.jsp">Users Management</a>
		</div>

		<div class="main_contents">
			<div id="sub_content">
				<form action="CreateStudentController" method="post">

					<h2 class="col-md-6 offset-md-2 mb-5 mt-4">Student
						Registration</h2>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="studentID" class="col-md-2 col-form-label">Student
							ID</label>
						<div class="col-md-4">
							<input type="text" class="form-control" value="STU<%= count %>"
								id="studentID" name="id" readonly>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="name" class="col-md-2 col-form-label">Name</label>
						<div class="col-md-4">
							<input type="text" class="form-control" id="name" name="name">
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="dob" class="col-md-2 col-form-label">DOB</label>
						<div class="col-md-4">
							<input type="date" class="form-control" id="dob" name="dob">
						</div>
					</div>
					<fieldset class="row mb-4">
						<div class="col-md-2"></div>
						<legend class="col-form-label col-md-2 pt-0">Gender</legend>
						<div class="col-md-4">
							<div class="form-check-inline">
								<input class="form-check-input" type="radio" name="gender"
									id="gridRadios1" value="Male" checked> <label
									class="form-check-label" for="gridRadios1"> Male </label>
							</div>
							<div class="form-check-inline">
								<input class="form-check-input" type="radio" name="gender"
									id="gridRadios2" value="Female"> <label
									class="form-check-label" for="gridRadios2"> Female </label>
							</div>

						</div>
					</fieldset>

					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="phone" class="col-md-2 col-form-label">Phone</label>
						<div class="col-md-4">
							<input type="text" class="form-control" id="phone" name="phone" value="+95 ">
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="education" class="col-md-2 col-form-label">Education</label>
						<div class="col-md-4">
							<select class="form-select" aria-label="Education" id="education" name="education">
								<option selected>Bachelor of Information Technology</option>
								<option value="1">Diploma in IT</option>
								<option value="2">Bachelor of Computer Science</option>

							</select>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="name" class="col-md-2 col-form-label">Attend</label>
						<div class="col-md-4">
							<c:forEach items="${clist}" var="item">
								<input type="checkbox" name="attend" value="${item.id}" />
								${item.name}
							</c:forEach>
						</div>
					</div>

					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="name" class="col-md-2 col-form-label">Photo</label>
						<div class="col-md-4">
							<input type="file" class="form-control" id="name">
						</div>
					</div>

					<div class="row mb-4">
						<div class="col-md-4"></div>

						<div class="col-md-4">
							 <button type="button" class="btn btn-danger ">Reset</button>
							<button type="submit" class="btn btn-secondary col-md-2"
								data-bs-toggle="modal" data-bs-target="#exampleModal">
								Add</button>
							
						</div>

					</div>

				</form>
			</div>
		</div>
	</div>
	<div id="testfooter">
		<span>Copyright &#169; ACE Inspiration 2022</span>
	</div>
	<script>
		/* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
		var dropdown = document.getElementsByClassName("dropdown-btn");
		var i;

		for (i = 0; i < dropdown.length; i++) {
			dropdown[i].addEventListener("click", function() {
				this.classList.toggle("active");
				var dropdownContent = this.nextElementSibling;
				if (dropdownContent.style.display === "block") {
					dropdownContent.style.display = "none";
				} else {
					dropdownContent.style.display = "block";
				}
			});
		}
	</script>
</body>

</html>
