package com.web.curation.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.NoticeDao;
import com.web.curation.model.notice.Notice;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDao dao;

	@Override
	public int insertNotice(Notice notice) throws Exception {
		// 여기서 dao 잘돌아가는지 확인
		// 잘들어가면 1이들어가고
		// 넵좋아요
		String str = notice.getNotice_title();
		int res = dao.insertNotice(notice);

		// notice_id를 받아와야하는데 방금전에 넣었던 notice_id를받아와야한다
		if (res > 0) {

			return dao.getNotice_id(str); // ex) 1
		} else {
			return 0;
		}
	}

	@Override
	public Notice getNotice(int notice_id) throws Exception {
		return dao.getNotice(notice_id);
	}

	@Override
	public List<Notice> getNoticeList() throws Exception {
		return dao.getNoticeList();
	}

	@Override
	public int updateNotice(Map<String, String> map) throws Exception {
		return dao.updateNotice(map);
	}

	@Override
	public int deleteNotice(int notice_id) throws Exception {
		return dao.deleteNotice(notice_id);
	}

	@Override
	public int getNotice_id(String title) throws Exception {

		return dao.getNotice_id(title);
	}

}
