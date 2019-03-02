package com.testmile.trishanku.tpi.value;

import java.util.ArrayList;
import java.util.List;

import com.testmile.daksha.core.value.AnyRefValue;

public class AbstractValueList implements RWValueList{
	
	private List<Value> values = null;
	
	public AbstractValueList() {
		this.values = new ArrayList<Value>();
	}
	
	public AbstractValueList(Object[] values) {
		this();
		this.addAll(values);
	}

	public AbstractValueList(List<Object> objects) {
		this();
		this.addAll(values.toArray(new Object[] {}));
	}

	@Override
	public List<Value> values() throws Exception {
		return this.values;
	}

	protected int maxIndex() {
		return values.size() - 1;
	}
	
	private void validateIndex(int index) throws Exception {
		if (values.size() == 0){
			throw new EmptyValueListLookUpException(index);			
		} else {
			if (index > maxIndex()) {
				throw new ValueListLookUpException(index, maxIndex());
			}
		}
	}

	private void validateIndices(List<Integer> indices) throws Exception {
		for (int index: indices) {
			validateIndex(index);
		}
	}
	
	@Override
	public List<Value> values(List<Integer> indices) throws Exception {
		validateIndices(indices);
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
		validateIndices(indices);
		List<Value> inValues = this.values(indices);
		return this.convertToStringList(inValues);
	}

	@Override
	public void addAll(List<Value> valueList) {
		this.values.addAll(valueList);
	}
	
	@Override
	public void addAll(Object[] objects) {
		for (Object obj : objects) {
			this.addObject(obj);
		}
	}

	@Override
	public void addAll(ValueList list) throws Exception {
		this.addAll(list.values());
	}

	@Override
	public void add(Value value) {
		this.values.add(value);
	}

	@Override
	public void addObject(Object obj) {
		this.add(new AnyRefValue(obj));
	}

	@Override
	public boolean hasIndex(int index)  throws Exception {
		return index < values.size();
	}

	@Override
	public Value valueAt(int index) throws Exception {
		validateIndex(index);
		return this.values.get(index);
	}
	
	@Override
	public String stringAt(int index) throws Exception {
		validateIndex(index);
		return this.values.get(index).toString();
	}

	@Override
	public Object objectAt(int index) throws Exception {
		validateIndex(index);
		return this.values.get(index).object();
	}

}
