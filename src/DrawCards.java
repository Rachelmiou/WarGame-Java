package com.company;

import java.util.Arrays;
import java.util.Random;

public class DrawCards {
    private final boolean[] cards;

    public DrawCards() {
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
        return card;
    }

    public boolean IsCardDrawn(int card) {
        return cards[card];
    }

    public static void main(String[] args) {
        DrawCards cards = new DrawCards();

        for(int i = 0; i < 26; ++i) {
            int card = cards.DrawCard();
            System.out.println("Drew Card: " + card);
            System.out.println();
        }
    }
}
