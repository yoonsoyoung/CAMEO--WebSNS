package com.web.curation.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.good.Good;

@Mapper
public interface GoodDao {
	
	// 내가(my_id) 좋아요를 누를 (user_id) , pheed_id 삽입 받아와서
	public int insertGood(@Valid Map<String, Object> map) throws SQLException;
	
	// 내가 좋아요를 누른 목록 전체 보여주기 
	List<Good> getGoodReqList(String good_my_email) throws SQLException;
	
	// 내가 좋아요른 사람 삭제하기
	int deleteGood(int good_id) throws SQLException;
	
	int ida(Map<String,String> map)throws SQLException;
	
	// pheed id를 받으면 id와 관련된 좋아요 count
	public int countGood(String good_pheed_id) throws SQLException;
	
	public String selectPheedtitle(int cnt) throws SQLException;

}
