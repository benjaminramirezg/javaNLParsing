/**
 * 
 */
package parser;

/**
 * 
 * Script intended to build a FSA and test it
 * @author benjamin
 *
 */
public class Parse {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String[] input = {"DA","DA","NC","Fp"};
		InputParserBuilder inputBuilder = new InputParserBuilder();
		FSA automata = new FSA("./grammars/FSA.xml",null);
		inputBuilder.buildInputParser(input);
		String[] myObj = automata.parse(inputBuilder.buildInputParser(input));
		
		for (String kk : myObj){

			System.out.print(kk + " ");
		}
		


	}

}
