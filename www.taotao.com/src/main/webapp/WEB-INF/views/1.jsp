<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>123123123</title>
		<script type="text/javascript" src="img/jquery.js"></script>
		<script type="text/javascript">
			$(function(){  
	        //按钮单击时执行  
	        $("#testAjax").click(function(){  
	              //取Ajax返回结果  
	              //为了简单，这里简单地从文件中读取内容作为返回数据  
	              htmlobj=$.ajax({url:"http://manage.taotao.com/rest/api/item/cat",async:false});  
	               //显示Ajax返回结果  
	               $("#myDiv").html(htmlobj.responseText);  
         });  
    });  
			
		</script>
	</head>
	<body>
		<div id="myDiv"><h2>通过 AJAX 改变文本</h2></div>  
        <button id="testAjax" type="button">Ajax改变内容</button>  
	</body>
</html>
