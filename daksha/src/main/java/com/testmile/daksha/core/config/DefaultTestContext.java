package com.testmile.daksha.core.config;

import java.util.Map;

import com.testmile.daksha.DakshaSingleton;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.daksha.tpi.test.TestConfig;
import com.testmile.daksha.tpi.test.TestContext;
import com.testmile.daksha.tpi.test.TestSession;
import com.testmile.trishanku.tpi.enums.Browser;
import com.testmile.trishanku.tpi.enums.OSType;
import com.testmile.trishanku.tpi.enums.SetuOption;
import com.testmile.trishanku.tpi.value.StringKeyValueMap;

public class DefaultTestContext implements TestContext {
	private String name;
	private String parentConfigSetuId = null;
	private TestSession testSession;
	private SetuOptionContainer setuOptions = new SetuOptionContainer();
	private StringKeyValueMap userOptions = new StringKeyValueMap();

	public DefaultTestContext(TestSession testsession, String name) {
		this.testSession = testSession;
		this.name = name;
	}
	
	protected void setParentConfig(TestConfig parentConfig) {
		this.parentConfigSetuId = parentConfig.getSetuId();
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#addSetuOption(com.testmile.trishanku.tpi.enums.SetuOption, java.lang.Object)
	 */
	@Override
	public void addSetuOption(SetuOption option, Object value) throws Exception {
		setuOptions.addObject(option, value);
	 }
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#addUserOption(java.lang.String, java.lang.Object)
	 */
	@Override
	public void addUserOption(String option, Object obj) throws Exception {
		userOptions.addObject(DakshaSingleton.INSTANCE.normalizeUserOption(option), obj);
	} 
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#addOption(java.lang.String, java.lang.Object)
	 */
	@Override
	public void addOption(String option, Object obj) throws Exception {
		String normalizedOption = DakshaSingleton.INSTANCE.normalizeUserOption(option);
		try {
			SetuOption sOption = SetuOption.valueOf(normalizedOption);
			this.setuOptions.addObject(sOption, obj);
		} catch (Exception e) {
			userOptions.addObject(option, obj);
		}	
	} 
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#addOptions(java.util.Map)
	 */
	@Override
	public void addOptions(Map<String,String> options) throws Exception {
		for(String option: options.keySet()) {
			this.addOption(option,  options.get(option));
		}
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#automationContext(com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext)
	 */
	@Override
	public TestContext automationContext(GuiAutomationContext context) throws Exception {
		addSetuOption(SetuOption.GUIAUTO_CONTEXT, context.toString());
		return this;
	}

	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#browserType(com.testmile.trishanku.tpi.enums.Browser)
	 */
	@Override
	public TestContext browserType(Browser browser) throws Exception {
		addSetuOption(SetuOption.BROWSER_NAME, browser.toString());
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#targetPlatform(com.testmile.trishanku.tpi.enums.OSType)
	 */
	@Override
	public TestContext targetPlatform(OSType osType) throws Exception {
		addSetuOption(SetuOption.TESTRUN_TARGET_PLATFORM, osType.toString());
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#uuiAutoMaxWaitTime(int)
	 */
	@Override
	public TestContext uuiAutoMaxWaitTime(int seconds) throws Exception {
		addSetuOption(SetuOption.GUIAUTO_MAX_WAIT, String.valueOf(seconds));
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#build()
	 */
	@Override
	public TestConfig build() throws Exception {
		String configSetuId = this.testSession.registerConfig(setuOptions.strItems(), userOptions.items());
		return new DefaultTestConfig(this.testSession, this.name, configSetuId);
	}
}
