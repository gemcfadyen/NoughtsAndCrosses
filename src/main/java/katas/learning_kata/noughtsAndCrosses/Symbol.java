package katas.learning_kata.noughtsAndCrosses;

public enum Symbol{
	X('x'), O('o'), EMPTY('-');
	private char value;
	
	Symbol(char value){
		this.value = value;
	}
	
	public char getCharValue(){
		return value;
	}
	
	public static Symbol valueOfChar(char charValue) {
		if (charValue == '-') return EMPTY;
		
		String value = String.valueOf(charValue).toUpperCase();
		return Symbol.valueOf(value);
	}
}
