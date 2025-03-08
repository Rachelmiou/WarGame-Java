package com.company;

import java.util.Arrays;
import java.util.Random;

public class CardGame {
    private final boolean[] cards;
    private static final String[] suits = new String[] {"Spades", "Diamonds", "Clubs", "Hearts"};
    private static final String[] names = new String[] {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

    public CardGame() {
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

    public static int GetCardValue(int card) {
        return Math.min(10, (card % 13) + 1);
    }

    public static String GetWinner(int[] player1, int[] player2) {
        int p1Values = 0;
        int p2Values = 0;

        for(int index = 0; index < 3; ++index) {
            p1Values += CardGame.GetCardValue(player1[index]);
            p2Values += CardGame.GetCardValue(player2[index]);
        }

        if (p1Values > p2Values) {
            return "Player 1";
        }
        if (p1Values < p2Values) {
            return "Player 2";
        }
        return "Tie";
    }

    public static void main(String[] args) {
        CardGame cards = new CardGame();

        int[] player1 = new int[3];
        int[] player2 = new int[3];

        for (int index = 0; index < 3; ++index) {
            player1[index] = cards.DrawCard();
            player2[index] = cards.DrawCard();
        }

        System.out.println("Player 1 drew:");

        for (int index = 0; index < 3; ++index) {
            System.out.println(" - the " + CardGame.GetCardName(player1[index]));
        }

        System.out.println("Player 2 drew:");

        for (int index = 0; index < 3; ++index) {
            System.out.println(" - the " + CardGame.GetCardName(player2[index]));
        }

        System.out.println("The winner is: " + CardGame.GetWinner(player1, player2));
    }
}
