<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
<script src="http://d3js.org/d3.v3.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/css/graph.css" />
<script>
		var rawdata = JSON.parse('${list}');
	</script>
<script src="/resources/js/d3_app.js"></script>
</head>
<body>

	<div class="chart_wrap">
			<h2>${webtoon_name}그래프</h2>
		<div class="button_group">
			<form action="./members">
				<input class="member_register button" type="submit" value="돌아가기" />
			</form>
		</div>
		<div id="chart"></div>
	</div>

</body>
</html>