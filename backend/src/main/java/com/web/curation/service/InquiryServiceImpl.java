package com.web.curation.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.InquiryDao;
import com.web.curation.model.inquiry.Inquiry;

@Service
public class InquiryServiceImpl implements InquiryService {

	@Autowired
	private InquiryDao dao;

	// 문의사항 생성
	@Override
	public int insertInquiry(Map<String, String> map) throws SQLException {
		return dao.insertInquiry(map);
	}

	// 문의사항 조회
	@Override
	public Inquiry getInquiry(int inquiry_id) throws SQLException {
		return dao.getInquiry(inquiry_id);
	}

	// 문의사항 리스트 조회
	@Override
	public List<Inquiry> selectInquiryList() throws SQLException {
		return dao.selectInquiryList();
	}

	// 문의사항 수정
	@Override
	public int updateInquiry(Map<String, String> map) throws SQLException {
		return dao.updateInquiry(map);
	}

	// 문의사항 삭제
	@Override
	public int deleteInquiry(int inquiry_id) throws SQLException {
		return dao.deleteInquiry(inquiry_id);
	}

}
