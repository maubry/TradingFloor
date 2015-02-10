import java.util.ArrayList;
import java.util.List;

public class Player {

	public List<Integer> cards = new ArrayList<Integer>();

	public int money;

	public int wealth = 0;

	public Player() {
	}

	public Player(int money) {
		this.money = money;
	}

	public Player(List<Integer> cards, int money) {
		this.cards = cards;
		this.money = money;
	}


}
