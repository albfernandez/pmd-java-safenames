package examples;

public class RejectedVariableNames {

	private String invalidFieldNameÑ;
	
	public RejectedVariableNames() {
		super();
	}
	public int function1(String parameter) {
		return function(parameter);
	}
	
	private int function(String invalidParameterNameÑ) {
		String invalidVariableÑ = invalidParameterNameÑ;
		this.invalidFieldNameÑ = invalidVariableÑ;
		return invalidVariableÑ.length();
	}
	public String getX() {
		return this.invalidFieldNameÑ;
	}
}
