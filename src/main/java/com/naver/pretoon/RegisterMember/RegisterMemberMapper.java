package com.naver.pretoon.RegisterMember;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RegisterMemberMapper {
	public List<RegisterMemberVO> selectAll(@Param("webtoon_name")String webtoon_name) throws Exception;
	public void insert(@Param("webtoon_name")String webtoon_name,
			@Param("person_name")String person_name,
			@Param("person_description")String person_description,
			@Param("vote")Integer vote,
			@Param("person_image")String personImage) throws Exception;
	public void vote(@Param("webtoon_name")String webtoon, 
			@Param("person_name")String voted);

}
