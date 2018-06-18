package daksha;

public class ErrorType {

	public static final String APPIUM_UNSUPPORTED_PLATFORM = "Unsupported platform: %s";

	public static final String APPIUM_UNSUPPORTED_BROWSER = "Unsupported browser %s for platform: %s";

	public static final String APPIUM_UNREACHABLE_BROWSER = "Not able to reach Appium Server for %s automation.";

	public static final String COMPARISON_IMAGE_NOT_FOUND = "Image file not found at path: %s";

	public static final String COMPARISON_NOT_POSSIBLE = "Image files are not comparable. Neither one seems to contain the other (size-wise). Left Image: %s. Right Image: %s.";

	/* UI Automator */

	public static final String ELEMENT_IDENTIFICATION_FAILURE = "%s failed to %s with %sproperties %s.";

	public static final String ELEMENT_GET_INSTANCE_FAILURE = "%s failed to %s of element with %sproperties %s.";

	public static final String ELEMENT_WAIT_FAILURE = "%s waited for %s seconds, without success for %s element with %sproperties %s.";

	public static final String ELEMENT_ACTION_FAILURE = "%s failed to %s element with %sproperties %s.";

	public static final String ELEMENT_GET_ATTR_FAILURE = "%s failed to get %s element with %sproperties %s.";

	public static final String ACTION_MULTIELEMENT_FAILURE = "%s failed to %s element with %sproperties %s and then %s element with %sproperties %s.";

	public static final String ELEMENT_UNSUPPORTED_ACTION = "Unsupported action: %s for element with %sproperties %s.";

	public static final String ELEMENT_INQUIRY_FAILURE = "%s was unable to check %s element with %sproperties %s.";

	public static final String PAGE_NULL_AUTOMATOR = "%s page was provided a null automator.";

	public static final String PAGE_UNDEFINED_ELEMENT = "Element with name %s is not defined for %s page.";

	public static final String COMPOSITE_PAGE_CONSTRUCTOR_NULL_AUTOMATOR = "Composite Page was provided a null value for %s Automator.";

	public static final String COMPOSITE_PAGE_GET_AUTOMATOR_NULL = "%s Automator is not a valid automator for composite page. Allowed Type: %s Automator.";

	public static final String MAPFILE_RELATIVE_PATH = "Page Mapper was provided a file with relative path: %s. Expected absolute path.";

	public static final String MAPFILE_NOT_FOUND = "Page Mapper was not able to find the source map file: %s.";

	public static final String MAPFILE_NOTAFILE = "Page Mapper was provided a path which is not a file: %s.";

	public static final String UNSUPPORTED_IDENTIFIER = "Unsupported identifier: %s.";

	public static final String UNSUPPORTED_MULTIPLE_IDENTIFIERS = "Multiple identifiers not supported: %s";

	public static final String PROPERTY_DOES_NOT_EXIST = "Property with the name %s does not exist in Property Manager.";

	public static final String UNSUPPORTED_MAP_FILE_FORMAT = "Unsupported map file format: %s.";

	public static final String AUTOMATOR_UNSUPPORTED_ACTION = "Unsupported action for: %s.";

	public static final String COMPOSITE_PAGE_NULL_AUTOMATOR = "Null Automator provided to Composite Page for %s automation.";

	public static final String COMPOSITE_PAGE_NONEXISTING_LABEL = "A page fragment with the label %s does not exist in %s composite page.";

	public static final String COMPOSITE_PAGE_NULL_LABEL = "Null was provided as the page fragment name for %s composite page.";

	public static final String UI_ELEMENT_INVALID_METADATA = "An invalid element definition was provided for %s context with meta-data: %s.";

	public static final String UI_NULL_ELEMENT = "Null was provided as element name for %s UI.";

	public static final String FACTORY_AUTOMATOR_MOBILE_NULL_APP_PATH = "Null value provided for mobile app path.";

	public static final String FACTORY_AUTOMATOR_UNSUPPORTED_CONTEXT = "%s automation context is not supported by Automator Factory.";

	public static final String FACTORY_METHOD_APPPATH_NOT_APPLICABLE = "Wrong factory method used for %s automation context. Use getAutomator(AutomationContext context).";

	public static final String UIAUTO_CONTEXT_HANDLER_NO_AUTO_FOR_LABEL = "No automator present with label: %s";

	public static final String UIAUTO_CONTEXT_HANDLER_NULL_AUTOMATOR = "Null automator provided.";

	public static final String ELEMENT_NEGATIVE_INEDX = "Negative index used for index based instance retieval for element with %sproperties %s.";

	public static final String ELEMENT_ZERO_ORDINAL = "Ordinal should be >= 1 in ordinal based instance retrieval for element with %sproperties %s.";

	public static final String ELEMENT_EMPTY_QUEUE = "Exception in instance retrieval for element with %sproperties %s. The element instance queue is empty. Try calling element.identifyAll() explicitly.";

	public static final String ELEMENT_UNSUPPORTED_SELECT_ACTION = "%s method works only for Drop downs and radio buttons. Not supported for element with %sproperties %s.";

}
