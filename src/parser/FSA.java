/**
 * 
 */
package parser;

import textualObject.NestedElement;

/**
 * A Deterministic Finite State Automata (FSA) that defines a regular pattern and tries matching it
 * against an input string. The pattern can be seen as a a sequence of tags defined in terms of
 * feature structures. The input string can be seen also as a sequence of feature structures. The FSA 
 * searches substrings in the input string that unifies with its own pattern. 
 * 
 * The FSA pattern is defined as a set of states (object State), a set of arcs (object Arc) and a set of tags (object Type). 
 * Arcs define possible transitions between states, given the presence of a certain tag in the input string. Tags
 * consist in non-nested feature structures.
 * 
 * The input string is a list of textual items (object Token, Segment...) whose analysis are a feature structure (object Type).
 * 
 * The patter of the FSA must be imported from a XML file according to the DTD '/javaNLParsing/grammars/FSA.dtd'.
 * 
 *  @author benjamin
 *  
 */
public class FSA extends Parser {

	private Arc[] arcs;
	private String[] outputTags;
	private String nonSuccessOutputTag = null;
	private State initialState;
	private State currentState;
	private int currentChainPosition;
	private int successfulPositionsInChain;
	private Unificator unificator;
	
	
	/**
	 * Creates a Deterministic Finite State Automata (FSA) according to the structure defined in a XML file.
	 * The DTD of these FSAs can be found in the file '/javaNLParsing/grammars/FSA.dtd' of this project. 
	 * 
	 * This DTD definition establish the possible structures of FSA objects, but it doesn't prevent the creation
	 * of non-deterministic automata. However the actual implementation of FSA doesn't support the use of
	 * non-deterministic declarations.  The DTD defines a set of states, and a set of arcs that define possible transitions between states. Arcs
	 * are labeled with tags that consist in non-nested feature structures (object Type).  Different definitions
	 * of FSAs can be saved, according to FSA.dtd, in the folder grammars of this project.
	 * 
	 * The labels that the analysis assigns to the input items that match with the FSA must be setup in the
	 * DTD, in the 'message' attribute of 'state' elements. If an item 'i' lets a transition until a state 'k',
	 * the attribute 'message' in 'k' is the label of 'i' in the output. On the other hand, items in the input
	 * without a successful match are labeled with a default label that can be setup in the FSA object. 
	 * 
	 * @param file  Path of the xml file in which the structure of the FSA is defined ('./grammars/example.xml'). 
	 * @param nonSuccessOutputTag  Label that the analysis gives to the non matched items in the input string.
	 * 
	 */
	
	public FSA(String file, String nonSuccessOutputTag){
		
		FSAStructureBuilder builder = new FSAStructureBuilder();  // The XML syntax is parsed by a FSAStructureBuilder object 
		arcs = builder.createStructureFromXMLFile(file);          // that maps XML into the java FSA structure
		initialState = builder.getInitialState();                 // Initial State of the FSA is retrieved by the FSAStructureBuilder object
		                                                          // Tags in the FSA are feature structures, son the match between
		
		this.nonSuccessOutputTag = nonSuccessOutputTag;           
		
		unificator = new Unificator();                            // the input chain and FSA's tags must is defined in terms of unification
                                                                   // This is the object in charge of check unification of feature structures  
		
	}                                                             

	
	/**
	 * Performs the parsing of an input chain according to the FSA. It takes an input list of 'n' textual elements and retrieves a 
	 * output list of 'n' labels (label 'i' is the analysis that the FSA assigns to the correspondent input element 'i').
	 * The method searches matches of a pattern in a sequential way over the input chain. If the FSA finishes a successful matching in 
	 * the position 'i' of the input chain the next try will start in the position 'i + 1'. And if the FSA finishes a partial unsuccessful
	 * match between positions 'k...i', the next try will start at position 'k + 1'.
	 * 
	 * Non successfully matched items in the input correspond to a non-match label (typically null), and different matched items receive 
	 * a correspondent label.
	 * 
	 * @param input  The list of 'n' items in the input chain (typically Token objects, but also Segment). 
	 * @return The list of 'n' analyzed items. Every label is a String.
	 */
	
	public String[] parse(NestedElement[] input){
		
		currentState = initialState;
		outputTags = new String[input.length];
		
		for (int i = 0; i < input.length; i++){
			
			NestedElement element = input[i];
			currentChainPosition = i;
			
			if (tryTransition(element)){
				
				manageSuccessfulTransition();
				
			} else {
			
				i = manageNonSuccessfulTransition(i);	
			}
		}
	
		return outputTags;
	}
	
	
	private void manageSuccessfulTransition(){
		
		outputTags[currentChainPosition] = currentState.getMessage();
		
		if (this.currentState.isFinalState()){

			currentState = initialState;
			successfulPositionsInChain = 0;
			
		} else {
			
			successfulPositionsInChain++;
		}
	}
	
	private int manageNonSuccessfulTransition(int i){

		currentState = initialState;

		for (int j = currentChainPosition - 1; successfulPositionsInChain > 0; successfulPositionsInChain--){
		
			outputTags[j] = nonSuccessOutputTag;
			i = j;
		}
	
		return i;
	}		

	
	private boolean tryTransition(NestedElement element){
		
		boolean success = false;
		
		for (int i = 0; i < arcs.length && !success; i++) {

			Arc arc = arcs[i];
			
			if (arc.getInitialState().getId() == this.currentState.getId() &&
			    unificator.unify(element.getAnalysis(), arc.getTag())) {
				
				this.currentState = arc.getFinalState(); 
				success = true;
			}
		}
		
		return success;
	}
	

}
