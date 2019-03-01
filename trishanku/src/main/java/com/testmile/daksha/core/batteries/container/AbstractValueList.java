package com.testmile.daksha.core.batteries.container;

import java.util.ArrayList;
import java.util.List;

import com.testmile.daksha.tpi.batteries.container.Value;

public class AbstractValueList implements RWValueList{
	
	private List<Value> values = null;
	
	public AbstractValueList() {
		this.values = new ArrayList<Value>();
	}

	@Override
	public List<Value> values() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Value> values(List<Integer> indices) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> strings() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> strings(List<Integer> indices) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value value(int index) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String string(int index) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAll(List<Value> map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAll(ValueList map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Value value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addObject(Object o) {
		// TODO Auto-generated method stub
		
	}

}
