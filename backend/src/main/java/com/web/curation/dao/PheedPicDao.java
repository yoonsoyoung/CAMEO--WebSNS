package com.web.curation.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.web.curation.model.pheedpic.PheedPic;

@Mapper
public interface PheedPicDao {
	public int insertPic(List<PheedPic> picList) throws SQLException;
	
	public int deletePic(int pheedId) throws SQLException;
	
	public List<PheedPic> selectPicList(int pheedId) throws SQLException;
	
	public int selectPicTotalCount(int pheedId) throws SQLException;
	
	public int undeletePic(List<Integer> PheedIds) throws SQLException;
	
	// 피드 수정하면서 어떤 사진은 지울 때 쓸 거
	//public int deleteSomePic(int picId) throws SQLException;
	
	public PheedPic selectAPic(int pheedPicId) throws SQLException;

}
