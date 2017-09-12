<%@ page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<footer id="myFooter">
		<div class="container">
			<div class="row">
				<div class="col-sm-3 myCols">
					<h5>Get started</h5>
					<ul>
						<li><a href="/student/" target="_blank">Home</a></li>
						<li><a href="/login/" target="_blank">Sign up</a></li>
					</ul>
				</div>
				<div class="col-sm-3 myCols">
					<h5>About us</h5>
					<ul>
						<li><a href="https://www.facebook.com/" target="_blank">Facebook</a></li>
						<li><a href="https://www.linkedin.com/feed/" target="_blank">Linkedin</a></li>
						<li><a href="#">Reviews</a></li>
					</ul>
				</div>
				<div class="col-sm-3 myCols">
					<h5>Support</h5>
					<ul>
						<li><a href="https://duongvu089x.github.io/" target="_blank">GitHub</a></li>
						<li><a href="https://drive.google.com/open?id=0B92EmFc7biHeQ2pMSVhsNHpsRkE" target="_blank">Drive</a></li>
					</ul>
				</div>
				<div class="col-sm-3 myCols">
					<h5>Legal</h5>
					<ul>
						<li><a href="#">Terms of Service</a></li>
						<li><a href="#">Terms of Use</a></li>
						<li><a href="#">Privacy Policy</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="social-networks">
			<a href="#" class="twitter"><i class="fa fa-twitter"></i></a> <a
				href="#" class="facebook"><i class="fa fa-facebook-official"></i></a>
			<a href="#" class="google"><i class="fa fa-google-plus"></i></a>
		</div>
		<div class="footer-copyright">
			<p>© 2016 Copyright Text</p>
		</div>
	</footer>
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<c:if test="${pageContext.request.requestURI == '/views/student.jsp'}">
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script src="/js/student.js"></script>
	</c:if>
	<c:if test="${pageContext.request.requestURI == '/views/login.jsp'}">
		<script src="/js/login.js"></script>
	</c:if>
</body>
</html>