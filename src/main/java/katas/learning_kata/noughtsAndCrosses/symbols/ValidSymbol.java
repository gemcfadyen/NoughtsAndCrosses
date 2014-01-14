package katas.learning_kata.noughtsAndCrosses.symbols;

public enum ValidSymbol implements Some, Symbol {
	 X('x'), O('o'), EMPTY('-');
     private char value;
     
     ValidSymbol(char value){
             this.value = value;
     }
     
     public char getCharValue(){
             return value;
     }
     
     public static ValidSymbol valueOfChar(char charValue) {
             if (charValue == '-') return EMPTY;
             
             String value = String.valueOf(charValue).toUpperCase();
             return ValidSymbol.valueOf(value);
     }
}
