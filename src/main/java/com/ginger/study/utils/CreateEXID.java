package com.ginger.study.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CreateEXID {
//
//	public static String getRandomNumAndChacters(int length) {
//		Random random = new Random();
//		String str = null;
//		do {
//			str = new String();
//			for (int i = 0; i < length; i++) {
//				boolean b = random.nextBoolean();
//				if (b) {
//					int choice = random.nextBoolean() ? 65 : 97;
//					str += (char) (choice + random.nextInt(26));
//				} else {
//					str += String.valueOf(random.nextInt(10));
//				}
//			}
//		} while (validate(str));// 验证是否为字母和数字的组合
//		return str;
//	}

	public static String getRandomNumAndChacters(Integer length) {
	    String str = "";
	    Random random = new Random();
	    for (int i = 0; i < length; i++) {
	        boolean b = random.nextBoolean();
	        if (b) { // 字符串
	            // int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母
	            str += (char) (97 + random.nextInt(6));
	        } else { // 数字
	            str += String.valueOf(random.nextInt(10));
	        }
	    }
	    return str;
	}

	public static boolean validate(String str) {
		//Pattern pattern = Pattern.compile("^([0-9]+)|([A-Fa-f]+)$");
		Pattern pattern = Pattern.compile("^([0-9]+)|([a-f]+)$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	public String getExid() {

		String id = new String();
		id = getRandomNumAndChacters(32) + "\n";

		return id;
	}

	public static void main(String[] args) throws Exception {
		String FileName = "";

		CreateEXID data_lab = new CreateEXID();

		FileName = "/home/XXX/some/testCreateEXID.txt";


			File f = new File(FileName);//"D:\\test.txt"

			if (!f.exists()) {
				f.createNewFile();
			}

			try {

				String buffer = "";
				int j = 0;
				FileOutputStream out = new FileOutputStream(f, true);

				for (int i = 0; i < 15; i++) {

					buffer = data_lab.getExid();

					out.write(buffer.toString().getBytes());

					j++;
					if (j % 100000 == 0) {
						System.out.println(j);
					}

				}
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
