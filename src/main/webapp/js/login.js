$(document).ready(function() {
	updateCss();
});

function updateCss() {
	const height = $(document).height();
	const heightHeader = $('.header').height();
	const heightFooter = $('#myFooter').height();
	if((height - heightHeader - heightFooter)>300){
		$('.login-page').height(height - heightHeader - heightFooter);
	}else{
		$('.login-page').height(300);
	}

}