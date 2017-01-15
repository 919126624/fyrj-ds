<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
body{font-size: 15px;font-family: 微软雅黑}
button{width: 100px;height: 30px}
input{height: 30px;width: 300px}
a{text-decoration: none;}
.invoke-title{color: red;font-size: 18px}
.invoke-border{border: 3px solid #ccc; margin: 30px 30px 30px 30px ;padding: 30px 30px 30px 30px; }
</style>

<script type="text/javascript" src="../asset/js/jquery-2.0.3.min.js"></script> 
<script type="text/javascript">
var baseUrl = "http://"+window.location.host+"/cims-web/";
function invokeServer(url,data,callBackFn){
	$.ajax({
		url:url,
		dataType: "json",
		type:"POST",
		data: data,	
		success:function(res){
			callBackFn(res)
		},
		error:function(){
			console.log("ERROR")
		}
	});
}

$(function(){
	$("#redis_put").click(function(){
		var redisKey = $("#redisKey").val();
		var redisValue = $("#redisValue").val();
		if(!redisKey){alert("redisKey not be should null!"); return false;}
		if(!redisValue){alert("redisValue not be should null!"); return false;}
		invokeServer(baseUrl+"invoke/redisPut",{key:redisKey,value:redisValue},function(result){
			$("#redisTestResult").val(result.value);
		});
	});

	$("#redis_get").click(function(){
		var redisKey = $("#redisKey").val();
		if(!redisKey){alert("redisKey not be should null!"); return false;}
		invokeServer(baseUrl+"invoke/redisGet",{key:redisKey},function(result){
			$("#redisTestResult").val(result.value);
		});
	});
})
</script>
<title>系统服务总测试页面</title>
</head>
<body>
	<div class="invoke-div1">
		
	</div>
	
	<div class="invoke-border invoke-div2">
		<p class="invoke-title">系统快速入口</p>
		<a href="#">nexus仓库中心&nbsp&nbsp&nbsp</a>
		<a href="#">dubbo管理控制台中心&nbsp&nbsp&nbsp</a>
		<a href="#">cas认证中心&nbsp&nbsp&nbsp</a>
		<a href="#">jenkins持续集成中心&nbsp&nbsp&nbsp</a>
		<a href="#">rabbit mq中心&nbsp&nbsp&nbsp</a>
	</div>
	
	<div class="invoke-border invoke-div3">
		<p class="invoke-title">redis集群测试</p>
		key <input type="text" id="redisKey" name="redisKey">
		value <input type="text" id="redisValue" name="redisValue"><br/><br/>
		<button id="redis_get">get</button>
		<button id="redis_put">put</button>
		<p>操作结果</p>
		<textarea rows="8" cols="150" id="redisTestResult">
		
		</textarea>
	</div>
	
	<div class="invoke-border invoke-div4">
		<p class="invoke-title">redis分布式锁与zookeeper分布式锁实现与性能对比测试</p>
		要启动的线程数量 <input type="text" id="lockThreadNum" name="lockThreadNum">
		要竞争的资源整数值 <input type="text" id="lockResNum" name="lockResNum"><br/><br/>
		<button id="redis_put">开始测试</button>
		<p>测试结果分析</p>
		<textarea rows="8" cols="150">
		
		</textarea>
	</div>
	
	<div class="invoke-border invoke-div5">
		<p class="invoke-title">rabbit Mq集群测试</p>
		send message value <input type="text" id="mqValue" name="redisValue"><br/><br/>
		<button id="redis_put">put</button>
		<p>operation result</p>
		<textarea rows="5" cols="150">
		
		</textarea>
	</div>
</body>
</html>