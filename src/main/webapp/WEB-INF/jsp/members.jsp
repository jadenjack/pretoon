<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="false"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8" />
<title>${webtoon_name}</title>
<link rel="stylesheet" type="text/css" href="/resources/css/members.css" />
</head>
<body>

	<div class="member_list_wrap">
		<h2>${webtoon_name}후보</h2>
			<div class="button_group_wrap">
				<div class="button_group">
					<form action="./register">
						<input class="member_register button" type="submit" value="후보 등록" />
					</form>
					<form action="./vote">
						<input class="member_vote button" type="submit" value="투표하기" />
					</form>d
				</div>
			</div>
			<div class="member_group">
				<c:forEach items="${list}" var="list" varStatus="loop">
				<c:if test="${loop.index%3==0}">
				<ul class="member_list">
				</c:if>
					<li>
						<div class="inner">
						<a> <!-- TODO:img insert --> 
							<img src="/resources/img/upload/${list.image}" class="person_image"/>
							<p class="title_wrap">
								<span class="title"><c:out value="${list.name}" /></span>
							</p>
							<p class="description"><c:out value="${list.description}" /></p>
							<p class="vote_wrap">
								<span class="vote"><c:out value="${list.vote}표" /></span>
							</p>
						</a>
						</div>
					</li>
		
				<c:if test="${loop.last || loop.index%3==2}">
				</ul>
				</c:if>
		
				</c:forEach>
			</div>
	</div>

</body>

</html>