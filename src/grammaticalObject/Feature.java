/**
 * 
 */
package grammaticalObject;


/**
 * @author benjamin
 *
 */
public class Feature implements Instance {

	private String[] values;
	private String name;
	
	public Feature(String[] values, String name){
		
		this.values = values;
		this.name = name;
	}

	public String[] getPossibleValues() {
		
		return values;
	}

	public String getName() {
		return name;
	}

}
