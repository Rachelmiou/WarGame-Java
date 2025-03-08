package com.company;

import java.util.Arrays;
import java.util.Random;

public class NameOfCard {
    private final boolean[] cards;
    private static final String[] suits = new String[] {"Spades", "Diamonds", "Clubs", "Hearts"};
    private static final String[] names = new String[] {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

    public NameOfCard() {
        cards = new boolean[52];
        Arrays.fill(cards, false);
    }

    public int DrawCard() {
        Random random = new Random();
        int card = random.nextInt(52);
        int counter = 0;
        while(cards[card] && counter < 52) {
            card = random.nextInt(52);
            counter++;
        }
        cards[card] = true;
        return  card;
    }

    public boolean IsCardDrawn(int card) {
        return cards[card];
    }

    public static String GetCardName(int card) {
        int suit = card / 13;
        int name = card % 13;

        return String.format("%s of %s", names[name], suits[suit]);
    }

    public static void main(String[] args) {
        NameOfCard cards = new NameOfCard();

        for(int i = 0; i < 26; ++i) {
            int card = cards.DrawCard();
            System.out.println("Drew Card: " + card);
            System.out.println(" - Card Name: " + NameOfCard.GetCardName(card));
            System.out.println();
        }
    }
}
