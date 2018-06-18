package daksha.core.batteries.config;

import java.util.Map;

public class CentralConfiguration extends BaseConfiguration{
	private boolean frozen = false;
	
	 public CentralConfiguration() throws Exception {
		 super();
	 }
	 
	 public void freeze(){
		 this.frozen = true;
	 }

	private void validateFrozen() throws Exception {
		if (frozen) {
			throw new Exception("You can not add options to central configuration after it is frozen.");
		}
	}
	
	@Override
	public void add(String k, String v) throws Exception {
		validateFrozen();
		super.add(k,v);
	 } 
	 
	@Override
	public void add(Map<String,String> map) throws Exception {
		validateFrozen();
		super.add(map);
	 } 
}
