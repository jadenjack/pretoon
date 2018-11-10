<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>${webtoon_name}</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/registerperson.css"/>
  </head>
  <body>
  	<div class="register_form_wrap">
	    <h2>${webtoon_name} 후보 등록</h2>
	    
	    		<form action="./registerprocessing" method="post" enctype="multipart/form-data">
	    	<input type="hidden" name="webtoon_name" value="${webtoon_name}"/>
	    	<div class="button_group">
	    			<input class="button" type="submit" value="후보 등록"/>
    			
	    	</div>
	    	<table class="form_table">
    			<tr>
				    <td style="width: 100px;">
				    	이름
			    	</td>
			    	<td>
				    	<input type="text" name="person_name" placeholder="이름을 입력하세요.(최대길이 20)" style="width: 99%;"maxlength="20" />
			    	</td>
				</tr>
				<tr>
			    	<td style="width: 100px;">
				    	이미지
			    	</td>
			    	<td>
			    		<input type="file" name="person_image" accept="image/*">
		    		</td>
	    		</tr>
				<tr>  
				    <td colspan='2' style="height:200px">
				    	<input type="text" name="person_description" placeholder="설명을 입력하세요.(최대길이 50)" style="width: 100%;height: 100%;" maxlength="50">
				    </td>
			    </tr>
	    	</table>
			    </form>
	    
    </div>

  </body>
  
</html>