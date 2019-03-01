package com.testmile.daksha.core.batteries.container;

import java.util.List;

import com.testmile.daksha.tpi.batteries.container.Value;

public interface ValueList {

	List<Value> values() throws Exception;

	List<Value> values(List<Integer> indices) throws Exception;

	List<String> strings() throws Exception;

	List<String> strings(List<Integer> indices) throws Exception;

	Value value(int index) throws Exception;

	String string(int index) throws Exception;

}