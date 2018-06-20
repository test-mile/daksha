package daksha.core.leaping.element.proxy;

import java.io.File;
import java.io.IOException;

import daksha.core.leaping.automator.proxy.GuiAutomatorProxy;
import daksha.core.leaping.element.ManagedConcreteGuiElement;
import daksha.core.leaping.element.ManagedElement;
import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.identifier.GuiElementMetaData;
import daksha.core.problem.ErrorType;
import daksha.core.problem.Problem;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.enums.GuiAutomationContext;
import daksha.tpi.leaping.pageobject.Page;

public class BaseGuiElementProxy implements ManagedElement{
	private GuiElementMetaData emd = null;
	private String name = null;
	private String entityName = null;
	private String imagePath = null;
	private ElementLoaderType loaderType = null;
	private String uiLabel = null;
	private GuiAutomationContext idType = null;
	private ManagedConcreteGuiElement concreteElement = null;
	private Page page = null;
	private GuiAutomatorProxy automator = null;
	
	public BaseGuiElementProxy(GuiAutomatorProxy automator, GuiElementMetaData emd) {
		this.automator = automator;
		this.emd = emd;
	}
	
	public BaseGuiElementProxy(Page page, GuiAutomatorProxy automator, GuiElementMetaData emd) {
		this(automator, emd);
		this.page = page;
	}
	
	protected GuiAutomatorProxy getAutomator() {
		return this.automator;
	}
	
	protected Page getPage() {
		return this.page;
	}
	
	public void setPage(Page page) {
		this.page = page;
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

	public String getCompositePageName(){
		return this.entityName;
	}

	public void setCompositePageName(String name){
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
	public String getPageLabel() {
		return this.uiLabel;
	}

	@Override
	public void setPageLabel(String label) {
		this.uiLabel = label;
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
	
	public void setIdentifier(GuiElementMetaData gei) {
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
		case PAGE: rValue = this.getPageLabel(); break;
		case COMPOSITE_PAGE: rValue = this.getCompositePageName() + "." + this.getPageLabel(); break;
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
		case PAGE:  rValue = String.format("name %s and ", this.getName()); break;
		case COMPOSITE_PAGE: rValue = String.format("name %s and ", this.getName()); break;
		}
		return rValue;
	}

	protected String getElementNameFillerForException(ManagedElement element) throws Exception{
		String rValue = "";
		if(element.getLoaderType() == null){
			return "";
		}
		switch(element.getLoaderType()){
		case AUTOMATOR: rValue =  ""; break;
		case PAGE: rValue = String.format("name %s and ", element.getName()); break;
		case COMPOSITE_PAGE: rValue = String.format("name %s and ", element.getName()); break;
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
