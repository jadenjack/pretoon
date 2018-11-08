package com.naver.pretoon.Util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonConvertor<T> {

	public String convert(List<T> list) {
		Type listType = new TypeToken<ArrayList<T>>(){}.getType();
		return new Gson().toJson(list, listType); 
	}
	
}
