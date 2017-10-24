package com.wis.tookit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.common.base.CaseFormat;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Generator {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static List<String> modelList = Lists.newArrayList();
	private static List<String> modelTypeList = Lists.newArrayList();
	private static List<String> columnsCommentList = Lists.newArrayList();
	private static List<String> sourceColumns = Lists.newArrayList();
	private static String pgName = "com.wis";
	private static String version = "1.0";
	private static String tableName = "";
	private static String modelTableName = "";
	private static String tableNameComment = "";
	private static String sourceTableName = "";
	private static String className = "";
	private static String modelName = "";
	private static String viewName = "";
	private static String columns = "";
	
	private static String idName = "";
	private static String idValue = "";
	
	private static String copyrightContent = "";
	private static String classNoteContent = "";
	
	public static final String MODEL_FILE_PATH =  "bin/templet/";
	
	private String convertName(String sName, boolean isFirst) {
		List<String> tempName = Splitter.on('_').splitToList(sName);
		String rName = tempName.get(0);
		if (isFirst) {
			rName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tempName.get(0));
		}
		for (int i = 1; i < tempName.size(); i++) {
			rName += CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tempName.get(i));
		}
		return rName;
	}
	
	private void init() {
		// modelList = Files.readLines(new File(MODEL_FILE_PATH + "model.txt"), Charsets.UTF_8);
		tableName = "sys_user";
		viewName = "系统用户";
		String sql = "desc " + tableName;
		modelName = convertName(tableName, false);
		className = convertName(tableName, true);
		List<Map<String,Object>> rstList = jdbcTemplate.queryForList(sql);
		for (int i = 0; i < rstList.size(); i++) {
			Map<String,Object> aMap = rstList.get(i);
			for (Map.Entry<String,Object> m : aMap.entrySet()) {
				if ("Field".equals(m.getKey())) {
					// System.out.println(m.getKey()+"---"+m.getValue());
					modelList.add(m.getValue().toString());
					sourceColumns.add(m.getValue().toString());
				} else if ("Type".equals(m.getKey())) {
					String typeStr = m.getValue().toString();
					if (typeStr.indexOf("bigint") > -1) {
						modelTypeList.add("long");
					} else if (typeStr.indexOf("int") > -1) {
						modelTypeList.add("int");
					} else {
						modelTypeList.add("String");
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Generator generatorer = new Generator();
		generatorer.init();
		
		generatorer.generateCopyright();
		generatorer.generateClassNote();

		// generateMapper();
		generatorer.generateModelClass();
		// generatePersistence();
		
		// generateService();
		// generateServiceImpl();
		generatorer.generateController();
		
		System.out.println("generator end");
	}
	
	/**
     * 生成 controller 对象类
     * @throws IOException
     * @throws TemplateException
     */
	private void generateController() throws IOException, TemplateException {
        generateCommonClass(null, MODEL_FILE_PATH + "controller.t", 
                "src/main/java/" + (pgName + ".controller.").replace(".", "/") + modelName + "Controller.java");
        System.out.println("generate controller is OK!");
    }
	
	/**
     * 生成 service 对象类
     * @throws IOException
     * @throws TemplateException
     */
	private void generateService() throws IOException, TemplateException {
        generateCommonClass(null, MODEL_FILE_PATH + "service.t", 
                "src/main/java/" + (pgName + ".service.").replace(".", "/") + modelName + "Service.java");
        System.out.println("generate service is OK!");
    }
    
    /**
     * 生成 serviceImpl 对象类
     * @throws IOException
     * @throws TemplateException
     */
	private void generateServiceImpl() throws IOException, TemplateException {
        generateCommonClass(null, MODEL_FILE_PATH + "serviceImpl.t", 
                "src/main/java/" + (pgName + ".service.impl.Default").replace(".", "/") + modelName + "Service.java");
        System.out.println("generate service is OK!");
    }
	
	/**
	 * 生成 Persistence 对象类
	 * @throws IOException
	 * @throws TemplateException
	 */
	private void generatePersistence() throws IOException, TemplateException {
		generateCommonClass(null, MODEL_FILE_PATH + "persistence.t", 
				"src/main/java/" + (pgName + ".dao.").replace(".", "/") + modelName + "DAO.java");
		System.out.println("generate persistence is OK!");
	}
	
	/**
	 * 生成 通用 对象类
	 * @throws IOException
	 * @throws TemplateException
	 */
	private void generateCommonClass(Map<String, Object> extendMap, String modelFileDir, String srcFileDir) throws IOException, TemplateException {
		String content = Files.toString(new File(modelFileDir), Charsets.UTF_8);
        Map<String, Object> map = Maps.newHashMap();
        if (null != extendMap && extendMap.size() > 0) {
        	map.putAll(extendMap);
        }
        copyrightContent = Files.toString(new File(MODEL_FILE_PATH + "java_copyright.temp"), Charsets.UTF_8);
        map.put("copyRight", copyrightContent);
        map.put("pgName", pgName);
        map.put("classNote", classNoteContent);
        map.put("className", className);
        map.put("modelName", modelName);
        map.put("tableName", tableName);
        
		Files.createParentDirs(new File(srcFileDir));
		
		FileOutputStream out = new FileOutputStream(srcFileDir);
		Template t = new Template(null, content, null);
		t.process(map, new OutputStreamWriter(out, Charsets.UTF_8));
	}
	
	/**
	 * 生成显示 jsp
	 * @throws IOException
	 * @throws TemplateException
	 */
	private void generateViewJsp() throws IOException, TemplateException {
		String content = Files.toString(new File(MODEL_FILE_PATH + "view.t"), Charsets.UTF_8);
        Map<String, Object> map = Maps.newHashMap();
        copyrightContent = Files.toString(new File(MODEL_FILE_PATH + "java_copyright.temp"), Charsets.UTF_8);
        
        String listStr = "";
        String editStr = "";
        for (int i = 0; i < modelList.size(); i++) {
        	String aName = modelList.get(i);
        	if (!aName.equals("id")) {
        		listStr += "                <th data-options=\"field:'" + aName + "',width:128\"></th>\r\n";
        		editStr += "            <div class=\"fitem\">"
        				+ "                <label>：</label>"
        				+ "                <input id=\"username\" name=\"username\" type=\"text\" class=\"easyui-textbox iw\" data-options=\"required:true\" missingMessage=\"请输入！\">"
        				+ "            </div>";
        	}
		}
        
        map.put("copyRight", copyrightContent);
        map.put("pgName", pgName);
        map.put("classNote", classNoteContent);
        map.put("className", className);
        map.put("modelName", modelName);
        map.put("tableName", tableName);
        map.put("viewName", viewName);
        
        map.put("listStr", listStr);
        
        String srcFileDir = "src/main/webapp/WEB-INF/views/admin/" + tableName + ".jsp";
		Files.createParentDirs(new File(srcFileDir));
		
		FileOutputStream out = new FileOutputStream(srcFileDir);
		Template t = new Template(null, content, null);
		t.process(map, new OutputStreamWriter(out, Charsets.UTF_8));
	}
	
	/**
	 * 生成版权信息
	 * @param fileDir
	 * @throws IOException
	 * @throws TemplateException
	 */
	private void generateCopyright() throws IOException, TemplateException {
		String content = Files.toString(new File(MODEL_FILE_PATH + "java_copyright.t"), Charsets.UTF_8);
        Map<String, Object> map = Maps.newHashMap();
        map.put("now", new Date());
        
		FileOutputStream out = new FileOutputStream(MODEL_FILE_PATH + "java_copyright.temp");
		Template t = new Template(null, content, null);
		t.process(map, new OutputStreamWriter(out, Charsets.UTF_8));
		
		copyrightContent = Files.toString(new File(MODEL_FILE_PATH + "java_copyright.temp"), Charsets.UTF_8);
		System.out.println("generate copyright is OK!");
	}
	
	/**
	 * 生成类注释
	 * @param fileDir
	 * @throws IOException
	 * @throws TemplateException
	 */
	private void generateClassNote() throws IOException, TemplateException {
		String content = Files.toString(new File(MODEL_FILE_PATH + "class_note.t"), Charsets.UTF_8);
        Map<String, Object> map = Maps.newHashMap();
        map.put("now", new Date());
        map.put("comment", tableNameComment);
        
		FileOutputStream out = new FileOutputStream(MODEL_FILE_PATH + "class_note.temp");
		Template t = new Template(null, content, null);
		t.process(map, new OutputStreamWriter(out, Charsets.UTF_8));
		
		classNoteContent = Files.toString(new File(MODEL_FILE_PATH + "class_note.temp"), Charsets.UTF_8);
		System.out.println("generate class note is OK!");
	}
	
	/**
	 * 生成 Model 对象类
	 * @throws IOException
	 * @throws TemplateException
	 */
	private void generateModelClass() throws IOException, TemplateException {
		String content = Files.toString(new File(MODEL_FILE_PATH + "model.t"), Charsets.UTF_8);
        Map<String, Object> map = Maps.newHashMap();
        map.put("copyRight", copyrightContent);
        map.put("pgName", pgName);
        map.put("classNote", classNoteContent);
        map.put("className", modelTableName);
        map.put("classColumns", "");
        map.put("hashCode", columns);
        
        String fileDir = "src/main/java/" + (pgName + ".model.").replace(".", "/") + modelTableName + ".java";
		Files.createParentDirs(new File(fileDir));
		
		String classColumns = "";
		String classConstructor = "    public " + modelTableName + "() {\r\n    }\r\n\r\n";
		String constructorFields = "";
		String constructorContents = "";
		
		String classGetterAndSetter = "";
		String equalsDetails = "";
		String toString = "";
		for (int i = 0; i < modelList.size(); i++) {
			classColumns += "    private " + modelTypeList.get(i) + " " + modelList.get(i) + ";\r\n";
			constructorFields += ", " + modelTypeList.get(i) + " " + modelList.get(i);
			constructorContents += "\r\n        this." + modelList.get(i) + " = " + modelList.get(i) + ";";
			String columnName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, modelList.get(i).substring(0, 1));
			columnName = columnName + modelList.get(i).substring(1);
			classGetterAndSetter += "\r\n    public " + modelTypeList.get(i) + " get" + columnName 
					+ "() {\r\n        return " + modelList.get(i) 
					+ ";\r\n    }\r\n\r\n    public void set" + columnName + "(" 
					+ modelTypeList.get(i) + " " + modelList.get(i) + ") {\r\n"
					+ "        this." + modelList.get(i) + " = " + modelList.get(i) + ";\r\n    }\r\n";
			equalsDetails += "\r\n                && Objects.equal(this." + modelList.get(i) + ", that." + modelList.get(i) + ")";
			toString += "\r\n                .add(\"" + columnName + "\", " + modelList.get(i) + ")";
		}
		
		classConstructor += "	public " + modelName + "(" + constructorFields.substring(2)
				+ ") {" + constructorContents + "\r\n    }";
		
		map.put("classColumns", classColumns);
		map.put("classConstructor", classConstructor);
		map.put("classGetterAndSetter", classGetterAndSetter);
		map.put("equalsDetails", equalsDetails.substring(21));
		map.put("toString", toString.substring(2));
		
		FileOutputStream out = new FileOutputStream(fileDir);
		Template t = new Template(null, content, null);
		t.process(map, new OutputStreamWriter(out, Charsets.UTF_8));
		System.out.println("generate model class is OK!");
	}
	
	/**
	 * 生成 Mapper
	 * @throws IOException
	 * @throws TemplateException
	 */
	private void generateMapper() throws IOException, TemplateException {
		if (modelList.size() > 1) {
			String templateContent = Files.toString(new File(MODEL_FILE_PATH + "mapper.t"), Charsets.UTF_8);
		
			String namespace = pgName + ".dao." + modelName + "DAO";
			
			String columnsInsertValue = "";
			String columnsUpdateValue = "";
			String idStr = "";
			
			String resultMap = "";
			String sourceColums = "";
			
			String whereColumsIfPage = "<where>\r\n";
			String whereColumsIf = "<where>\r\n";
			String insertColumsIf = "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >\r\n";
			String insertValuesIf = "<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >\r\n";
			String updateColumsIf = "<set>\r\n";
			boolean havID = false;
			
			for (int i = 0; i < modelList.size(); i++) {
				columnsInsertValue += ", #{" + modelList.get(i) + "}";
				columnsUpdateValue += ", " + sourceColumns.get(i) + "=#{" + modelList.get(i) + "}";
				sourceColums += ", " + sourceColumns.get(i);
				if (i == 0) {
					idName = sourceColumns.get(i);
					idValue = modelList.get(i);
					idStr = sourceColumns.get(i) + "=#{" + modelList.get(i) + "}";
					resultMap += "<id property='" + modelList.get(i) + "' column='" + sourceColumns.get(i) + "' />\r\n";
					if (!"id".equals(sourceColumns.get(i))) {
						whereColumsIf += "      <if test=\" "+modelList.get(i)+" != null \">\r\n"
								+ "        "+sourceColumns.get(i) + "=#{"+modelList.get(i)+"}\r\n"
							    + "      </if>\r\n";
						whereColumsIfPage += "      <if test=\" " + tableName + "." + modelList.get(i) + " != null \">\r\n"
								+ "        " + sourceColumns.get(i) + "=#{" + tableName + "." + modelList.get(i)+"}\r\n"
							    + "      </if>\r\n";
					}
					if ("id".equals(sourceColumns.get(i))) {
						havID = true;
					}
				} else {
					resultMap += "    <result property='" + modelList.get(i) + "' column='" + sourceColumns.get(i) + "' />\r\n";
					if ("stamp_created".equals(sourceColumns.get(i)) || "stamp_updated".equals(sourceColumns.get(i))) {
						//
					} else {
						if (havID) {
							whereColumsIf += "      <if test=\" "+modelList.get(i)+" != null and "+modelList.get(i)+" != '' \">\r\n"
								+ "         "+sourceColumns.get(i) + "=#{"+modelList.get(i)+"}\r\n"
							    + "      </if>\r\n";
							whereColumsIfPage += "      <if test=\" " + tableName + "." + modelList.get(i)+" != null and " + tableName + "." + modelList.get(i)+" != '' \">\r\n"
									+ "         " + sourceColumns.get(i) + "=#{" + tableName + "." + modelList.get(i)+"}\r\n"
								    + "      </if>\r\n";
							havID = false;
						} else {
							whereColumsIf += "      <if test=\" "+modelList.get(i)+" != null and "+modelList.get(i)+" != '' \">\r\n"
									+ "        AND "+sourceColumns.get(i) + "=#{"+modelList.get(i)+"}\r\n"
								    + "      </if>\r\n";
							whereColumsIfPage += "      <if test=\" " + tableName + "." + modelList.get(i)+" != null and " + tableName + "." + modelList.get(i)+" != '' \">\r\n"
									+ "        AND " + sourceColumns.get(i) + "=#{" + tableName + "." + modelList.get(i)+"}\r\n"
								    + "      </if>\r\n";
						}
						insertColumsIf += "      <if test=\" "+modelList.get(i)+" != null and "+modelList.get(i)+" != '' \">\r\n"
								+ "        " + sourceColumns.get(i) + ",\r\n"
								+ "      </if>\r\n";
						insertValuesIf += "      <if test=\" "+modelList.get(i)+" != null and "+modelList.get(i)+" != '' \">\r\n"
								+ "        #{" + modelList.get(i) + "},\r\n"
								+ "      </if>\r\n";
						updateColumsIf += "      <if test=\" "+modelList.get(i)+" != null and "+modelList.get(i)+" != '' \">\r\n"
								+ "        " + sourceColumns.get(i) + "=" + "#{" + modelList.get(i) + "},\r\n"
								+ "      </if>\r\n";
					}
				}
				
			}
			sourceColums = sourceColums.substring(2);
			
			Map<String, Object> map = Maps.newHashMap();
			map.put("tableName", sourceTableName);
			map.put("modelName", modelName);
			map.put("namespace", namespace);
			map.put("resultMap", resultMap);
			map.put("columns", sourceColums);
			
			map.put("whereColumsIfPage", whereColumsIfPage+"    </where>");
			map.put("whereColumsIf", whereColumsIf+"    </where>");
			map.put("insertColumsIf", insertColumsIf+"    </trim>");
			map.put("insertValuesIf", insertValuesIf+"    </trim>");
			map.put("updateColumsIf", updateColumsIf+"    </set>");
			map.put("columnsInsertValue", columnsInsertValue.substring(2));
			map.put("columnsUpdateValue", columnsUpdateValue.substring(2));
			map.put("id", idStr);
			
			String fileDir = "src/main/resources/mapper/" + modelName + "Mapper.xml";
			Files.createParentDirs(new File(fileDir));
			FileOutputStream out = new FileOutputStream(fileDir);
			Template t = new Template(null, templateContent, null);
			t.process(map, new OutputStreamWriter(out, Charsets.UTF_8));
			System.out.println("generate Mapper is OK!");
		}
	}
}
