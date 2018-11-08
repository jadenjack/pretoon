package com.naver.pretoon.Member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
	public List<MemberVO> selectAll(@Param("webtoon_name")String webtoon_name) throws Exception;
	public List<VoteVO> selectVoteData(@Param("webtoon_name")String webtoon_name);
	
	public void insert(@Param("webtoon_name")String webtoon_name,
			@Param("person_name")String person_name,
			@Param("person_description")String person_description,
			@Param("vote")Integer vote,
			@Param("person_image")String personImage) throws Exception;
	public void vote(@Param("webtoon_name")String webtoon, 
			@Param("person_name")String voted);
	public Boolean voteCheck(@Param("vote_table")String vote_table,
			@Param("ip")String ip,
			@Param("webtoon_name")String webtoon_name);
	public void voteInsert(@Param("vote_table")String vote_table,
			@Param("ip")String ip,
			@Param("webtoon_name")String webtoon_name);
	
}
