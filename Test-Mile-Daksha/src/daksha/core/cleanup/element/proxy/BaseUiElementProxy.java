package daksha.core.cleanup.element.proxy;

import java.io.File;
import java.io.IOException;

import daksha.core.cleanup.automator.proxy.UiAutomatorProxy;
import daksha.core.cleanup.element.ManagedConcreteUiElement;
import daksha.core.cleanup.element.ManagedUiElement;
import daksha.core.cleanup.enums.ElementLoaderType;
import daksha.core.cleanup.picker.UiElementMetaData;
import daksha.core.problem.ErrorType;
import daksha.core.problem.Problem;
import daksha.tpi.cleanup.enums.UiAutomationContext;
import daksha.tpi.cleanup.ui.UI;

public class BaseUiElementProxy implements ManagedUiElement{
	private UiElementMetaData emd = null;
	private String name = null;
	private String entityName = null;
	private String imagePath = null;
	private ElementLoaderType loaderType = null;
	private String uiLabel = null;
	private UiAutomationContext idType = null;
	private ManagedConcreteUiElement concreteElement = null;
	private UI ui = null;
	private UiAutomatorProxy automator = null;
	
	public BaseUiElementProxy(UiAutomatorProxy automator, UiElementMetaData emd) {
		this.automator = automator;
		this.emd = emd;
	}
	
	public BaseUiElementProxy(UI ui, UiAutomatorProxy automator, UiElementMetaData emd) {
		this(automator, emd);
		this.ui = ui;
	}
	
	protected UiAutomatorProxy getAutomator() {
		return this.automator;
	}
	
	protected UI getUI() {
		return this.ui;
	}
	
	public void setUI(UI ui) {
		this.ui = ui;
	}
	
	protected void setConcreteFinder(ManagedConcreteUiElement finder) {
		this.concreteElement = finder;
	}
	
	protected ManagedConcreteUiElement getFinder() {
		return this.concreteElement;
	}
	
	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getCompositeUiName(){
		return this.entityName;
	}

	public void setCompositeUiName(String name){
		this.entityName = name;
	}
	
	@Override
	public void setLoaderType(ElementLoaderType type) {
		this.loaderType = type;
	}

	@Override
	public ElementLoaderType getLoaderType() {
		return this.loaderType;
	}

	@Override
	public String getUiLabel() {
		return this.uiLabel;
	}

	@Override
	public void setUiLabel(String label) {
		this.uiLabel = label;
	}

	public String property(String propName) {
		return emd.get(propName);
	}

	public String getProperty(String propName) {
		return emd.get(propName);
	}

	public UiElementMetaData getMetaData() {
		return this.emd;
	}
	
	public void setMetaData(UiElementMetaData gei) {
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
		case UI: rValue = this.getUiLabel(); break;
		case COMPOSITE_UI: rValue = this.getCompositeUiName() + "." + this.getUiLabel(); break;
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
		case UI:  rValue = String.format("name %s and ", this.getName()); break;
		case COMPOSITE_UI: rValue = String.format("name %s and ", this.getName()); break;
		}
		return rValue;
	}

	protected String getElementNameFillerForException(ManagedUiElement element) throws Exception{
		String rValue = "";
		if(element.getLoaderType() == null){
			return "";
		}
		switch(element.getLoaderType()){
		case AUTOMATOR: rValue =  ""; break;
		case UI: rValue = String.format("name %s and ", element.getName()); break;
		case COMPOSITE_UI: rValue = String.format("name %s and ", element.getName()); break;
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
