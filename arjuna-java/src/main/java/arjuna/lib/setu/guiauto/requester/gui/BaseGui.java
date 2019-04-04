package arjuna.lib.setu.guiauto.requester.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arjuna.lib.enums.GuiAutomationContext;
import arjuna.lib.setu.core.requester.config.SetuActionType;
import arjuna.lib.setu.core.requester.connector.SetuArg;
import arjuna.lib.setu.core.requester.connector.SetuResponse;
import arjuna.lib.setu.guiauto.requester.automator.AbstractAppAutomator;
import arjuna.lib.setu.testsession.requester.TestSession;
import arjuna.tpi.guiauto.Gui;
import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.With;
import arjuna.tpi.guiauto.component.Browser;
import arjuna.tpi.guiauto.component.ChildWindow;
import arjuna.tpi.guiauto.component.DomRoot;
import arjuna.tpi.guiauto.component.DropDown;
import arjuna.tpi.guiauto.component.Frame;
import arjuna.tpi.guiauto.component.GuiElement;
import arjuna.tpi.guiauto.component.GuiMultiElement;
import arjuna.tpi.guiauto.component.MainWindow;
import arjuna.tpi.guiauto.component.RadioGroup;

public class BaseGui extends AbstractAppAutomator implements Gui{

	private GuiAutomator automator;
	private Map<String, Gui> children = new HashMap<String, Gui>();
	private GuiAutomationContext autoContext;
	private TestSession testSession;
	
	private String label;
	private String defFileName;
	private boolean guiRegistered = false;
	private Gui parent = null;
	
	public BaseGui(GuiAutomator automator) throws Exception {
		super(automator.getConfig());
		this.setConfig(automator.getConfig());
		this.automator = automator;
		this.autoContext = automator.getAutomationContext();
		this.testSession = automator.getTestSession();
		this.setAutomatorSetuIdArg(automator.getSetuId());
	}
	
	public BaseGui(GuiAutomator automator, Gui parent) throws Exception {
		this(automator);
		this.parent = parent;
	}
	
	private void checkRegStatus() throws Exception {
		if (guiRegistered) throw new Exception("Attempt to change Gui critical attribute post registration with Setu.");
	}
	
	protected void setLabel(String label) throws Exception {
		checkRegStatus();
		this.label = label;
		this.setDefFileName(label + ".gns");
	}
	
	protected void setDefFileName(String name) throws Exception {
		checkRegStatus();
		this.defFileName = name;
	}
	
	protected void register() throws Exception{
		if (guiRegistered) throw new Exception("Attempt to re-register Gui with Setu.");
		List<SetuArg> args = new ArrayList<SetuArg>();
		args.add(SetuArg.arg("testSessionSetuId", this.testSession.getSetuId()));
		args.add(SetuArg.arg("automatorSetuId", automator.getSetuId()));
		args.add(SetuArg.arg("label", label));
		args.add(SetuArg.arg("name", this.getClass().getSimpleName()));
		args.add(SetuArg.arg("qualName", this.getClass().getName()));
		args.add(SetuArg.arg("defFileName", defFileName));
		
		String guiSetuId = null;
		SetuArg[] sArgs;
		if (parent ==  null) {
			sArgs = args.toArray(new SetuArg[args.size()]);
			guiSetuId = this.testSession.createGui(automator, sArgs);		
		} else {
			args.add(SetuArg.arg("parentGuiSetuId", parent.getSetuId()));
			sArgs = args.toArray(new SetuArg[args.size()]);
			SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_GUI_CREATE_GUI, sArgs);
			guiSetuId = response.getGuiSetuId();
		}

		this.setSetuId(guiSetuId);
		this.setSelfSetuIdArg("guiSetuId");
		
		if (parent != null) {
			parent.addChild(label, this);
		}
		
		load();
	}
	
	public BaseGui(GuiAutomator automator, String label, String defFileName) throws Exception {
		this(automator);
		this.setLabel(label);
		this.setDefFileName(defFileName);
		this.register();
	}
	
	public BaseGui(GuiAutomator automator, String label, String defFileName, Gui parent) throws Exception {
		this(automator, parent);
		this.setLabel(label);
		this.setDefFileName(defFileName);
		this.register();
	}

	public GuiAutomator getAutomator() {
		return this.automator;
	}

	protected String getQualifiedName() {
		return this.getClass().getName();
	}

	public void addChild(String label, Gui gui) {
		children.put(label.toLowerCase(), gui);
	}

	public Gui getChild(String label) throws Exception {
		if (label != null){
			if (children.containsKey(label.toLowerCase())){
				return children.get(label.toLowerCase());
			} else{
				throw new Exception(String.format("No child Gui with label: %s defined.", label));
			}
		} else {
			throw new Exception("Child Gui label is null.");
		}
	}

	protected void reachUntil() throws Exception {
		// Child classes can override this and write any necessary loading instructions.
	}

	protected void validateReadiness() throws Exception {
	}

	public final void load() throws Exception {
		try {
			this.validateReadiness();
		} catch (Exception e) {
			try {
				this.reachUntil();
				this.validateReadiness();
			} catch (Throwable f) {
				throw new Exception(
						String.format("UI [%s] with SetuId [%s] did not load as expected. Error: %s.", 
								this.getClass().getName(), this.getSetuId(), e.getMessage())
				);
			}
		}
	}

	@Override
	public GuiElement Element(String name) throws Exception {
		return this.Element(With.assignedName(name));
	}

	@Override
	public GuiMultiElement MultiElement(String name) throws Exception {
		return this.MultiElement(With.assignedName(name));
	}

	@Override
	public DropDown DropDown(String name) throws Exception {
		return this.DropDown(With.assignedName(name));
	}

	@Override
	public RadioGroup RadioGroup(String name) throws Exception {
		return this.RadioGroup(With.assignedName(name));
	}

	@Override
	public ChildWindow ChildWindow(String name) throws Exception {
		return this.childWindow(With.assignedName(name));
	}

	@Override
	public Frame Frame(String name) throws Exception {
		return this.Frame(With.assignedName(name));
	}

	public MainWindow MainWindow() throws Exception {
		return this.automator.MainWindow();
	}

	public Browser Browser() {
		return this.automator.Browser();
	}

	public DomRoot DomRoot() {
		return this.automator.DomRoot();
	}

	public boolean isGui() {
		return true;
	}

}