//package com.ginger.study.kg;
//
///**
//Jena提供了将RDF数据存入关系数据库的接口
//
// https://github.com/zhangguixu/jena-learning
// */
//
//import com.hp.hpl.jena.db.DBConnection;
//import com.hp.hpl.jena.db.IDBConnection;
//
//import com.hp.hpl.jena.ontology.OntModel;
//import com.hp.hpl.jena.ontology.OntModelSpec;
//import com.hp.hpl.jena.rdf.model.Model;
//import com.hp.hpl.jena.rdf.model.ModelFactory;
//import com.hp.hpl.jena.rdf.model.ModelMaker;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//import com.hp.hpl.jena.rdf.model.RDFNode;
//import com.hp.hpl.jena.rdf.model.Resource;
//import com.hp.hpl.jena.rdf.model.Statement;
//import com.hp.hpl.jena.rdf.model.StmtIterator;
//
//public class Mysql2Console {
//    private static final Logger log = LoggerFactory.getLogger(Mysql2Console.class);
//
//    public static final String strDriver = "com.mysql.jdbc.Driver"; // path of
//    public static final String strURL = "jdbc:mysql://XX.XXX.XXX.XXX:8011/KGDemo?useUnicode=true&characterEncoding=utf8"; // URL
//    public static final String strUser = "root"; // database user id
//    public static final String strPassWord = "XXX"; // database password
//    public static final String strDB = "MySQL"; // database type
//    public static final String PATH = "doc/rdf/academic.rdf"; // file path
//    public static final String OWL = "zoo.owl"; // file path
//    public static final String MODLE_NAME="MyOntology";
//    /**
//     * @param args
//     * @throws ClassNotFoundException
//     */
//    public static void main(String[] args) throws ClassNotFoundException {
//
//        // 加载数据库驱动类，需要处理异常
//        Class.forName(strDriver);
//        // 创建一个数据库连接
//        IDBConnection conn = getConnection(strURL, strUser, strPassWord,strDB);
//        if(conn!=null){
//            log.debug("-------mysql数据库连接成功~");
//        }
//        //持久化到数据库
//        //createModel(conn,MODLE_NAME,PATH);
//        OntModel model = getModelFromRDB(conn, MODLE_NAME);
//        log.debug(model+" :"+model.isEmpty());
//        getTriples(model);
//
//    }
//
//    //获取数据连接
//    public static DBConnection getConnection(String dbUrl, String dbUser,
//                                             String dbPwd, String dbName) {
//        return new DBConnection(dbUrl, dbUser, dbPwd, dbName);
//    }
//
//    //从数据读取OntModel
//    public static OntModel getModelFromRDB(IDBConnection con,String name){
//        ModelMaker maker = ModelFactory.createModelRDBMaker(con);
//        Model model = maker.getModel(name);//读取我们之前创建的MyOntology模型
//        OntModelSpec spec = new OntModelSpec(OntModelSpec.RDFS_MEM);//这里是指定的RDF格式
//        OntModel ontModel = ModelFactory.createOntologyModel(spec,model);
//        return ontModel;
//    }
//
//    //
//    public static void getTriples(OntModel model){
//        StmtIterator stmIter=model.listStatements();
//        while(stmIter.hasNext()){
//            Statement stmt = stmIter.nextStatement();
//            RDFNode object= stmt.getObject();
//            if(object instanceof Resource){//主要为了判定RDFNode是Resource还是Literal
////                log.debug(stmt.getSubject().getLocalName()+"--"
////                        +stmt.getPredicate().getLocalName()+"--"
////                        +object.asResource().getLocalName());
//            }else{
//
//                log.debug(stmt.getSubject().getLocalName()+"**"
//                        +stmt.getPredicate().getLocalName()+"**"
//                        +object.toString());
//
//            }
//
//        }
//
//
//    }
//
//}
//
//
