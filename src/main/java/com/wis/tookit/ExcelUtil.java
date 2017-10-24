package com.wis.tookit;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel工具类
 */
public class ExcelUtil {
	
	/**
	 * 将 Excel 文件输出至 web 页面
	 * @param response
	 * @param fileName 文件名称
	 * @param list 原始数据
	 * @param cls 对象名称
	 * @param title 标题名称
	 * @param columns 输出字段
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <Q> void writeExcelToWeb(HttpServletResponse response, String fileName, 
    		List<Q> list, Class<Q> cls, String[] title, String[] columns) throws IOException, IllegalArgumentException, IllegalAccessException {
	}
	
	/**
	 * 将 Excel 文件输出至文件
	 */
	public static <Q> void writeExcelToFile(HttpServletResponse response, String fileName, 
    		List<Q> list, Class<Q> cls, String[] title, String[] columns) throws IOException, IllegalArgumentException, IllegalAccessException {
	}

}
