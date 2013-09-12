package katas.learning_kata.noughtsAndCrosses;

import katas.learning_kata.noughtsAndCrosses.GameStatus.GameStates;

public class GameStatusBuilder {
	public static GameStatusBuilder aGameStatusBuilder(){
		return new GameStatusBuilder();
	}

	private GameStates state;
	private String message;
	
	public GameStatusBuilder withAStatusOf(GameStates state){
		this.state = state;
		return this;
	}
	
	public GameStatusBuilder withAMessageOf(String message){
		this.message = message;
		return this;
	}
	
	public GameStatus build(){
		GameStatus gameStatus = new GameStatus();
		gameStatus.setStatus(state);
		gameStatus.setMessage(message);
		return gameStatus;
	}
}
