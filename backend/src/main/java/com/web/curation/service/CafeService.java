package com.web.curation.service;

import java.sql.SQLException;
import java.util.List;

import com.web.curation.model.cafe.Cafe;

public interface CafeService {
	public List<Cafe> findCafe(String cafe_name) throws Exception;

	public List<Cafe> getAllCafe() throws Exception;

	public List<Cafe> findCafePlace(String place) throws Exception;
}
