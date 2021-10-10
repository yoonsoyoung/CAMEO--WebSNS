package com.web.curation.service;

import java.sql.SQLException;
import java.util.Map;




public interface HelpSelectService {
	
	int updateHelpPlus(Map<String,String> map)throws SQLException;
	
	int updateHelpMinus(Map<String,String> map)throws SQLException;
	

}
