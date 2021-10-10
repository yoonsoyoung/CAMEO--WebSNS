package com.web.curation.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.web.curation.model.inquiry.Inquiry;

public interface InquiryService {

	// 문의사항 생성
	int insertInquiry(Map<String, String> map) throws SQLException;

	// 문의사항 조회
	Inquiry getInquiry(int inquiry_id) throws SQLException;

	// 문의사항 리스트 조회
	List<Inquiry> selectInquiryList() throws SQLException;

	// 문의사항 수정
	int updateInquiry(Map<String, String> map) throws SQLException;

	// 문의사항 삭제
	int deleteInquiry(int inquiry_id) throws SQLException;

}
