package com.ginger.study.others;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ginger on 17-6-6.
 */
public class TestUri {
    private static Map<String,String> idTypeMap = new HashMap();
    static{
        idTypeMap.put("XX","XX");
        idTypeMap.put("X","XX");
        idTypeMap.put("XXX","XX");
    }
    public static void main(String[] args){
        String hdfsUri = "hdfs://XXX:9000/";
        String inputPath = "input/id.txt";
        String pathString = hdfsUri + inputPath;
        System.out.println(pathString);

        String test = "X";
        String test1 ="X";
        System.out.println(test1.length());
        System.out.println(validateIdNumber(hdfsUri));

        System.out.println(idTypeMap.get("89"));

        System.out.println("null".equals("null"));
    }

    private static boolean validateIdNumber(String idNumber){
        String regex = "^[a-z0-9]+$";
        return idNumber.matches(regex);
    }
}
