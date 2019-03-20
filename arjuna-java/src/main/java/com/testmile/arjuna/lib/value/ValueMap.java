package com.testmile.arjuna.lib.value;

import java.util.List;
import java.util.Map;

import com.testmile.arjuna.tpi.value.Value;

public interface ValueMap<T> {

	Map<T, Value> items() throws Exception;

	Map<T, Value> items(List<T> filterKeys) throws Exception;

	Map<String, String> strItems() throws Exception;

	Map<String, String> strItems(List<T> filterKeys) throws Exception;

	Value value(T key) throws Exception;

	String string(T key) throws Exception;

	boolean hasKey(T key) throws Exception;

}