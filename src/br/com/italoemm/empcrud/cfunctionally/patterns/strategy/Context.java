package br.com.italoemm.empcrud.cfunctionally.patterns.strategy;

public class Context {
	/**
	 * @author ${github/italoemm}
	 *
	 * 
	 */
	/* I use this pattern to count ID , cause all tables has a id 
	 * so why not use a strategy pattern to return id, each one are going to return int 
	 * and each are going to do almost the same functionally with only a individual operation.
	 * like calculator, I could use strategy pattern in it - almost the same functionally, cause
	 * what only change is operation (-,+,*,/).
	 */
    private InterfaceGenerateId ig; 
    
	public Context(InterfaceGenerateId ig) {
		this.ig=ig;
	}

	public int executeStrategy() throws Exception{
		return ig.generateID();
	}
}
