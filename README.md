# firechill

#### 介绍

firechill 中文译名:"凉火" 由qiubrobro团队开发的orm框架   
要说一群小屁孩为什么突然想搞框架研发了，初衷很简单：就是想发现更好的自己。大部分java研发程序员梦想就是研发出可以代替spring的框架，我们也不例外，但是路很漫长，firechill将是起点。

目前阶段已经完成了基于实体类的crud操作，以及动态代理面向接口编程的自定义sql

对比了如火如荼的mybatis-plus 发现它没有实体类联表的操作，所以咱们就弄了一个实体类联表的功能，详见第4项。

#### 软件架构

java原生jdbc的封装，目前阶段仅允许mysql驱动的接入，但是数据库连接池可以引入好多的了，比如alibaba的druid。
该orm允许面向实体类数据库操作，也可以自定义sql。
今后考虑加上代码的自动生成，考虑到不同项目不同的目录结构，过多的生成反而会出现一定的麻烦，所以考虑仅仅映射数据库字段生成实体类的属性。
刚开始是打算用javaassist来完成面向接口编程的，但是考虑到定义代码格式太麻烦了，所以换成了jdk的动态代理。

项目结构截图如下

![输入图片说明](https://images.gitee.com/uploads/images/2020/1010/112129_a752cfde_5118695.png "屏幕截图.png")

需要说明的是:框架测试环境将放在devtest包下，使用框架的实例就在这个包里面。

#### 使用教程
![输入图片说明](https://images.gitee.com/uploads/images/2020/1001/094300_a089fdbe_5118695.png "屏幕截图.png")

 devtest包下有这些子包，这些包的用途就不在赘述了，都是很常见的命名方式。
1. 首先配置数据库连接池。示例如下：
 
 
```
public class MyDataSourceConfig implements DataSourceInfoConfig {

    private static ConnectBean config;

    public ConnectBean config() throws Exception {
        if(config == null){
            Map<String ,Object> map = new HashMap<String, Object>();
            map.put("url","jdbc:mysql://xxx.xxx.xxx.xxx:pppp/firechildren?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true");
            map.put("username","root");
            map.put("password","123456");
            map.put("initialSize","5");
            map.put("maxActive","10");
            map.put("maxWait","3000");
            map.put("driverClassName","com.mysql.jdbc.Driver");
            DataSource ds = DruidDataSourceFactory.createDataSource(map);

            ConnectBean connectBean = new ConnectBean();
            //装入数据库连接池
            connectBean.setDataSource(ds);

            //扫描mapper文件  没用到忽视它
            connectBean.setMapperScan("com.qiu.firechill.devtest.mapper");

            config=connectBean;
            return config;
        }else {
            return config;
        }

    }

}

```
项目发起人因为很讨厌xml的配置方式，所以直接在java里面配置，不过今后整合spring的时候还会不可避免的用到xml吧？

不管什么数据库连接池，只要返回了DataSource这个类就ok了。所以上头说了，数据库连接池依赖什么的，目前可以引入大部分的了。
 

2.  实体类的注解，示例如下：

```
@TableName("qiu_user")
public class QiuUser {

    @ColumnName("id")
    private Integer id;

    @ColumnName("uname")
    private String uname;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }


    @Override
    public String toString() {
        return "QiuUser{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                '}';
    }
}
```
TableName 以及 ColumnName 这两个注解顾名思义，就是表名和字段名，该orm和大部分传统的orm框架一样，数据库操作将会围绕实体类。

3.  main方法测试，示例如下：


```
public class AfterDBCMain {

    public static void main(String[] args) throws Exception {

        SqlAction<QiuUser> action = new CommonDBConnectFactory().getAction(QiuUser.class);

        List<QiuUser> list = action.selectAll();
        System.out.println(list);

    }
}

```
结果如下截图：

![输入图片说明](https://images.gitee.com/uploads/images/2020/1001/095634_0450a44f_5118695.png "屏幕截图.png")

以上代码可以看到无需手写sql即可实现全部查询


方法命名如同用法。


3.  面向接口编程的查询方法 示例如下：

 **mapper层接口** 

```
@SqlMapper
public interface TestMapper {

    @Select(sql = "select * from qiu_user",result = QiuUser.class)
    List<QiuUser> findAll();

    @Select(sql = "select * from qiu_user where id=?",result = QiuUser.class)
    QiuUser findOne(@SqlParam(index = "1") Integer id);
}

```
SqlMapper这个注解代表该类允许被动态代理

Select注解就是自定义sql的，result就是数据返回的类型，虽然方法上已经有返回类型，但是因为技术太渣的原因感觉还是在注解里加个result会觉得反射方便点。

SqlParam注解就是定义参数存放在sql中哪个位置用的。今后会添加实体类作为参数的方式来应对各种复杂的业务。

 **main方法测试如下** 

```
public class AfterDBCMain {

    public static void main(String[] args) throws Exception {

        SqlAction<QiuUser> action = new CommonDBConnectFactory().getAction(QiuUser.class);
        //使用自带的方法
        TestMapper interFace = action.getInterFace(TestMapper.class);
        QiuUser one = interFace.findOne(1);
        System.out.println(one);

    }
}
```
结果截图如下：

![输入图片说明](https://images.gitee.com/uploads/images/2020/1001/100642_6987b777_5118695.png "屏幕截图.png")

那么删插改操作也是差不多的操作。

4.  联表操作，示例如下：

准备两个实体类，User和Role 目前暂定User和Role是一对一的关系

![输入图片说明](https://images.gitee.com/uploads/images/2020/1010/112623_27415668_5118695.png "屏幕截图.png")

User实体类代码如下：对比上面的单表QiuUser 该实体类多了一个 @OneToOne的注解 pkey就是当前实体类要关联的字段，skey就是被关联表的要被关联的字段。

```
@TableName("qiu_user")
public class User {

    @ColumnName("id")
    private Integer id;

    @ColumnName("uname")
    private String uname;

    @ColumnName("rid")
    private Integer rid;

    //关联子表
    @OneToOne(pkey = "rid",skey = "id")
    private Role role;
```
Role实体类代码如下：很普通，只需要主表定义注解了就可以了

```
@TableName("qiu_role")
public class Role {

    @ColumnName("id")
    private int id;

    @ColumnName("rname")
    private String rname;
```
main方法测试：

```
public class AfterDBCMain {

    public static void main(String[] args) throws Exception {

        SqlAction<User> action = new CommonDBConnectFactory().getAction(User.class);

        User user = action.selectById(1);
        System.out.println(user);
    }
}
```
结果如下：

![输入图片说明](https://images.gitee.com/uploads/images/2020/1010/112945_142fdf6a_5118695.png "屏幕截图.png")

