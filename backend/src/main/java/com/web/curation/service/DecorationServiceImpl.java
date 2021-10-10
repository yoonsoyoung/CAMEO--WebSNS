package com.web.curation.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.curation.dao.DecorationDao;
import com.web.curation.model.decoration.Decoration;
import com.web.curation.utils.FileUtilsDeco;

@Service
public class DecorationServiceImpl implements DecorationService {
	
	@Autowired
	private DecorationDao dao;
	
	@Autowired
	private FileUtilsDeco fileUtilsDeco;

	@Override
	public Decoration getJson(String Decoration) throws Exception {
		Decoration decorationJson = new Decoration();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			decorationJson = objectMapper.readValue(Decoration, Decoration.class);
		} catch (IOException err) {
			System.out.printf("Error", err.toString());
		}
		
		return decorationJson;
	}

	@Override
	public int insertDecoration(MultipartFile[] files) throws Exception {
		int res = 0;
		System.out.println(1);
//		for(MultipartFile fileLists : files) {
//			System.out.println(fileLists);
//			
//		}
		List<Decoration> fileList = fileUtilsDeco.uploadFiles(files);
		for(Decoration d : fileList) {
			System.out.println(d);
		}

		
		if(CollectionUtils.isEmpty(fileList) == false) {
			System.out.println("여기안나옴" + 4);
			res = dao.insertPic(fileList);
			
		}
		return res;
	}

	@Override
	public int selectScore(String user_id) throws Exception {
		
		return dao.selectScore(user_id);
	}

	@Override
	public Decoration selectPic(int user_total_score) throws Exception {
		// 가지고온 score 점수를 통해서 
		// 점수 범위 따라 다른 이미지를 출력
		int res = user_total_score;
//		System.out.println("여기는 서비스" + res);
		int cnt = 0;
		
		if(res >= 0 & res < 30) {
			user_total_score =1;
//			System.out.println("여기는cnt" + user_total_score);
			return dao.selectPic(user_total_score);
		}else if(res >= 30 & res <60) {
			user_total_score =2;
//			System.out.println("여기는cnt:" + user_total_score);
			return dao.selectPic(user_total_score);
		}else if(res >= 60 & res <90) {
			user_total_score =3;
			return dao.selectPic(user_total_score);
		}else if(res >= 90 & res <120) {
			
			user_total_score=4;
			return dao.selectPic(user_total_score);
		}else  {
			user_total_score=5;
			return dao.selectPic(user_total_score);
		}
	}

	

	

}
