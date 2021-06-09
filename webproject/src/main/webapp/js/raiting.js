$(document).ready(function() {
	$("input[type='radio']").click(function() {
		var sim = $("input[type='radio']:checked").val();
        $.ajax({
            url: "controller?command=REDIRECT_HOME",
            success: function(url) {
                alert(url);
            }
        });
        // $.ajax({
        //     method: "POST",
        //     url: "controller/command=REDIRECT_HOME",
        //     data: { command="REDIRECT_HOME" }
        // })
        // .success(
        //     function(){
        //         alert("success");
        //     })
        // .error(
        //     function() {
        //         alert("error")
        //     })
        // .complete(
        //     function() {
        //         alert("end of running")
        //     }
        // );
	});
});