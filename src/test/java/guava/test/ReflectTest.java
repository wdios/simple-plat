package guava.test;


/**
 * guava 反射测试
 */
public class ReflectTest {
	
    public static void main(String[] args) throws Exception {
    	/*SysUser user = new SysUser(1l, 1, "3line", "4t", "5t", "8t",
    			false, false, "10t", "11t",
    			"12t", "13t", "14t",
    			"15t");
    	SqlHelper aSqlHelper = new SqlHelper(user, "sys_user");*/
    }
    
    private void ttt() throws Exception {
    	/*SysUser user = new SysUser(1l, 1, "3line", "4t", "5t", "8t",
    			false, false, "10t", "11t",
    			"12t", "13t", "14t",
    			"15t");
    	
    	Field[] fields = user.getClass().getDeclaredFields();

        // 写数据
        for (Field ff : fields) {
            String name = ff.getName();    // 获取属性的名字
            // System.out.println(name);
            String type = ff.getGenericType().toString();    // 获取属性的类型
            // System.out.println(type);
            ff.setAccessible(true);

            PropertyDescriptor pd = new PropertyDescriptor(ff.getName(), user.getClass());
            Method rM = pd.getReadMethod();     // 获得读方法
            Object getObject = (Object) rM.invoke(user);
            System.out.println(getObject);

            if (num == null) {
                Method wM = pd.getWriteMethod();    // 获得写方法
                if (type.equals("class java.lang.String")) {
                	
                } else if (type.equals("int")) {
                	
                } else if (type.equals("long")) {
                	
                } else if (type.equals("class java.lang.Integer")) {
                	
                } else if (type.equals("class java.lang.Short")) {
	
	            } else if (type.equals("class java.lang.Double")) {
	
	            } else if (type.equals("class java.lang.Boolean")) {
	
	            } else if (type.equals("class java.util.Date")) {
	
	            }
	            // wM.invoke(bo, "1"); 
	        }
        }*/
    }

}

