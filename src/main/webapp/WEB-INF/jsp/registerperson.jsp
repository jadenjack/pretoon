<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>${webtoon_name}</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
  </head>
  <body>
    <h2>${webtoon_name} 후보 등록</h2>
    
    <form action="registerprocessing" method="post" enctype="multipart/form-data">
    	<input type="hidden" name="webtoon_name" value="${webtoon_name}"/>
	    <h2>이름</h2>
	    <input type="text" name="person_name"/>
	    
	    <h2>설명</h2>
	    <input type="text" name="person_description"/>
    	
    	<input type="file" name="person_image" accept="image/*">
    	<input type="submit" value="ok"/>
    	
    </form>
    

  </body>
  
</html>