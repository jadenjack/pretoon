<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>툰통령을 뽑아보자.</title>
    <link rel="stylesheet" type="text/css" href="./css/index.css"/>
  </head>
  <body>
    <div class="webtoon_list_wrap">
	    <h2>투표 리스트</h2>
    	<c:forEach items="${webtoon_list}" var="webtoon_name" varStatus="loop">
    	<c:if test="${loop.index%3==0}">
    		<ul class="webtoon_list">
    	</c:if>
    		<li>
    			<div class="inner">
    				<a href="${webtoon_name}/members">
	    				<img src="./img/${webtoon_name }.jpg" width="320" height="320">
	    				<span class="webtoon_name">${webtoon_name}</span>
    				</a>
    			</div>
   			</li>
  		
  		<c:if test="${loop.last || loop.index%3==2 }">
  		</ul>
  		</c:if>	
    	</c:forEach>
    </div>
  </body>
  
</html>