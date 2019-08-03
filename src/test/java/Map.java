import java.util.HashMap;

/**
 * <pre>包名称：PACKAGE_NAME
 * 类名称：Map
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/1917:41
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class Map {

    /*public static void main(String[] args) {
        Map<String,String> result = new HashMap();
        result.put("userName","zhangsan");
        result.put("sex","男");
        result.put("age","10");
        StringBuffer sb = new StringBuffer();
        Iterator<Map.Entry<String, String>> iterator = result.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            String key = next.getKey();
            String value = next.getValue();

            //System.out.println(key+":"+value);
            sb.append(key).append("=").append(value).append(";");
        }
        System.out.println(sb.toString());
    }*/

  /*  public static void main(String[] args) {
        Map<String,Object> map = new HashMap();
        map.put("userName","zhangsan");
        map.put("photo",5845120);
        map.put("sex","男");
        StringBuffer sb = new StringBuffer();
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Object> info = iterator.next();
            String key = info.getKey();
            Object value = info.getValue();
            sb.append(key).append("=").append(value).append(";");
        }
        System.out.println(sb);
    }*/

    /* public static void main(String[] args) {
         //定义一个map集合 并赋值
         Map<String,String> map = new HashMap();
         map.put("姓名","张三");
         map.put("性别","男");
         map.put("年龄","15");
         map.put("在哪里","在你心里");
         //迭代器遍历map集合
         Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
         System.out.println(iterator);
         StringBuilder sb = new StringBuilder();
         //判断是否有下一个数据
         while (iterator.hasNext()){
             //获取下一条数据
             Map.Entry<String, String> next =  iterator.next();
             String key = next.getKey();//获取key值
             String value = next.getValue();//获取value

             sb.append(key).append(":").append(value).append(";");
         }
         System.out.println(sb);

     }*/
    /*public static void main(String[] args) {
        Map<String, Object> map = new HashMap();
        map.put("userName", "zhangsan");
        map.put("photo", 5845120);
        map.put("sex", "男");
        StringBuffer sb = new StringBuffer();
        System.out.println("第三种：通过Map.entrySet遍历key和value  尤其是容量大时");
        for (Map.Entry<String, Object> m : map.entrySet()) {
            //System.out.println("key:" + m.getKey() + " value:" + m.getValue());
            sb.append(m.getKey()).append(":").append(m.getValue()).append(";");
        }
        System.out.println(sb);
    }*/
}
