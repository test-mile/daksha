package com.testmile.trishanku.tpi.value;

import java.util.ArrayList;
import java.util.List;

import com.testmile.daksha.core.value.AnyRefValue;

public class AbstractValueList implements RWValueList{
	
	private List<Value> values = null;
	
	public AbstractValueList() {
		this.values = new ArrayList<Value>();
	}

	@Override
	public List<Value> values() throws Exception {
		return this.values;
	}

	@Override
	public List<Value> values(List<Integer> indices) throws Exception {
		List<Value> outValues = new ArrayList<Value>();
		for (int i=0; i < this.values.size(); i++) {
			if (indices.contains(i)) {
				outValues.add(this.values.get(i));
			}
		}
		
		return outValues;
	}
	
	private List<String> convertToStringList(List<Value> inValues){
		List<String> outValues = new ArrayList<String>();
		for (Value value: inValues) {
			outValues.add(value.toString());
		}
		
		return outValues;		
	}

	@Override
	public List<String> strings() throws Exception {
		return this.convertToStringList(this.values);
	}

	@Override
	public List<String> strings(List<Integer> indices) throws Exception {
		List<Value> inValues = this.values(indices);
		return this.convertToStringList(inValues);
	}

	@Override
	public Value value(int index) throws Exception {
		return this.values.get(index);
	}

	@Override
	public String string(int index) throws Exception {
		return this.values.get(index).toString();
	}

	@Override
	public void addAll(List<Value> list) {
		this.values.addAll(list);
	}

	@Override
	public void addAll(ValueList list) throws Exception {
		this.addAll(list.values());
	}

	@Override
	public void add(Value value) {
		this.add(value);
	}

	@Override
	public void addObject(Object obj) {
		this.add(new AnyRefValue(obj));
	}

}
