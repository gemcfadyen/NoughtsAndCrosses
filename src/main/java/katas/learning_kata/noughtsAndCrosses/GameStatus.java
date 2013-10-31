package katas.learning_kata.noughtsAndCrosses;

public class GameStatus {
	public enum GameStates {
		WINNER("Congratulations [%s] you have won! \n Game Over"), NO_WINNER("Game Over, there was no winner!");
		
		private String statusMessage;
		
		GameStates(String statusMessage){
			this.statusMessage = statusMessage;
		}
		
		public String getStatusMessage(){
			return statusMessage;
		}
	};

	private GameStates gameState;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public GameStates getStatus(){
		return gameState;
	}
	
	public void setStatus(GameStates state){
		gameState = state;
	}
}
