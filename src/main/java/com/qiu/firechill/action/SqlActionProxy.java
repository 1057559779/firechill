package com.qiu.firechill.action;

import com.qiu.firechill.ann.*;
import com.qiu.firechill.common.sql.SqlProxyDo;
import com.qiu.firechill.common.sql.impl.SqlProxyDoImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author qiu
 * @create 2020/9/28 9:50
 * @Des 代理类
 */
public class SqlActionProxy<T> implements InvocationHandler {

    private Class<T> proxyInterface;

    public SqlActionProxy(Class<T> proxyInterface){
        this.proxyInterface = proxyInterface;
    }

    private static SqlProxyDo sqlProxyDo = new SqlProxyDoImpl();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {

        //获得参数上的注解
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        //参数注解里的值的数组
        String[] params = new String[parameterAnnotations.length];
        //获取参数注解中的值
        for (int i = 0; i <parameterAnnotations.length; i++) {
            String param="";
            for (int j = 0; j <parameterAnnotations[i].length ; j++) {
                SqlParam  sqlParam =  ((SqlParam)parameterAnnotations[i][j]);
                param= sqlParam.index();
            }
            params[i]=param;
        }
        Object o=null;
        //获得方法上头的所有注解
        Annotation[] allann = method.getDeclaredAnnotations();
        for (Annotation annotation:allann) {
            //h获得注解的名字
            String name = annotation.annotationType().getSimpleName();
            switch (name){
                case "Select":
                    //返回类型
                    Class<?> returnType = method.getReturnType();
                    //返回类型的名字
                    String islist = returnType.getSimpleName();
                    //得到注解
                    Select select = (Select)annotation;
                    //sql语句
                    String selectSql =select.sql();
                    //返回类型
                    Class<?> result = select.result();
                    //调用查询方法
                    o = sqlProxyDo.doSelectSql(selectSql,result,islist, params,args);
                    break;
                case "Update":
                    Update update = (Update)annotation;
                    String updateSql = update.sql();
                    o = sqlProxyDo.doChangeSql(updateSql, params, args);
                    break;
                case "Delete":
                    Delete delete = (Delete)annotation;
                    String deletesql=delete.sql();
                    o = sqlProxyDo.doChangeSql(deletesql, params, args);
                    break;
                case "Insert":
                    Insert insert=(Insert) annotation;
                    String insertsql = insert.sql();
                    o = sqlProxyDo.doChangeSql(insertsql, params, args);
                    break;
                default:
                    System.out.println("无法识别sql");;
            }
        }
        return o;

    }

    public T getProxy(){
        return (T) Proxy.newProxyInstance(proxyInterface.getClassLoader(),new Class[]{proxyInterface},this);
    }

}
/**
 *        //自定义mapper
 *         TestMapper interFace = action.getInterFace(TestMapper.class);
 *
 *         List<QiuUser> all = interFace.findAll();
 */
