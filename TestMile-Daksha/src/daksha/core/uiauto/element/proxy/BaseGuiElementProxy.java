package daksha.core.uiauto.element.proxy;

import java.io.File;
import java.io.IOException;

import daksha.core.problem.ErrorType;
import daksha.core.problem.Problem;
import daksha.core.uiauto.automator.proxy.GuiAutomatorProxy;
import daksha.core.uiauto.element.ManagedConcreteGuiElement;
import daksha.core.uiauto.element.ManagedGuiElement;
import daksha.core.uiauto.enums.GuiElementLoaderType;
import daksha.core.uiauto.identifier.GuiElementMetaData;
import daksha.tpi.uiauto.enums.GuiAutomationContext;
import daksha.tpi.uiauto.gui.Gui;

public class BaseGuiElementProxy implements ManagedGuiElement{
	private GuiElementMetaData emd = null;
	private String name = null;
	private String entityName = null;
	private String imagePath = null;
	private GuiElementLoaderType loaderType = null;
	private String label = null;
	private GuiAutomationContext idType = null;
	private ManagedConcreteGuiElement concreteElement = null;
	private Gui gui = null;
	private GuiAutomatorProxy automator = null;
	
	public BaseGuiElementProxy(GuiAutomatorProxy automator, GuiElementMetaData emd) {
		this.automator = automator;
		this.emd = emd;
	}
	
	public BaseGuiElementProxy(Gui gui, GuiAutomatorProxy automator, GuiElementMetaData emd) {
		this(automator, emd);
		this.gui = gui;
	}
	
	protected GuiAutomatorProxy getAutomator() {
		return this.automator;
	}
	
	protected Gui getGui() {
		return this.gui;
	}
	
	public void setGui(Gui gui) {
		this.gui = gui;
	}
	
	protected void setConcreteFinder(ManagedConcreteGuiElement finder) {
		this.concreteElement = finder;
	}
	
	protected ManagedConcreteGuiElement getFinder() {
		return this.concreteElement;
	}
	
	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getCompositeGuiName(){
		return this.entityName;
	}

	public void setCompositeGuiName(String name){
		this.entityName = name;
	}
	
	@Override
	public void setLoaderType(GuiElementLoaderType type) {
		this.loaderType = type;
	}

	@Override
	public GuiElementLoaderType getLoaderType() {
		return this.loaderType;
	}

	@Override
	public String getGuiLabel() {
		return this.label;
	}

	@Override
	public void setGuiLabel(String label) {
		this.label = label;
	}

	public String property(String propName) {
		return emd.get(propName);
	}

	public String getProperty(String propName) {
		return emd.get(propName);
	}

	public GuiElementMetaData getMetaData() {
		return this.emd;
	}
	
	public void setMetaData(GuiElementMetaData gei) {
		this.emd = gei;
	}

	public void setProperty(String propName, String value) {
		emd.set(propName, value);
	}
	
	public String getImagePath() throws Exception {
		return this.imagePath;
	}


	public void setImagePath(String imagePath) throws Exception {
		this.imagePath = imagePath;
	}
	
	private String takeScreenshotIfPossible() throws IOException{
		try{
			File path = null;//this.getProxy().takeScreenshot();
			if (path != null){
				return path.getCanonicalPath();
			} else {
				return "NA";
			}
		} catch (Exception e){
			return "Not able to take snapshot";
		}
	}

	protected String getClassForLoaderType() throws Exception{
		String rValue = "";
		if(this.getLoaderType() == null){
			return "Element API";
		}
		switch(this.getLoaderType()){
		case AUTOMATOR: rValue = "Element API"; break;
		case GUI: rValue = this.getGuiLabel(); break;
		case COMPOSITE_GUI: rValue = this.getCompositeGuiName() + "." + this.getGuiLabel(); break;
		}
		return rValue;
	}

	protected String getElementNameFillerForException() throws Exception{
		String rValue = "";
		if(this.getLoaderType() == null){
			return "";
		}
		switch(this.getLoaderType()){
		case AUTOMATOR: rValue = ""; break;
		case GUI:  rValue = String.format("name %s and ", this.getName()); break;
		case COMPOSITE_GUI: rValue = String.format("name %s and ", this.getName()); break;
		}
		return rValue;
	}

	protected String getElementNameFillerForException(ManagedGuiElement element) throws Exception{
		String rValue = "";
		if(element.getLoaderType() == null){
			return "";
		}
		switch(element.getLoaderType()){
		case AUTOMATOR: rValue =  ""; break;
		case GUI: rValue = String.format("name %s and ", element.getName()); break;
		case COMPOSITE_GUI: rValue = String.format("name %s and ", element.getName()); break;
		}
		return rValue;
	}

	protected Object throwElementActionException(Throwable e, String action, String filler) throws Exception{
		return throwBasicElementMessageException(e, ErrorType.ELEMENT_ACTION_FAILURE, action, filler);		
	}

	protected Object throwElementInquiryException(Exception e, String action, String filler) throws Exception{
		return throwBasicElementMessageException(e, ErrorType.ELEMENT_INQUIRY_FAILURE, action, filler);		
	}

	protected Object throwElementGetAttributeException(Exception e, String action, String filler) throws Exception{
		return throwBasicElementMessageException(e, ErrorType.ELEMENT_GET_ATTR_FAILURE, action, filler);		
	}
	
	protected Object throwElementException(
			Throwable e,
			String code,
			String action,
			String message
			) throws Exception{
		throw new Problem(
				"Element Proxy",
				getClassForLoaderType(),
				action,
				code,
				message,
				takeScreenshotIfPossible(),
				e
				);
	}
	
	protected Object throwBasicElementMessageException(Throwable e, String code, String action, String filler) throws Exception{
		return throwElementException(
				e, 
				code,
				action,
				String.format(
						code,
						concreteElement.getAutomatorName(),
						filler,
						getElementNameFillerForException(),
						this.getMetaData().getAllProperties().toString()
						)
				);		
	}

	protected Object throwUnsupportedException(String action) throws Exception{
		throw new Problem(
				"Element Proxy",
				getClassForLoaderType(),
				action,
				ErrorType.ELEMENT_UNSUPPORTED_ACTION,
				String.format(
						ErrorType.ELEMENT_UNSUPPORTED_ACTION,
						action,
						getElementNameFillerForException(),
						this.getMetaData().getAllProperties().toString()
						),
				takeScreenshotIfPossible()
				);	
	}
	
	protected Object throwExceptionFromProxy(String code, String action) throws Exception{
		throw new Problem(
				"Element Proxy",
				getClassForLoaderType(),
				action,
				code,
				String.format(
						code,
						getElementNameFillerForException(),
						this.getMetaData().getAllProperties().toString()
						),
				takeScreenshotIfPossible()
				);	
	}
	
	protected Object throwUnsupportedActionExceptionFromProxy(String code, String action) throws Exception{
		throw new Problem(
				"Element Proxy",
				getClassForLoaderType(),
				action,
				code,
				String.format(
						code,
						action,
						getElementNameFillerForException(),
						this.getMetaData().getAllProperties().toString()
						),
				takeScreenshotIfPossible()
				);	
	}

	protected Object throwElementGetInstanceException(Exception e, String action, String filler) throws Exception{
		return throwBasicElementMessageException(e, ErrorType.ELEMENT_GET_INSTANCE_FAILURE, action, filler);		
	}

	protected Object throwElementIdentificationException(Exception e, String action, String filler) throws Exception{
		return throwBasicElementMessageException(e, ErrorType.ELEMENT_IDENTIFICATION_FAILURE, action, filler);		
	}
	
}
