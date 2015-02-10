
public class Main {

	public static void main(String[] args) {
		
		GameEngine game = new GameEngine();
		
		game.setUp(100);
		for (int i = 0; i < 10000; i++) {
			game.round();
		}
		
		display(game);

	}

	private static void display(GameEngine game) {
		int totalWealth = 0;
		
		int playerCount = 0;
		for (Player player : game.players) {
			System.out.println("\nPlayer: "+playerCount);
			System.out.println("wealth "+player.wealth);
			System.out.println("money "+player.money);
			
			for (Integer card : player.cards) {
				System.out.println("card: "+card.intValue());
			}
			
			totalWealth += player.wealth;
			
			playerCount++;
		}
		
		System.out.println("Total wealth: "+totalWealth);
	}

}
