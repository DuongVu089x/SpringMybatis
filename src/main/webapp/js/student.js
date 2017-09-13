var currentPage = 1;
var currentStudentId;
var keyword = $("#search").val();
var token = $("meta[name='jwt']").attr("content");
var role = $("meta[name='role']").attr("content");

var XML_CHAR_MAP = {
	'<' : '&lt;',
	'>' : '&gt;',
	'&' : '&amp;',
	'"' : '&quot;',
	"'" : '&apos;'
};

function escapeXml(s) {
	return s.replace(/[<>&"']/g, function(ch) {
		return XML_CHAR_MAP[ch];
	});
}

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
	 $("#dateOfBirth").datepicker({
		  autoSize: true,
		  dateFormat: 'dd/mm/yy',
		  changeMonth: true,
		  changeYear: true,
		  yearRange: "-50:+0"
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
function formatDate(stringDate){
	var date = new Date(stringDate);
	return date.getDate() + '/' + (date.getMonth() + 1)  + '/' +  date.getFullYear();
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
			 $("#dateOfBirth").datepicker({
				  autoSize: true,
				  dateFormat: 'dd/mm/yy'
			 });
			var strResult = '';

			$.each(response,function(index, value) {
				strResult += "<tr>"
								+"<td>"+escapeXml(value.name)+"</td>"
								+"<td>"+escapeXml(value.code)+"</td>"
								+"<td>"+formatDate(value.dateOfBirth)+"</td>"
								+"<td>"+value.averageScore+"</td>"
								+"<td>"+escapeXml(value.address)+ "</td>";
				if(role === 'ADMIN') {
					strResult += "<td>"
								 +	"<button type='button' class='btn btn-success' data-toggle='modal' data-target='#modalInsertUpdate' onclick='getStudentInfo(" + value.id + ")'>Update</button>"
								 +	"<button type='button' class='btn btn-danger' data-toggle='modal' data-target='#modalDelete' onclick='showDeleteStudentInfo(" + value.id + ")'>Delete</button>"
								 +"</td>";
				}
				strResult += "</tr>";
			});
			$("#data-student tbody").html(strResult);
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
			$("#dateOfBirth").datepicker({
				 dateFormat: 'dd/MM/yy'
			}).datepicker('setDate', new Date(response.dateOfBirth));
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
	$('.error-message').each(function( index ) {
		$(this).empty();
		$(this).addClass('alert-hide');
	});

	var id = currentStudentId;
	var student = {
		id : currentStudentId,
		name : $("#name").val(),
		code : $("#code").val(),
		address : $("#address").val(),
		averageScore : $("#averageScore").val(),
		dateOfBirth :  $("#dateOfBirth").datepicker({ dateFormat: "dd/MM/yy" }).val()
	};

	if (checkValidStudent(student)) {
		var data = JSON.stringify(student);
		if (currentStudentId != -1) {
			updateStudent(currentStudentId, data);
		} else {
			insertStudent(data);
		}
	}
}

function showMessage(field, message){
	$("#error-"+field).append("<p>"+message+"</p>");
	$("#error-"+field).removeClass("alert-hide");
}

function checkValidStudent(student){
	$('.error-message').each(function( index ) {
		$(this).empty();
		$(this).addClass('alert-hide');
	});
	var flag = true;
	if(student.name.trim().length < 2 || student.name.trim().length > 255){
		showMessage("name","Name of student must be between 2 to 255");
		flag = false;
	}
	if(student.code.trim().length < 2 || student.code.trim().length > 11 || !(/^[0-9]*$/.test(student.code))){
		showMessage("code","Code of student must be between 2 and 11 and only should containt number");
		flag = false;
	}
	if(student.address.trim().length < 2 || student.address.trim().length > 50){
		showMessage("address","Address of student must be between 2 and 50");
		flag = false;
	}
	if(student.averageScore === "" || student.averageScore< 0.0 || student.averageScore > 10.0){
		showMessage("averageScore","Average Score of student must be between 0.0 and 10.0");
		flag = false;
	}
	if(!(/^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/).test(student.dateOfBirth)){
		showMessage("dateOfBirth","Date not valid. Please enter date with pattern= dd/mm/yyyy");
		flag = false;
	}
	return flag;
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
			console.log(xhr.responseJSON);
			if(typeof xhr.responseJSON.errors !== "undefined" && xhr.responseJSON.errors.length > 0){
				addErrorMessage(xhr.responseJSON.errors);
			}else{
				console.log(xhr.responseJSON.error);
			}
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
			if(xhr.responseJSON.errors.length > 0){
				addErrorMessage(xhr.responseJSON.errors);
			}else{
				console.log(xhr.responseJSON.error);
			}


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



