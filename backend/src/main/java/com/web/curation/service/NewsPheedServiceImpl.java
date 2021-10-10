package com.web.curation.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.curation.dao.NewsPheedDao;
import com.web.curation.dao.PheedCommentDao;
import com.web.curation.dao.PheedPicDao;
import com.web.curation.dao.UserDao;
import com.web.curation.model.pheedpic.PheedPic;
import com.web.curation.model.pheed.Pheed;
import com.web.curation.model.pheedcomment.PheedComment;
import com.web.curation.utils.FileUtils;
@Service
public class NewsPheedServiceImpl implements NewsPheedService{

	@Autowired
	private NewsPheedDao dao;
	
	@Autowired
	private PheedPicDao picdao;
	
	@Autowired
	private PheedCommentDao codao;
	
	@Autowired
	private UserDao userdao;

	@Autowired
	private FileUtils fileUtils;
	
	@Override
	public int insertPheed(Pheed pheed) throws Exception {
		return dao.insertPheed(pheed);
	}

	@Override
	public List<Map<String, Object>> selectPheedList(String user_id) throws Exception {
		String following = user_id;
		List<Map<String, Object>> res = new ArrayList<>();
		
		List<Pheed> list = dao.selectPheedList(following);
		
		for (Pheed p : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("pheed", p);
			int pId = p.getPheed_id();
			List<PheedPic> pPic = getPicList(pId);
			List<String> pheedPicList = new ArrayList<>();
			for (PheedPic pp : pPic) {
				pheedPicList.add(pp.getSaveName());
			}
			String pheedWriterUserId = userdao.getUserIdByEmail(p.getPheed_user_id());
			map.put("pheedPicList", pheedPicList);
			int commentCnt = codao.selectCommentCount(pId);
			map.put("pheedWriterUserId", pheedWriterUserId);
			map.put("commentCnt", commentCnt);
			res.add(map);
		}
		return res;
	}

	@Override
	public int deletePheed(int pheed_id) throws Exception {
		int res = 0;
		int commentCnt = -1;
		int cnt = picdao.deletePic(pheed_id);
		if(cnt != 0) {
			commentCnt = codao.deleteComment(pheed_id);
			if(commentCnt != -1) {
				res = dao.deletePheed(pheed_id);
			}
		}
		return res;
	}

	@Override
	public int updatePheed(Pheed pheed, MultipartFile[] files) throws Exception {
		int res = 1;
		int tmp = updatePheed(pheed);
		if(tmp == 0) {
			return 0;
		}
		List<PheedPic> fileList = fileUtils.uploadFiles(files, pheed.getPheed_id());
		if(CollectionUtils.isEmpty(fileList) == false) {
			res = picdao.insertPic(fileList);
			if(res < 1) {
				res = 0;
			}
		}
		return res;
	}
	
	@Override
	public int updatePheed(Pheed pheed) throws Exception {
		return dao.updatePheed(pheed);
	}

	@Override
	public int insertPheed(Pheed pheed, MultipartFile[] files) throws Exception {
		int res = 1;
		int tmp = insertPheed(pheed); 
		if(tmp == 0) {
			return 0;
		}
		System.out.println(pheed.getPheed_id());
		List<PheedPic> fileList = fileUtils.uploadFiles(files, pheed.getPheed_id());
		if(CollectionUtils.isEmpty(fileList) == false) {
			res = picdao.insertPic(fileList);
			if(res < 1) {
				res = 0;
			}
		}
		return pheed.getPheed_id();
	}

	@Override
	public List<PheedPic> getPicList(int pheed_id) throws Exception {
		int picTotalCnt = picdao.selectPicTotalCount(pheed_id);
		if(picTotalCnt < 1)
			return Collections.emptyList();
		
		return picdao.selectPicList(pheed_id);
	}

	@Override
	public Pheed getJson(String Pheed) throws Exception {
		Pheed pheedJson = new Pheed();
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			pheedJson = objectMapper.readValue(Pheed, Pheed.class);
		}catch(IOException err){
			System.out.printf("Error", err.toString());
		}
		
		
		return pheedJson;
	}

	@Override
	public Map<String, Object> selectPheed(int pheed_id) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Pheed pheed = dao.selectPheed(pheed_id);
		map.put("pheed", pheed);
		List<PheedPic> list = picdao.selectPicList(pheed_id);
		List<String> picList = new ArrayList<>();
		for (PheedPic pp : list) {
			picList.add(pp.getSaveName());
		}
		String pheedWriterUserId = userdao.getUserIdByEmail(pheed.getPheed_user_id());
	
		List<PheedComment> pComment = codao.getAllComment(pheed_id);
		List<Map<String, Object>> pheedCommentList = new ArrayList<>();

		for (PheedComment pc : pComment) {
			Map<String, Object> tmp = new HashMap<>();
			String commentWriterUserId = userdao.getUserIdByEmail(pc.getUserEmail());
			tmp.put("commentWriterUserId", commentWriterUserId);
			tmp.put("pheedComment", pc);
			
			pheedCommentList.add(tmp);
		}
		
		map.put("picList", picList);
		map.put("pheedCommentCnt", codao.selectCommentCount(pheed_id));
		map.put("pheedCommentList", pheedCommentList);
		return map;
	}

	@Override
	public int deletePic(int pheedId) throws Exception {
		return picdao.deletePic(pheedId);
	}

	@Override
	public PheedPic selectAPic(int pheedPicId) throws Exception {
		return picdao.selectAPic(pheedPicId);
	}

	@Override
	public List<Map<String, Object>> selectAllPheed() throws Exception {
		List<Pheed> list = dao.selectAllPheed();
		List<Map<String, Object>> res = new ArrayList<>();
		for (Pheed p : list) {
			Map<String,Object> elements = new HashMap<>();
			
			elements.put("pheed", p);
			List<PheedPic> pheedPic = picdao.selectPicList(p.getPheed_id());
			List<String> picList = new ArrayList<>();
			int pCnt = 0;
			
//			List<PheedComment> pComment = codao.getAllComment(p.getPheed_id());
//			for (PheedComment pc : pComment) {
//				Map<String, Object> map = new HashMap<>();
//				String commentWriterUserId = userdao.getUserIdByEmail(pc.getUserEmail());
//				map.put("pComment", pc);
//				System.out.println(pc);
//				map.put("commentWriterUserId", commentWriterUserId);
//				System.out.println(commentWriterUserId);
//				elements.put("comment", map);
//			}
			
			String pheedWriterUserId = p.getPheed_user_id();
			elements.put("pheedWriterUserId", userdao.getUserIdByEmail(pheedWriterUserId));
			
			pCnt = codao.selectCommentCount(p.getPheed_id());
			elements.put("commentCnt", pCnt);

			for (PheedPic pp : pheedPic) {
				String s = pp.getSaveName();
				picList.add(s);
			}
			elements.put("pheedPicList", picList);
			
			res.add(elements);
		}
		return res;
	}

	@Override
	public int insertComment(PheedComment pheedComment) throws Exception {
		return codao.insertComment(pheedComment);
	}

	@Override
	public int updateComment(PheedComment pheedComment) throws Exception {
		return codao.updateComment(pheedComment);
	}

	@Override
	public int deleteComment(int pheedCommentId) throws Exception {
		return codao.deleteComment(pheedCommentId);
	}

	@Override
	public List<Map<String, Object>> selectAllPheedByEmail(String userEmail) throws Exception {
		List<Pheed> list = dao.selectPheedByEmail(userEmail);
		List<Map<String, Object>> res = new ArrayList<>();
		for (Pheed p : list) {
			Map<String,Object> elements = new HashMap<>();
			
			elements.put("pheed", p);
			List<PheedPic> pheedPic = picdao.selectPicList(p.getPheed_id());
			List<String> picList = new ArrayList<>();
			int pCnt = 0;
			
			String pheedWriterUserId = p.getPheed_user_id();
			elements.put("pheedWriterUserId", userdao.getUserIdByEmail(pheedWriterUserId));
			
			pCnt = codao.selectCommentCount(p.getPheed_id());
			elements.put("commentCnt", pCnt);

			for (PheedPic pp : pheedPic) {
				String s = pp.getSaveName();
				picList.add(s);
			}
			elements.put("pheedPicList", picList);
			
			res.add(elements);
		}
		return res;
	}
}
