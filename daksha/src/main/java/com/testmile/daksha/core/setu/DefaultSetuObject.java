package com.testmile.daksha.core.setu;

public class DefaultSetuObject implements SetuManagedObject{
	private String setuId;

	@Override
	public String getSetuId() {
		return this.setuId;
	}
	
	protected void setSetuId(String id) {
		this.setuId = id;
	}

}
