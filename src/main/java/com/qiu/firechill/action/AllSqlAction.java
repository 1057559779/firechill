package com.qiu.firechill.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author qiu
 * @create 2020/9/25 16:09
 * @Des sql处理类 总和
 */
public interface AllSqlAction<T> extends CudSqlAction<T>,DefaultSelfSqlAction,ReadSqlAction<T>{


}
