package com.happy.util;

import java.sql.ResultSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
/**
 * 导出excel
 * @author lengjinlin
 *
 */
public class ExcelUtil {
/**
 * 
 * @param rs
 * @param wb
 * @param headers
 * @throws Exception
 */
	public static void fillExcelData(ResultSet rs, Workbook wb, String[] headers)
			throws Exception {
		int rowIndex = 0;
		Sheet sheet = wb.createSheet();
		Row row = sheet.createRow(rowIndex++);
		for (int i = 0; i < headers.length; i++) {
			row.createCell(i).setCellValue(headers[i]);
		}
		while (rs.next()) {
			row = sheet.createRow(rowIndex++);
			for (int i = 0; i < headers.length; i++) {
				row.createCell(i).setCellValue(rs.getObject(i + 1).toString());
			}
		}
	}

	/**
	 * 在excel中写入数据
	 * 
	 * @param jsonArray
	 * @param wb
	 * @param headers
	 * @throws Exception
	 */
	public static void fillExcelData(JSONArray jsonArray, Workbook wb,
			String[] headers) throws Exception {
		int rowIndex = 0;
		Sheet sheet = wb.createSheet();
		Row row = sheet.createRow(rowIndex++);
		for (int i = 0; i < headers.length; i++) {
			row.createCell(i).setCellValue(headers[i]);
		}
		for (int i = 0; i < jsonArray.size(); i++) {
			row = sheet.createRow(i + 1);
			JSONObject obj = jsonArray.getJSONObject(i);
			Object[] objs = obj.values().toArray();
			for (int j = 0; j < objs.length; j++) {
				row.createCell(j).setCellValue(
						String.valueOf(objs[j] == null ? "" : objs[j]));
			}
		}

	}
}
