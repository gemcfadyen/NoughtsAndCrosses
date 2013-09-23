package katas.learning_kata.noughtsAndCrosses;

public class NoughtsAndCrossesGame {

	private static NoughtsAndCrosses game;
	
	public static void main(String[] args){
		game = new NoughtsAndCrosses();
		GameStatus status = game.playGame();
		System.out.println(status.getStatus() + " " + status.getMessage());
	}
}
