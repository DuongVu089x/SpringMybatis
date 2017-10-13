var currentPage = 1;
var currentStudentId;
var patternDate = /^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/;
var patternNumber = /^[0-9]*$/;

var keyword = $("#search").val();
var token = $("meta[name='jwt']").attr("content");
var role = $("meta[name='role']").attr("content");
var accessDate = new Date();
accessDate.setDate(accessDate.getDate() - 365);

var XML_CHAR_MAP = {
	'<': '&lt;',
	'>': '&gt;',
	'&': '&amp;',
	'"': '&quot;',
	"'": '&apos;'
};

function escapeXml(s) {
	return s.replace(/[<>&"']/g, function (ch) {
		return XML_CHAR_MAP[ch];
	});
}

function updateCss() {
	// Update heigh for div content student
	const height = $(document).height();
	const heightHeader = $('.header').height();
	const heightFooter = $('#myFooter').height();
	if ((height - heightHeader - heightFooter) > 500) {
		$('.student-content').height(height - heightHeader - heightFooter);
	} else {
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
	if (currentPage !== index) {
		$("#data-student tbody").html("");
		currentPage = index;
		getStudentWithPage(index);
	}
	event.preventDefault();
}

// Search student with value in input#search
// student match if name, code or address containt value
function searchStudent() {
	clearTimeout($(this).data("timer"));
	keyword = $("#search").val();
	if (keyword.length >= 0) {
		$("img#loading").addClass("active");
		$(this).data("timer", setTimeout(function () {
			$("#data-student tbody").html("");
			getStudentWithPage(currentPage);
			getTotalPage();
			$("img#loading").removeClass("active");
		}, 1000));
	}
}

// Get total page with reponse is total student / 5
function getTotalPage(page) {
	result = false;
	$(".pagination").html("");
	$.ajax({
		url: "/api/student/getTotalPage?keyword=" + keyword,
		dataType: "json",
		headers: {
			'Authorization': token
		},
		async: false,
		success: function (response) {
			var strResult = '';
			for (var i = 0; i < response; i++) {
				var strClass = '';
				if (i === page) {
					strClass = 'active';
				}
				strResult += "<li class=" + strClass + "><a href='#' onclick='changePage(" + (i + 1) + ",event)'>" + (i + 1) + "</a></li>";
			}
			$(".pagination").append(strResult);
			if (response > 0) {
				result = true;
			}
		}
	});
	return result;
}
// Format date with pattern dd/mm/yyyy
function formatDate(stringDate) {
	var date = new Date(stringDate);
	return date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear();
}

// Format length of text
function formatLength(stringData) {
	if (stringData.length > 25) {
		return stringData.substring(0, 25) + '...';
	}
	return stringData;
}

// Get list student of current page
// with response success is list Student
// and bind to view
function getStudentWithPage(page) {
	$.ajax({
		url: "/api/student/getStudentWithPage?page=" + (page - 1) + "&keyword=" + keyword,
		dataType: "json",
		headers: {
			'Authorization': token
		},
		success: function (response) {
			if (response.length === 0 && page != 1) {
				getStudentWithPage(--page);
			} else {
				$("#dateOfBirth").datepicker({
					autoSize: true,
					dateFormat: 'dd/mm/yy'
				});
				var strResult = '';
				$.each(response, function (index, value) {
					strResult += "<tr>" +
						"<td>" + formatLength(escapeXml(value.name)) + "</td>" +
						"<td>" + escapeXml(value.code) + "</td>" +
						"<td>" + formatDate(value.dateOfBirth) + "</td>" +
						"<td>" + value.averageScore + "</td>" +
						"<td>" + formatLength(escapeXml(value.address)) + "</td>";
					if (role === 'ADMIN') {
						strResult += "<td>" +
							"<button type='button' class='btn btn-success' data-toggle='modal' data-target='#modalInsertUpdate' onclick='getStudentInfo(" + value.id + ")'>Update</button>" +
							"<button type='button' class='btn btn-danger' data-toggle='modal' data-target='#modalDelete' onclick='showDeleteStudentInfo(" + value.id + ")'>Delete</button>" +
							"</td>";
					}
					strResult += "</tr>";
				});
				$("#data-student tbody").html(strResult);
				changePageActive(page - 1);
			}
		}
	});
}

function changePageActive(index) {
	$('.pagination li').each(function (i) {
		$(this).removeClass('active');
		if (i === index) {
			$(this).addClass('active');
		}
	});
}

// Show modal insert of update.
// Get student info with parameter is user id
// and response is detail of user have id is parameter id
function getStudentInfo(studentId) {
	clearForm();
	$.ajax({
		url: "/api/student/getStudentInfo?id=" + studentId,
		dataType: "json",
		headers: {
			'Authorization': token
		},
		success: function (response) {
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

// If currentStudentId is -1 then insert usert to DB
// If currentStudentId is not -1 then update user
function updateOrInsertStudent() {
	$('.error-message').each(function (index) {
		$(this).empty();
		$(this).addClass('alert-hide');
	});

	var id = currentStudentId;
	var student = {
		id: currentStudentId,
		name: $("#name").val(),
		code: $("#code").val(),
		address: $("#address").val(),
		averageScore: $("#averageScore").val(),
		dateOfBirth: $("#dateOfBirth").datepicker({
			dateFormat: "dd/MM/yy"
		}).val()
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

// Show message error to form #modalInsertUpdate
function showMessage(field, message) {
	$("#error-" + field).append("<p>" + message + "</p>");
	$("#error-" + field).removeClass("alert-hide");
}

function checkLengthFloat(bigDecimal) {
	var arrNum = bigDecimal.split(".");
	var length = arrNum.length;
	if (length > 1 && arrNum[1].length > 10) {
		return false;
	}
	return true
}

// Check vaild object student if success then send request to server
// If get error message then show it to form #modalInsertUpdate
function checkValidStudent(student) {
	$('.error-message').each(function (index) {
		$(this).empty();
		$(this).addClass('alert-hide');
	});
	var flag = true;
	if (student.name.trim().length < 2 || student.name.trim().length > 255) {
		showMessage("name", "Name of student must be from 2 to 255 char");
		flag = false;
	}
	if (student.code.trim().length < 2 || student.code.trim().length > 11 || !(patternNumber.test(student.code))) {
		showMessage("code", "Code of student must be from 2 to 11 char and only should containt number");
		flag = false;
	}
	if (student.address.trim().length < 2 || student.address.trim().length > 50) {
		showMessage("address", "Address of student must be from 2 to 50 char");
		flag = false;
	}
	if (student.averageScore === "" || student.averageScore < 0.0 || student.averageScore > 10.0 || !(checkLengthFloat(student.averageScore))) {
		showMessage("averageScore", "Average Score of student must be from 0.0 to 10.0 char and the part after '.' must be from 0 to 10 char");
		flag = false;
	}
	var dateParts = student.dateOfBirth.split("/");
	dateChoose = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
	if (!patternDate.test(student.dateOfBirth) || dateChoose.getTime() >= accessDate.getTime()) {
		showMessage("dateOfBirth", "Please enter date with pattern = dd/mm/yyyy and day must be smaller than present one year");
		flag = false;
	}
	return flag;
}

// Update user info
// If success modal will hide and info user was update
// If error, detail of error will show in modal
function updateStudent(id, data) {
	$.ajax({
		type: "POST",
		url: "/api/student/updateStudent?id=" + id,
		headers: {
			'Authorization': token,
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		data: data,
		success: function (reponse) {
			$("#data-student tbody").html("");
			$('#modalInsertUpdate').modal('hide');
			getStudentWithPage(currentPage);
		},
		error: function (xhr, status, error) {
			console.log(xhr.responseJSON);
			if (typeof xhr.responseJSON.errors !== "undefined" && xhr.responseJSON.errors.length > 0) {
				addErrorMessage(xhr.responseJSON.errors);
			} else {
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
		type: "POST",
		url: "/api/student/insertStudent",
		headers: {
			'Authorization': token,
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		data: data,
		success: function (reponse) {
			$("#data-student tbody").html("");
			$('#modalInsertUpdate').modal('hide');
			getStudentWithPage(currentPage);
			getTotalPage();
		},
		error: function (xhr, status, error) {
			if (xhr.responseJSON.errors.length > 0) {
				addErrorMessage(xhr.responseJSON.errors);
			} else {
				console.log(xhr.responseJSON.error);
			}
		}
	});
}

// Bind error to view
function addErrorMessage(errors) {
	$.each(errors, function (index, value) {
		if ($("#error-" + value.field).text().length <= 0) {
			$("#error-" + value.field).append("<p>" + value.defaultMessage + "</p>");
			$("#error-" + value.field).removeClass("alert-hide");
		}
	});
}

function showDeleteStudentInfo(userId) {
	currentStudentId = userId;
}

// Delete user info with parameter id
function deleteStudentInfo() {
	$.ajax({
		type: "DELETE",
		headers: {
			'Authorization': token
		},
		url: "/api/student/deleteStudent?id=" + currentStudentId,
		success: function (reponse) {
			$("#data-student tbody").html("");
			$('#modalDelete').modal('hide');
			getTotalPage();
			getStudentWithPage(currentPage);
		}
	});
}

function clearForm() {
	$("#name").val("");
	$("#code").val("");
	$("#dateOfBirth").val("");
	$("#averageScore").val("");
	$("#address").val("");
	currentStudentId = -1;
	$('.error-message').each(function (index) {
		$(this).empty();
		$(this).addClass('alert-hide');
	});
}

$(document).ready(function () {
	updateCss();
	if (getTotalPage(0)) {
		getStudentWithPage(currentPage);
	}
});