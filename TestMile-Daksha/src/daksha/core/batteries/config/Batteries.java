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
package daksha.core.batteries.config;

import java.util.Map;

import org.apache.log4j.Level;

import daksha.core.batteries.integration.ComponentConfigurator;
import daksha.core.enums.BatteriesPropertyType;
import daksha.core.lib.BatteriesConfigurator;
import daksha.core.lib.BatteriesSingleton;
import daksha.core.lib.CentralConfiguration;
import daksha.core.lib.ComponentIntegrator;
import daksha.core.value.DefaultStringKeyValueContainer;
import daksha.tpi.batteries.container.ReadOnlyStringKeyValueContainer;
import daksha.tpi.batteries.container.Value;
import daksha.tpi.ddauto.interfaces.DataRecord;
import daksha.tpi.ddauto.interfaces.DataReference;

public class Batteries {
	public static boolean logFileDiscoveryInfo = false;
	private static ComponentIntegrator integrator = new ComponentIntegrator();

	public static void addConfigurator(ComponentConfigurator configurator) {
		configurator.setIntegrator(integrator);
		configurator.setBaseDir(integrator.getBaseDir());
		integrator.addConfigurator(configurator);
	}

	public static void init() throws Exception {
		integrator.init();
		Batteries.addConfigurator(new BatteriesConfigurator());
	}

	public static void init(String baseDir) throws Exception {
		integrator.init(baseDir);
		Batteries.addConfigurator(new BatteriesConfigurator());
	}

	public static String getBaseDir() {
		return integrator.getBaseDir();
	}
	
	public static String getProjectDir() {
		return integrator.getProjectDir();
	}

	public static void processDakshaDefaults() throws Exception {
		integrator.processDefaults();
	}

	public static void processDakshaOptions(Map<String, Value> properties) throws Exception {
		integrator.processConfigProperties(properties);
	}

	public static void processCentralExecVars(Map<String, Value> properties) {
		integrator.processCentralExecVars(properties);
	}
	
	public static void processCentralUserOptions(Map<String, Value> properties) {
		integrator.processCentralUserOptions(properties);
	}

	public static void freezeCentralConfig() throws Exception {
		Configuration configuration = integrator.freezeCentralConfig();
	}

	public static class info {
		public static final String EXIT_ON_ERROR = "message.exit.on.error";
	}
}
