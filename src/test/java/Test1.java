import com.fh.shop.Context;

/**
 * <pre>包名称：PACKAGE_NAME
 * 类名称：Test1
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/7/2820:47
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class Test1 {


    public static void main(String[] args) {
        Context context = new Context();

        Thread t1 = new Thread(context);

        t1.start();

        try {
            t1.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(context.count);


    }


}
