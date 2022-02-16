package com.example.jdbc.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

public class StudentRSforGroup implements ResultSetExtractor<Map<String, List<String>>> {

	Map<String, List<String>> sMap = new HashMap<>();

	@Override
	public Map<String, List<String>> extractData(ResultSet rs) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub

		while (rs.next()) {
//			System.out.println(rs.getString("studentName"));
			String address = rs.getString("studentAddress");
			boolean isAddressAlreadyAvailable = checkIsAdressAlreadyAvailable(address);
			if (isAddressAlreadyAvailable == true) {
				List<String> sList = sMap.get(address);
				sList.add(rs.getString("studentName"));
			} else {
				List<String> sNameList = new ArrayList<>();
				sNameList.add(rs.getString("studentName"));
				sMap.put(address, sNameList);
			}

		}
		return sMap;
	}

	private boolean checkIsAdressAlreadyAvailable(String address) {

		boolean isCheckValue = false;
		for (String key : sMap.keySet()) {
			if (key.equalsIgnoreCase(address)) {
				isCheckValue = true;
			}
		}
		return isCheckValue;
	}

}
