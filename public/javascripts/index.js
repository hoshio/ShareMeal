$(function(){
	$("#sendbtn").click( function(){
		var jsondata = {
				'input': $("#input").val()
		};
		$.post("/ajax",jsondata,function(result){
			var res = "tweet:" + result.tweet + ", username:" + result.username;
			$("#datas").text(res);
		},
		"json"
		);
	})
})