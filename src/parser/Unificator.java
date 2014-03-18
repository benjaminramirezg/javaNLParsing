/**
 * 
 */
package parser;

import grammaticalObject.Feature;
import grammaticalObject.Type;

/**
 * 
 * Defines an object able to check if two non-nested feature structures (objects Type) unify.
 *  
 * @author benjamin
 *
 */
public class Unificator {

	/**
	 *  Creates an Unificator object.
	 */
	
	public Unificator(){
		
	}
	
	/**
	 * 
	 * Takes two feature structures (Type objects) and retrieves a boolean value (true if the feature structures
	 * unify and false if they don't unify).
	 * 
	 * @param typeA
	 * @param typeB
	 * @return boolean
	 */
	
	public boolean unify(Type typeA, Type typeB){
		
		boolean success = true;

		Feature[] featuresA = typeA.getFeaturesAsList();
		Feature[] featuresB = typeB.getFeaturesAsList();
		
		for (int i = 0; i < featuresA.length && success; i++){

			String attributeA = featuresA[i].getName(); 
			String[] valueA = featuresA[i].getPossibleValues();
			
			for (int j = 0; j < featuresB.length && success; j++){

				String attributeB = featuresB[j].getName(); 
				String[] valueB = featuresB[j].getPossibleValues();
				
				if (attributeA.equals(attributeB) && !unifyValues(valueA, valueB)) {
						
					success = false;
				}
			}	
		}
		
		return success;
	}
	
	private boolean unifyValues(String[] valuesA,String[] valuesB){
		
		boolean success = false;
		
		for (int i = 0; i < valuesA.length && !success; i++){

			String valueA = valuesA[i];
			
			for (int j = 0; j < valuesB.length && !success; j++){

				String valueB = valuesB[j];
				
				if (valueA.equals(valueB)) {
					success = true;
				}
			}
		}
		
		return success;
	}
}