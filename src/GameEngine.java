import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameEngine {

	public static int numberOfSameCardNeeded = 4;

	public List<Player> players = new ArrayList<Player>();

	private int differentCardsCount;

	private List<Integer> cardsPool;

	public void setUp(int playerCount) {

		differentCardsCount = playerCount + 1;
		int cardsCount = differentCardsCount + numberOfSameCardNeeded;

		cardsPool = new ArrayList<Integer>(cardsCount);

		for (int i = 0; i < differentCardsCount; i++) {
			for (int j = 0; j < numberOfSameCardNeeded; j++) {
				cardsPool.add(new Integer(i));
			}
		}
		Collections.shuffle(cardsPool);

		// Triplez le nombre de « billets » par rapport au nombre de
		// participants (ex : 15 participants x 3 = 45 « billets »)
		int moneyCount = playerCount * 3;

		int moneyPerPlayer = moneyCount / playerCount;

		// create players
		for (int i = 0; i < playerCount; i++) {
			Player newPlayer = new Player();

			// Chaque joueur commence avec 4 cartes à jouer
			for (int j = 0; j < numberOfSameCardNeeded; j++) {
				newPlayer.cards.add(cardsPool.get(cardsCount - (i + j)));
				cardsPool.remove(cardsCount - (i + j));
			}

			players.add(newPlayer);
		}

	}

	public void round() {

		Collections.shuffle(this.players);
		int playersCount = this.players.size();

		for (int i = 0; i < playersCount; i++) {

			Player player = this.players.get(i);
			Player nextPlayer = this.players.get((i + 1) % playersCount);

			this.exchange(player, nextPlayer);
		}
	}

	private void exchange(Player player1, Player player2) {
		boolean hasSamecards = false;

		for (int i = 0; i < differentCardsCount; i++) {
			Integer card = new Integer(i);

			int player1number = Collections.frequency(player1.cards, card);
			int player2number = Collections.frequency(player2.cards, card);

			
			if (player1number >= 2 && player2number > 0) {
				player2.cards.remove(card);
				player1.cards.add(card);
				player1number++;
			} else if (player1number > 0 && player2number >= 2) {
				player1.cards.remove(card);
				player2.cards.add(card);
				player2number++;
			} else if (player1number >= 2 && player2number >= 2){
				hasSamecards = true;
			}

			if (player1number == numberOfSameCardNeeded) {
				takeAWealth(player1, card);
			}
		}
		
		if (!hasSamecards){
			
		}
	}

	private void takeAWealth(Player player1, Integer card) {
		for (int j = 0; j < numberOfSameCardNeeded; j++) {
			player1.cards.remove(card);
			cardsPool.add(new Integer(card.intValue()));
		}
		player1.wealth++;
		
		//aux joueurs qui sont parvenus à obtenir un carré de carte ; il leur redonne 4 cartes aléatoires
		((ArrayList<Integer>)cardsPool).trimToSize();
		Collections.shuffle(cardsPool);
		
		for (int j = 0; j < numberOfSameCardNeeded; j++) {
			player1.cards.add(cardsPool.get(j));
			cardsPool.remove(j);
		}
	}
}
