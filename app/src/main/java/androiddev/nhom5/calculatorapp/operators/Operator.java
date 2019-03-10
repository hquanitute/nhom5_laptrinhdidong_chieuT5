package androiddev.nhom5.calculatorapp.operators;

import androiddev.nhom5.calculatorapp.interfaces.Token;

public abstract class Operator implements Token {

	protected java.lang.String notation;


	public Operator(){

	}

	public abstract double execute(java.util.Stack stack);

	public void printError(java.lang.String String){

	}

}