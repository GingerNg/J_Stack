package com.ginger.study.others;
import java.io.File;

/**
 * Created by ginger on 17-6-12.
 */
public class testXlsx {

    public static void main(String[] args) {
        System.out.println(getXslxPath());
    }


    private static boolean getXslxPath() {//
        // String path = PropertyConfig.getPropertyByKey(XXXX);
          String path = "/XX/data/XX";
          File xslxFolder = new File(path);
        return (xslxFolder.exists() && xslxFolder.isDirectory()) ? true : xslxFolder.mkdirs();
    }

}
