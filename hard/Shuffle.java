package hard;


public class Shuffle {

    public static class Card {
        public String value;
        public String symbol;

        public Card(String value, String symbol) {
            this.value = value;
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return "Card{" +
                    "value='" + value + '\'' +
                    ", symbol='" + symbol + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] symbols = {"diamond", "spade", "tree", "hearts"};
        Card[] cards = new Card[52];
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < symbols.length; j++) {
                cards[j + 4 * i] = new Card(values[i], symbols[j]);
            }
        }
        print(cards);

        shuffle(cards);
    }

    private static void shuffle(Card[] cards) {
        System.out.println("Shuffling...");
        for (int i = 0; i < cards.length; i++) {
            int k = (int) (Math.random() * (i + 1));
            Card temp = cards[k];
            cards[k] = cards[i];
            cards[i] = temp;
        }
        print(cards);
    }

    private static void print(Card[] cards) {
        for (Card card : cards) {
            System.out.println(card);
        }
    }


}
