# pretoon
##나의 최애캐! 툰통령 만들기
https://github.com/NAVER-CAMPUS-HACKDAY/common/issues/5<br/><br/>

-주제선정 배경<br/>
내가 가장 애정하는 캐릭터, 웹툰에서도 최고였으면 좋겠다.<br/>
웹툰 서비스는 댓글/좋아요 외에 작가와 사용자간의 커뮤니케이션 수단이 부족하다.<br/>
자기가 좋아하는 웹툰 캐릭터를 직접 투표하여 작가에게 힘을 주고, 캐릭터 선호도에 대한 여론을 반영할 수 있게 한다.<br/>
확장해서 특정 웹툰에 출연하는 캐릭터들에 대해 투표를 만들어서 작품의 대해 독자들의 의견을 전달하는 통로로 사용할 수 있다.<br/>
* 개발 요구사항 (필수)<br/>
	- 후보 등록 기능<br/>
	- 유권자 투표 기능<br/>
	- 실시간 집계 기능<br/>
	- 대선 실시간 중계 기능 구현<br/>
	- 득표율 그래프 구현<br/>
	- 서버 푸시 기능 사용<br/>
	- CSS 활용 애니메이션 구현<br/>
* 개발 요구사항 (선택)<br/>
	- 실제 대선 중계처럼 여러가지 애니메이션 아이디어 적용<br/>
* 개발언어<br/>
	- server : Java(Spring boot)<br/>
	- client : JavaScript(vue, react 중 택1)<br/>
* 플랫폼<br/>
	- 웹<br/>

발생했던 문제점<br/>
1. Tomcat7 사용을 하면서 발생한 lib 버전문제<br/>
문제) gradle에서 관리되지 않는 Tomcat7의 내장 lib를 사용하면서 발생했던 문제점<br/>
해결) Tomcat8으로 변경<br/>
2. 윈도우, 우분투, EC2 서로 달랐던 MySQL 비밀번호 문제점<br/>
문제) 개발 환경과 배포 환경의 MySQL 비밀번호가 달랏던 문제. 빌드는 되지만, 톰캣을 시작할 때 에러 발생.<br/>
해결) catalina.out(톰캣 로그파일)을 보고 확인 후 비밀번호 통일<br/>
3. 정적 리소스의 주소 문제점<br/>
문제) 정적 리소스(image, css, javascript)와 RequestMapping 주소들이 배포 환경에 따라 경로가 다르게 배포됨<br/>
해결) ROOT로 배포 및 80포트로 배포하면서 어느 환경에서나 동일한 경로를 갖게 끔 설정<br/>
4. 인코딩문제<br/>
문제)톰캣, MySQL, JSP<br/>
톰캣) server.xml의 <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8"/>
JSP) <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
MySQL) my.ini(WINDOWS), my.cnf(LINUX) 파일에서 아래와 같이 수정
[client]
default-character-set=utf8

[mysql]
default-character-set=utf8


[mysqld]
collation-server = utf8_unicode_ci
init-connect='SET NAMES utf8'
character-set-server = utf8
+
기존 테이블의 캐릭터셋도 마찬가지로 변경
ALTER DATABASE [DB명] DEFAULT CHARACTER SET utf8;
+
status로 characterset: utf8 확인
+
service mysql restart

5. MyBatis와 JSTL을 동시에 사용<br/>
