/*******************************************************************************
 * Copyright 2015-19 Test Mile Software Testing Pvt Ltd
 * 
 * Website: www.TestMile.com
 * Email: support [at] testmile.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package arjuna.lib.setu.guiauto.requester.automator;

import arjuna.lib.setu.core.requester.config.SetuActionType;
import arjuna.lib.setu.core.requester.connector.SetuArg;
import arjuna.lib.setu.core.requester.connector.SetuResponse;
import arjuna.lib.setu.guiauto.requester.component.GuiAutoComponentFactory;
import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.GuiDriverExtendedConfig;
import arjuna.tpi.test.TestConfig;

public class DefaultGuiAutomator extends AbstractAppAutomator implements GuiAutomator {
	private GuiDriverExtendedConfig extendedConfig;
	
	public DefaultGuiAutomator(TestConfig config) throws Exception {
		super(config);
		this.launch();
	}
	
	public DefaultGuiAutomator(TestConfig config, GuiDriverExtendedConfig extendedConfig) throws Exception {
		super(config);
		this.extendedConfig = extendedConfig;
		this.launch();
	}

	private void launch() throws Exception {
		SetuResponse response;
		if (this.extendedConfig != null) {
			response = this.sendRequest(
					SetuActionType.TESTSESSION_LAUNCH_GUIAUTOMATOR, 
					SetuArg.configArg(this.getConfig().getSetuId()),
					SetuArg.arg("extendedConfig", extendedConfig)
			);
		} else {
			response = this.sendRequest(
					SetuActionType.TESTSESSION_LAUNCH_GUIAUTOMATOR, 
					SetuArg.configArg(this.getConfig().getSetuId())
			);				
		}
		this.setSetuId(response.getValueForGuiAutomatorSetuId());
		this.setSelfSetuIdArg("automatorSetuId");
		
		SetuResponse winResponse = this.sendRequest(SetuActionType.GUIAUTO_GET_MAIN_WINDOW);
		this.setMainWindow(GuiAutoComponentFactory.MainWindow(this.getTestSession(), this, winResponse.getValueForElementSetuId()));
		
		this.setDomRoot(GuiAutoComponentFactory.DomRoot(this.getTestSession(), this));
		this.setBrowser(GuiAutoComponentFactory.Browser(this.getTestSession(), this));
	}
	


	@Override
	public void quit() throws Exception {
		this.sendRequest(SetuActionType.TESTSESSION_QUIT_GUIAUTOMATOR);
	}

}
