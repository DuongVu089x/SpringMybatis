var currentPage = 1;
var currentStudentId;
var keyword = $("#search").val();
var token = $("meta[name='jwt']").attr("content");

function updateCss() {
	// Update heigh for div content student
	const height = $(document).height();
	const heightHeader = $('.header').height();
	const heightFooter = $('#myFooter').height();
	if((height - heightHeader - heightFooter)>500){
		$('.student-content').height(height - heightHeader - heightFooter);
	}else{
		$('.student-content').height(500);
	}
	// Update code config for input Date Of Birth
	var date_input=$('input[name="dateOfBirth"]'); //our date input has the name "date"
	var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
	date_input.datepicker({
		format: 'dd/mm/yyyy',
		container: container,
		todayHighlight: true,
		autoclose: true,
	});

}

// Click change page current
function changePage(index, event) {
	$("#data-student tbody").html("");
	getStudentWithPage(index);
	currentPage = index;
	event.preventDefault();
}

// Search student with value in input#search
// student match if name, code or address containt value
function searchStudent(){
	keyword = $("#search").val();
	$("#data-student tbody").html("");
	getStudentWithPage(currentPage);
	getTotalPage();
}

// Get total page with reponse is total student / 5
function getTotalPage() {
	$(".pagination").html("");
	$.ajax({
		url : "/api/student/getTotalPage?keyword=" + keyword,
		dataType : "json",
		headers : {
			'Authorization' : token
		},
		success : function(response) {
			var strResult = '';
			for (var i = 0; i < response; i++) {
				strResult += "<li><a href='#' onclick='changePage("+ ( i + 1 ) +",event)'>" + (i + 1) + "</a></li>";
			}
			$(".pagination").append(strResult);
		}
	});
}

// Get list student of current page
// with response success is list Student
// and bind to view
function getStudentWithPage(page) {
	$.ajax({
		url : "/api/student/getStudentWithPage?page=" + (page - 1)+"&keyword=" + keyword,
		dataType : "json",
		headers : {
			'Authorization' : token
		},
		success : function(response) {
			var strResult = '';
			$.each(response,function(index, value){
				strResult += "<tr>"
								+"<td>"+value.name+"</td>"
								+"<td>"+value.code+"</td>"
								+"<td>"+value.dateOfBirth+"</td>"
								+"<td>"+value.averageScore+"</td>"
								+"<td>"+value.address+"</td>"
								+"<td>"
								+	"<button type='button' class='btn btn-success' data-toggle='modal' data-target='#modalInsertUpdate' onclick='getStudentInfo(" + value.id + ")'>Update</button>"
								+	"<button type='button' class='btn btn-danger' data-toggle='modal' data-target='#modalDelete' onclick='showDeleteStudentInfo(" + value.id + ")'>Delete</button>"
								+"</td>"
							+"</tr>";
			});
			$("#data-student tbody").append(strResult);
		}
	});
}

// Get student info with parameter is user id
// and response is detail of user have id is parameter id
function getStudentInfo(studentId) {
	clearForm();
	$.ajax({
		url : "/api/student/getStudentInfo?id="+studentId,
		dataType:"json",
		headers : {
			'Authorization' : token
		},
		success: function(response){
			console.log(response);
			$("#name").val(response.name);
			$("#code").val(response.code);
			$("#dateOfBirth").val(response.dateOfBirth);
			$("#averageScore").val(response.averageScore);
			$("#address").val(response.address);
			currentStudentId = response.id;
		}
	});
}

// Show modal insert of update.
// If currentStudentId is -1 then insert usert to DB
// If currentStudentId is not -1 then update user 
function updateOrInsertStudent() {
	var id = currentStudentId;
	var student = {
		id : currentStudentId,
		name : $("#name").val(),
		code : $("#code").val(),
		address : $("#address").val(),
		averageScore : $("#averageScore").val(),
		dateOfBirth : $("#dateOfBirth").val()
	};

	var data = JSON.stringify(student);
	if (currentStudentId != -1) {
		updateStudent(currentStudentId, data);
	} else {
		insertStudent(data);
	}

}

// Update user info
// If success modal will hide and info user was update
// If error, detail of error will show in modal
function updateStudent(id, data) {
	$.ajax({
		type : "POST",
		url : "/api/student/updateStudent?id=" + id,
		headers : {
			'Authorization' : token,
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		data : data,
		success : function(reponse) {
			$("#data-student tbody").html("");
			$('#modalInsertUpdate').modal('hide');
			getStudentWithPage(currentPage);
		},
		error : function(xhr, status, error) {
			addErrorMessage(xhr.responseJSON.errors);
		}
	});
}

// Insert user info
// If success modal will hide and info user was update
// If error, detail of error will show in modal
function insertStudent(data) {
	$.ajax({
		type : "POST",
		url : "/api/student/insertStudent",
		headers : {
			'Authorization' : token,
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		data : data,
		success : function(reponse) {
			$("#data-student tbody").html("");
			$('#modalInsertUpdate').modal('hide');
			getStudentWithPage(currentPage);
			getTotalPage();
		},
		error : function(xhr, status, error) {
			addErrorMessage(xhr.responseJSON.errors);
		}
	});
}

// Bind error to view
function addErrorMessage(errors) {
	$.each(errors, function(index, value) {
		if($("#error-" + value.field).text().length <=0){
			$("#error-" + value.field).append("<p>" + value.defaultMessage + "</p>");
			$("#error-" + value.field).removeClass("alert-hide");
		}
	});
}

function showDeleteStudentInfo(userId){
	currentStudentId = userId;
}

// Delete user info with parameter id
function deleteStudentInfo() {
	$.ajax({
		type : "DELETE",
		headers : {
			'Authorization' : token
		},
		url : "/api/student/deleteStudent?id=" + currentStudentId,
		success : function(reponse) {
			$("#data-student tbody").html("");
			$('#modalDelete').modal('hide');
			getStudentWithPage(currentPage);
			getTotalPage();
		}
	});
}

function clearForm(){
	$("#name").val("");
	$("#code").val("");
	$("#dateOfBirth").val("");
	$("#averageScore").val("");
	$("#address").val("");
	currentStudentId = -1;
	$('.error-message').each(function( index ) {
		$(this).empty();
		$(this).addClass('alert-hide');
	});
}

$(document).ready(function() {
	updateCss();
	getTotalPage();
	getStudentWithPage(currentPage);
	
	
});



