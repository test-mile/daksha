/*******************************************************************************
 * Copyright 2015-18 Test Mile Software Testing Pvt Ltd
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

package arjex.s01config;

import arjuna.tpi.Arjuna;
import arjuna.tpi.enums.ArjunaOption;
import arjuna.tpi.test.TestContext;

public class Basic6ToTestCLIConfig{
	
	public static void main (String args[]) throws Exception {
		Arjuna.init();

		String contextName = "custom";
		TestContext context = Arjuna.createTestContext(contextName);
		System.out.println("central: max wait = " + context.getConfig().getArjunaOptionValue(ArjunaOption.GUIAUTO_MAX_WAIT));
		System.out.println("central: a.b.c = " + context.getConfig().getUserOptionValue("a.b.c"));

		context
		.ConfigBuilder()
		.firefox()
		.guiAutoMaxWaitTime(30)
		.build("ff");
		
		System.out.println("ff: max wait = " + context.getConfig("ff").getArjunaOptionValue(ArjunaOption.GUIAUTO_MAX_WAIT));
		System.out.println("ff: a.b.c = " + context.getConfig("ff").getUserOptionValue("a.b.c"));
		Arjuna.getLogger().debug("hello");
	}

}
