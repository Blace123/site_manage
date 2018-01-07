/**
 * 
 */

$(".site_delete").on("click",function(event){
	event.preventDefault();
	$.post($(this).attr("href") ,
		function(data){
			window.location.href = "/index/article";
		});
}); 

$('.article_input').on("click", function(){
	var url = $(this).parents("form").attr("action");
	var data = $(this).parents("form").serialize();
	
	$.post(url, data, function(data){
		alert("保存成功");
		window.location.href = "/index/article";
	});
});

$('.article_update').on("click", function(){
	var data = $(this).parents("form").serialize();
	var url = $(this).parents("form").attr("action");
	$.post(url, data, function(data){
		window.location.href = "/index/article";
	});
});





var files = [];
$(document).ready(function(){
  $("*[name='file']").change(function(e){
	  console.log(location.href);
    files = this.files;
    for (var i = 0; i < files.length; i++) {
        //console.log(files[i]);
      }
  });
});
