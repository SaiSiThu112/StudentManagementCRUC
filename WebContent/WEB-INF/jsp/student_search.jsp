<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate"%>
	 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<body>
	<div id="testheader">
		<div class="container">
			<div class=row>
				<div class="col-md-5 ">
					<a href="home.jsp"><h3>Student Registration</h3></a>
				</div>
				<div class="col-md-6">
					<p>
						User :
						${sessionScope.userName.name}</p>
					<p>
						Current Date :
						<%=LocalDate.now()%>
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
				<form class="row g-3 mt-3 ms-2" action="SearchStudentController">
					<div class="col-auto">
						<label for="staticEmail2" class="visually-hidden">studentID</label>
						<input type="text" class="form-control" id="staticEmail2" name="id"
							placeholder="Student ID">
					</div>
					<div class="col-auto">
						<label for="inputPassword2" class="visually-hidden">studentName</label>
						<input type="text" class="form-control" id="inputPassword2"
							placeholder="Student Name" name="name">
					</div>
					<div class="col-auto">
						<label for="inputPassword2" class="visually-hidden">Course</label>
						<input type="text" class="form-control" id="inputPassword2"
							placeholder="Course" name="course">
					</div>
					<div class="col-auto">
						<button type="submit" class="btn btn-success mb-3">Search</button>
					</div>
					<div class="col-auto">
						<button type="button" class="btn btn-secondary mb-3">Reset</button>
					</div>
				</form>
				<div>

					<table class="table table-striped" id="stduentTable">
						<thead>
							<tr>

								<th scope="col">Student ID</th>
								<th scope="col">Name</th>
								<th scope="col">Course Name</th>
								<th scope="col">Details</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${applicationScope.slist}" var="data">
								<tr>

									<td>${data.studentId}</td>
									<td>${data.studentName }</td>
									<td><c:forEach var="course" items="${data.studentCourse}">
											<c:out value="${course}" />
										</c:forEach></td>
									<td><button
												type="submit" class="btn btn-secondary mb-2" onclick="location.href= 'student_profile.jsp?id=${data.studentId}&name=${data.studentName}&dob=${data.studentDOB}&phone=${data.studentPhone}'">See
												More</button></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
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
