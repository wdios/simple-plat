package com.wis.tookit.sql;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import com.wis.model.user.User;

/**
 * sql 拼装类，根据 model 类来生成基本的查询，修改，新增的 sql 语句
 * @author wd
 * Date: 2017-01-16 18:12
 */
public class SqlHelper {
	
	private final static String REFUSE_COLUMN_NAME = "|id|stampCreated|stampUpdated|queryStartDate|queryEndDate|";

	private Object curObj;
	private String tableName;
	private ArrayList<Object> allParamsList;
	private Object[] allParams;
	private Object idObject;

	public SqlHelper() {
	}

	public SqlHelper(Object curObj, String tableName) {
		this.curObj = curObj;
		this.tableName = tableName;
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化程序
	 * @throws IntrospectionException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	private void init() {
		String packageStr = curObj.getClass().getPackage().toString();
		packageStr = packageStr.replace("package ", "");
		allParamsList = new ArrayList<Object>();
	}
	
	/**
	 * 获取查询语句，查询分页数据
	 * @return
	 */
	public String getQuerySQLPage(String conditionName, String likeParams, String updateTimeName, int page, int rows) {
		String querySql = "";
		String conditionSql = getConditionSql(conditionName, likeParams, updateTimeName);
		int limitNum = 0;
		if (page > 0) {
			limitNum = (page - 1) * rows;
		}
		querySql = "SELECT * FROM " + tableName + conditionSql + " LIMIT " + limitNum + "," + rows;
		return querySql;
	}
	
	/**
	 * 获取查询语句，查询所有数据
	 * @return
	 */
	public String getQuerySQLAll(String conditionName, String likeParams, String updateTimeName) {
		String querySql = "";
		String conditionSql = getConditionSql(conditionName, likeParams, updateTimeName);
		querySql = "SELECT * FROM " + tableName + conditionSql;
		return querySql;
	}
	
	/**
	 * 获取查询语句，根据 id 查询所有数据
	 * @param ids 1,2,3,4
	 * @return
	 */
	public String getQueryForIdsSQL(String ids) {
		return "SELECT * FROM " + tableName + " WHERE id in(" + ids + ")";
	}
	
	/** 获取查询语句，根据 id 查询一条数据
	 * @param id 1
	 * @return
	 */
	public String getQueryForIdSQL(String id) {
		return "SELECT * FROM " + tableName + " WHERE id=" + id;
	}
	
	/**
	 * 获取查询语句，根据实例化对象来判断是否加入查询条件
	 * @return
	 */
	public String getQuerySQLParams() {
		return "";
	}
	
	/**
	 * 获取参数，根据对象不是 null 的属性生成
	 * @return
	 */
	public Object[] getParams() {
		if (null != allParamsList && allParamsList.size() > 0) {
			allParams = new Object[allParamsList.size()];
			for (int i = 0; i < allParamsList.size(); i++) {
				allParams[i] = allParamsList.get(i);
				// System.out.println("params = " + allParamsList.get(i));
			}
		}
		return allParams;
	}
	
	/**
	 * 清理所有的参数
	 */
	private void clearParams() {
		if (null != allParamsList && allParamsList.size() > 0) {
			allParamsList.clear();
		}
	}
	
	/**
	 * 获取插入语句，根据是否为空判断要不要插入
	 * @return
	 */
	public String getInsertSQL() {
		String insertSql = "";
		String insertNames = "";
		String insertVals = "";
		clearParams();
		
		Field[] fields = curObj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field aField = fields[i];
			String classType = aField.getType().toString();
            int lastIndex = classType.lastIndexOf(".");
            classType = classType.substring(lastIndex + 1);
			
			if (!Modifier.isStatic(aField.getModifiers()) && 
					"|String|Date|Boolean|Integer|".indexOf("|" + classType + "|") > -1) {
	            String name = aField.getName();    // 获取属性的名字
	            aField.setAccessible(true);
	
	            try {
		            PropertyDescriptor pd = new PropertyDescriptor(aField.getName(), curObj.getClass());
		            Method rM = pd.getReadMethod();     // 获得读方法
		            Object getObject = (Object) rM.invoke(curObj);
		            if (null != getObject) {
		            	if ((REFUSE_COLUMN_NAME + "userUpdated|").indexOf("|" + name + "|") == -1) {
		            		insertNames += "," + name;
		            		insertVals += ",?";
		            		allParamsList.add(getObject);
		            	}
		            }
	            } catch (Exception e) {
					e.printStackTrace();
				}
			}
        }
		
		if (insertNames.length() > 0) {
			insertSql += "INSERT INTO " + tableName + "(" + insertNames.substring(1) + ") VALUES(" + insertVals.substring(1) + ")";
		}
		
		return insertSql;
	} 
	
	/**
	 * 获取更新语句，根据是否为空判断要不要更新
	 * @return
	 */
	public String getUpdateSQL() {
		String updateSql = "";
		clearParams();
		
		Field[] fields = curObj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field aField = fields[i];
			String classType = aField.getType().toString();
            int lastIndex = classType.lastIndexOf(".");
            classType = classType.substring(lastIndex + 1);
			
			if (!Modifier.isStatic(aField.getModifiers()) && 
					"|String|Date|Boolean|Integer|".indexOf("|" + classType + "|") > -1) {
	            String name = aField.getName();    // 获取属性的名字
	            aField.setAccessible(true);
	
	            try {
		            PropertyDescriptor pd = new PropertyDescriptor(aField.getName(), curObj.getClass());
		            Method rM = pd.getReadMethod();     // 获得读方法
		            Object getObject = (Object) rM.invoke(curObj);
		            if (null != getObject) {
		            	if ((REFUSE_COLUMN_NAME + "userCreated|").indexOf("|" + name + "|") == -1) {
		            		updateSql += "," + name + "=?";
		            		allParamsList.add(getObject);
		            	}
		            	if ("id".equals(name)) {
		            		idObject = getObject;
		            	}
		            }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        }
		
		if (updateSql.length() > 0) {
			updateSql = "UPDATE " + tableName + " SET " + updateSql.substring(1) + " WHERE id=?";
			allParamsList.add(idObject);
		}
		
		return updateSql;
	}
	
	/**
	 * 获取删除语句，根据是否为空判断要不要更新
	 * @return
	 */
	public String getDeleteSQL(String conditionName, String likeParams, String updateTimeName) {
		String deleteSql = "";
		clearParams();
		String conditionSql = getConditionSql(conditionName, likeParams, updateTimeName);
		deleteSql = "DELETE FROM " + tableName + conditionSql;
		return deleteSql;
	}
	
	/**
	 * 获取查询条件 sql 语句
	 * @param likeParams
	 * @param updateTimeName
	 * @return
	 */
	private String getConditionSql(String conditionName, String likeParams, String updateTimeName) {
		String conditionSql = "";
		Object updateTimeObj = null;
		
		Field[] fields = curObj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field aField = fields[i];
			String classType = aField.getType().toString();
            int lastIndex = classType.lastIndexOf(".");
            classType = classType.substring(lastIndex + 1);
			
			if (!Modifier.isStatic(aField.getModifiers()) && 
					"|String|Date|Boolean|Integer|".indexOf("|" + classType + "|") > -1) {
	            String name = aField.getName();    // 获取属性的名字
	            aField.setAccessible(true);
	
	            PropertyDescriptor pd;
				try {
					pd = new PropertyDescriptor(aField.getName(), curObj.getClass());
					Method rM = pd.getReadMethod();     // 获得读方法
		            Object getObject = (Object) rM.invoke(curObj);
		            if (null != getObject) {
		            	if (conditionName.indexOf("|" + name + "|") > -1) {
			            	if ((REFUSE_COLUMN_NAME + "userCreated|userUpdated|").indexOf("|" + name + "|") == -1) {
			            		if (likeParams.indexOf("|" + name + "|") > -1) {
			            			conditionSql += " AND " + name + " LIKE CONCAT('%', ?, '%')";
			            		} else {
			            			conditionSql += " AND " + name + "=?";
			            		}
			            		allParamsList.add(getObject);
			            	}
			            	if ("id".equals(name) && Integer.valueOf(getObject.toString()) != 0) {
			            		idObject = getObject;
			            		conditionSql += " AND " + name + "=?";
			            		allParamsList.add(getObject);
			            	} else if (updateTimeName.equals(name)) {
			            		updateTimeObj = getObject;
			            	} else if ("queryStartDate".equals(name)) {
			            		conditionSql += " AND DATE(stampUpdated) >= ?";
			            		allParamsList.add(updateTimeObj);
			            	} else if ("queryEndDate".equals(name)) {
			            		conditionSql += " AND DATE(stampUpdated) <= ?";
			            		allParamsList.add(updateTimeObj);
			            	}
		            	}
		            }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        }
		if (conditionSql.length() > 0) {
			conditionSql = " WHERE " + conditionSql.substring(5);
		} else {
			conditionSql = "";
		}
		return conditionSql;
	}
	
	public static void main(String[] args) {
		User aUser = new User();
		aUser.setUsername("wd");
		System.out.println(aUser.toString());
		
		SqlHelper aSqlHelper = new SqlHelper(aUser, "sys_user");
		aSqlHelper.init();
		
		System.out.println("---------- select sql pager -----------");
		System.out.println(aSqlHelper.getQuerySQLPage("", "", "", 0, 12));
		System.out.println(aSqlHelper.getParams());
		
		System.out.println("---------- select all sql -----------");
		System.out.println(aSqlHelper.getQuerySQLAll("|username|", "", ""));
		System.out.println(aSqlHelper.getParams());
		
		System.out.println("---------- select for ids sql -----------");
		System.out.println(aSqlHelper.getQueryForIdsSQL("1,2,3,4"));
		System.out.println(aSqlHelper.getParams());
		
		System.out.println("---------- select for id sql -----------");
		System.out.println(aSqlHelper.getQueryForIdSQL("1"));
		System.out.println(aSqlHelper.getParams());
		
		System.out.println("---------- insert sql -----------");
		System.out.println(aSqlHelper.getInsertSQL());
		System.out.println(aSqlHelper.getParams());
		
		System.out.println("---------- update sql -----------");
		aUser.setId(10l);
		System.out.println(aSqlHelper.getUpdateSQL());
		System.out.println(aSqlHelper.getParams());
		
		System.out.println("---------- delete sql -----------");
		aUser.setId(101l);
		System.out.println(aSqlHelper.getDeleteSQL("|id|", "", ""));
		System.out.println(aSqlHelper.getParams());
		
		System.out.println("---------- query sql -----------");
		
	}
	
}
