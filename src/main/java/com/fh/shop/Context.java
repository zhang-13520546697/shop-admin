package com.fh.shop;

/**
 * <pre>包名称：com.fh.shop
 * 类名称：Context
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/7/2820:45
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public  class Context  implements Runnable {


   public long count = 0;

    @Override
    public void run() {
        add();
    }

    private void  add(){
        count++;

    }

}
