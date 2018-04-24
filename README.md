# json-mysql
#一款json对象到mysql数据库的映射框架，可以有json对象生产sql语句,OccupySql、OcpSqlAndArgs、CompleteSql对象分别用于产生占位符、占位符及参数、拼接型的sql语句，DBCore以及DBJsonCore对象用于json对象到数据库的自动映射操作<br>
第一步，导入alllibs目录下的所有jar包到你的向项目中<br>
第二步，创建一个类，实现BManager接口,如下：<br>
public class DefaultDBManager implements DBManager{
       
        @Override
        public Connection getConnection() {
        //获取数据库连接
        }
        
        @Override
        public boolean closeConnection(Connection con) {
          //关闭数据库连接
        }
  
  }
  
  
  DBJsonCore对象用法示例，（最好为每个数据库定义为一个全局的变量）<br>
  public class DBJsonCoreSample {
	

    //第一步，创建数据库管理器对象DBManager,只要实现DBManager接口的对象都可以，DefaultDBManager为一个默认的实现类
    static DefaultDBManager manager=new DefaultDBManager();
   
    //或者也可以这样 static DBManager manager=new DefaultDBManager(dburl,username,password);

    //第二步，创建数据库核心操作对象
    static DBCore core=new DBCore(manager);

    //第三步，创建数据库占位符sql构造对象(可选)
    static OcpSqlAndArgs ocpSql=new OcpSqlAndArgs(core);

    //第三步，数据库json操作对象(可选)
    static DBJsonCore dbjsoncore=new DBJsonCore(core,ocpSql);


    public static void main(String[] args) {	

      try {
        //添加json对象到数据库表student中
        JSONObject addsql=new JSONObject();
        addsql.put("name", "赵飞");
        addsql.put("pass", "mypass");
        addsql.put("sex", "男");
        addsql.put("age", "15");
        int i=dbjsoncore.add("student", addsql);
        System.out.println(i);
        
        //删除表student中id=201509030203的记录
        JSONObject delsql=new JSONObject();
        delsql.put("id", "201509030203");
        i=dbjsoncore.delete("student", delsql);
        System.out.println(i);
        
        //修改表student中name='李子龙'的记录，修改age=20,pass=123
        JSONObject condition=new JSONObject();
        condition.put("name","李子龙");
        JSONObject newdata=new JSONObject();
        newdata.put("age",20);
        newdata.put("pass", "123");
        i=dbjsoncore.update("student", condition, newdata);
        System.out.println(i);


        //查询name='李子龙'所有记录
        JSONObject conditionsql=new JSONObject();
        conditionsql.put("name","李子龙");
        JSONArray array=dbjsoncore.selectList("student", conditionsql);
        System.out.println(array);

        //查询id='2015'的唯一的记录
        JSONObject idcondition=new JSONObject();
        idcondition.put("id","2015");
        JSONObject studentitem=dbjsoncore.selectOne("student", idcondition);
        System.out.println(studentitem);

      } catch (Exception e) {
        e.printStackTrace();
      }
	}
  
  <br>关于DBCore、OccupySql、OcpSqlAndArgs、CompleteSql等更多的用法详见使用示例https://github.com/chenfengluoye/json-mysql/tree/master/src/ckj/lib/test
