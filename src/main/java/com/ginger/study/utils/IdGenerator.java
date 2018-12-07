package com.ginger.study.utils;

import java.util.UUID;

public class IdGenerator {

	public static String generateId() {
		return "XX" + UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
	}


}
