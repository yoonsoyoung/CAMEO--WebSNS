package com.web.curation.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.web.curation.model.decoration.Decoration;

public interface DecorationService {
	
	Decoration getJson(String Decoration) throws Exception;
	
	int insertDecoration( MultipartFile[] files) throws Exception;
	
	int selectScore(String user_id) throws Exception;
	
	public Decoration selectPic(int user_total_score) throws Exception;

}
