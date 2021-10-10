package com.web.curation.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.web.curation.model.pheedpic.PheedPic;
import com.web.curation.model.pheed.Pheed;
import com.web.curation.model.pheedcomment.PheedComment;

public interface NewsPheedService {
	
	Pheed getJson(String Pheed) throws Exception;

	int insertPheed(Pheed pheed) throws Exception;
	
	int insertPheed(Pheed pheed, MultipartFile[] files) throws Exception;

	List<Map<String, Object>> selectPheedList(String user_id) throws Exception;

	int deletePheed(int pheed_id) throws Exception;

	int updatePheed(Pheed pheed, MultipartFile[] files) throws Exception;
	
	int updatePheed(Pheed pheed) throws Exception;
	
	List<PheedPic> getPicList(int pheed_id) throws Exception;
	
	Map<String, Object> selectPheed(int pheed_id) throws Exception;
	
	public int deletePic(int pheedId) throws Exception;
	
	public PheedPic selectAPic(int pheedPicId) throws Exception;
	
	public List<Map<String, Object>> selectAllPheed() throws Exception;
	
	public int insertComment(PheedComment pheedComment) throws Exception;
	
	public int updateComment(PheedComment pheedComment) throws Exception;
	
	public int deleteComment(int pheedCommentId) throws Exception;
	
	public List<Map<String, Object>> selectAllPheedByEmail(String userEmail) throws Exception;
}
