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

package com.testmile.setu.agent.guiauto.core.handler.automator.sikuli;

import org.sikuli.script.Screen;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractAutomatorHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.Scroller;

public class SikuliScroller extends AbstractAutomatorHandler implements Scroller{
	private int defaultMouseCount = 5;
	
	public SikuliScroller(SetuAgentConfig config) throws Exception {
		super(config);
	}
	
	@Override
	public void scrollDown(int times)  throws Exception{
		Screen screen = new Screen();
		screen.mouseDown(defaultMouseCount* times);
	}
	

	@Override
	public void scrollUp(int times)  throws Exception{
		Screen screen = new Screen();
		screen.mouseUp(defaultMouseCount* times);
	}

}