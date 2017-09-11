<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="layout/_header.jsp" />

<div class="student-content">
	<div class="container">
		<div class="row">
			<h4 class="text-center">
				<b>Student</b>
			</h4>
			<div class="row">
				<button type="button" class="btn btn-success  col-md-1" data-toggle="modal" data-target="#modalInsertUpdate" onclick="clearForm()">Insert</button>
				<div class="col-md-5 col-md-push-6">
		            <div id="custom-search-input">
		                <div class="input-group col-md-12">
		                    <input type="text" id="search" class="form-control input-lg" placeholder="Name, Code, Address"  onkeyup="searchStudent()"/>
		                    <span class="input-group-btn">
		                        <button class="btn btn-info btn-lg" type="button" onclick="searchStudent()">
		                            <i class="glyphicon glyphicon-search"></i>
		                        </button>
		                    </span>
		                </div>
		            </div>
		        </div>
			</div>
			<table id="data-student" class="table table-hover">
				<thead>
					<tr>
						<th>Name</th>
						<th>Code</th>
						<th>Date of birth</th>
						<th>Average Score</th>
						<th>Address</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<ul class="pagination col-sm-4 col-sm-push-4">
			</ul>
			<!-- Modal Update-->
			<div class="modal fade" id="modalInsertUpdate" role="dialog">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Modal Header</h4>
						</div>
						<div class="modal-body">
							<form>
								<div class="form-group">
									<label for="exampleInputEmail1">Name</label> 
									<input type="text" class="form-control" id="name" placeholder="Name">
									<div id="error-name" class="error-message alert alert-danger alert-hide"></div>
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Code</label> 
									<input type="text" class="form-control" id="code" placeholder="Code">
									<div id="error-code" class="error-message alert alert-danger alert-hide"></div>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Date of birth</label> 
									<div class="input-group">
										<input class="form-control" id="dateOfBirth" name="dateOfBirth" placeholder="DD/MM/YYYY" type="text" />
										<div class="input-group-addon">
											<i class="fa fa-calendar"> </i>
										</div>
									</div>
									<div id="error-dateOfBirth" class="error-message alert alert-danger alert-hide"></div>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Average Score</label> 
									<input type="number" class="form-control" id="averageScore" placeholder="Average Score">
									<div id="error-averageScore" class="error-message alert alert-danger alert-hide"></div>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Address</label> 
									<input type="text" class="form-control" id="address" placeholder="Address">
									<div id="error-address" class="error-message alert alert-danger alert-hide"></div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
									<button type="button" id="submitUpdate" class="btn btn-primary" onclick= "updateOrInsertStudent()">Submit</button>
								</div>
							</form>
						</div>

					</div>
				</div>
			</div>
			<!-- Delete -->
			<div class="modal fade" id="modalDelete" role="dialog">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Modal Header</h4>
						</div>
						<div class="modal-body">
							<p class="bg-danger">Do you want delete this record?</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-default" onclick="deleteStudentInfo()">OK</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="layout/_footer.jsp" />