package com.company;

import java.util.Arrays;

public class WarGame {
    public static void ShuffleDeck(int[] deck, int[] discard, int dptr) {
        Arrays.fill(deck, -1);
        for(int i = 0; i < dptr; ++i) {
            int card = -1;
            while(card == -1) {
                card = discard[(int)Math.floor(Math.random() * dptr)];
            }
            deck[i] = card;
        }
        Arrays.fill(discard, -1);
    }

    public static int DrawCard(int[] deck, int[] discard, int[] Ptr) {
        if (Ptr[2] >= Ptr[0]) {
            // Out of cards reshuffle discard
            ShuffleDeck(deck, discard, Ptr[1]);
            Ptr[0] = Ptr[1];
            Ptr[1] = 0;
            Ptr[2] = 0;
        }

        return deck[Ptr[2]++];
    }

    public static void main(String[] Args) {
        // Split cards into 2 decks
        int TurnCounter = 0;
        boolean WarDeclared = false;

        int[] Player1Ptr = { 0, 0, 0};
        int[] Player2Ptr = { 0, 0, 0};

        int[][] Player1Deck = new int[3][52];
        int[][] Player2Deck = new int[3][52];

        Arrays.fill(Player1Deck[0], -1);
        Arrays.fill(Player1Deck[1], -1);
        Arrays.fill(Player1Deck[2], -1);
        Arrays.fill(Player2Deck[0], -1);
        Arrays.fill(Player2Deck[1], -1);
        Arrays.fill(Player2Deck[2], -1);

        DrawCards deck = new DrawCards();

        for(int i = 0; i < 26; ++i) {
            Player1Deck[0][Player1Ptr[0]++] = deck.DrawCard();
            Player2Deck[0][Player2Ptr[0]++] = deck.DrawCard();
        }

        while(true) {
            WarDeclared = false;
            // When a player is out of cards in deck shuffle discard pile into deck
            // Each player draws a card
            int Player1Card = DrawCard(Player1Deck[0], Player1Deck[1], Player1Ptr );
            int Player2Card = DrawCard(Player2Deck[0], Player2Deck[1], Player2Ptr );

            if (Player1Card == -1) {
                System.out.println("Player 1 is out of cards");
                System.out.println("Player 2 wins");
                break;
            }
            if (Player2Card == -1) {
                System.out.println("Player 2 is out of cards");
                System.out.println("Player 1 wins");
                break;
            }

            System.out.printf("\n------\nTurn %d\n", ++TurnCounter);

            // Compare cards
            // If both are equal draw three cards and compare last
            int k = 0;
            while (ValueOfCard.GetCardValue(Player1Card) == ValueOfCard.GetCardValue(Player2Card)) {
                WarDeclared = true;
                System.out.printf("Player1: %20s === %-20s :Player2\n", NameOfCard.GetCardName(Player1Card), NameOfCard.GetCardName(Player2Card));
                System.out.println("\n---WAR!---\n");
                for(int i = 0; i < 3; ++i) {
                    Player1Deck[2][k] = DrawCard(Player1Deck[0], Player1Deck[1], Player1Ptr );
                    Player2Deck[2][k] = DrawCard(Player2Deck[0], Player2Deck[1], Player2Ptr );

                    if (Player1Deck[2][k] == -1) {
                        System.out.println("Player 1 is out of cards");
                        System.out.println("Player 2 wins");
                        return;
                    }
                    if (Player2Deck[2][k] == -1) {
                        System.out.println("Player 2 is out of cards");
                        System.out.println("Player 1 wins");
                        return;
                    }

                    if (i < 2) {
                        System.out.printf("Player1: %20s :=: %-20s :Player2\n", NameOfCard.GetCardName(Player1Deck[2][k]), NameOfCard.GetCardName(Player2Deck[2][k]));
                    }
                    k++;
                }

                // Swap Player1Card with last card in war for ease of comparison
                int temp = Player1Deck[2][k-1];
                Player1Deck[2][k-1] = Player1Card;
                Player1Card = temp;

                // Swap Player1Card with last card in war for ease of comparison
                temp = Player2Deck[2][k-1];
                Player2Deck[2][k-1] = Player2Card;
                Player2Card = temp;
            }

            // If one is larger than the other that player gains both
            if (ValueOfCard.GetCardValue(Player1Card) > ValueOfCard.GetCardValue(Player2Card)) {
                System.out.printf("Player1: %20s <== %-20s :Player2\n", NameOfCard.GetCardName(Player1Card), NameOfCard.GetCardName(Player2Card));
                Player1Deck[1][Player1Ptr[1]++] = Player1Card;
                Player1Deck[1][Player1Ptr[1]++] = Player2Card;
                if (WarDeclared) {
                    for (int i = 0; i < k; ++i) {
                        Player1Deck[1][Player1Ptr[1]++] = Player1Deck[2][i];
                        Player1Deck[1][Player1Ptr[1]++] = Player2Deck[2][i];
                    }
                }
            } else if (ValueOfCard.GetCardValue(Player1Card) < ValueOfCard.GetCardValue(Player2Card)) {
                System.out.printf("Player1: %20s ==> %-20s :Player2\n", NameOfCard.GetCardName(Player1Card), NameOfCard.GetCardName(Player2Card));
                Player2Deck[1][Player2Ptr[1]++] = Player1Card;
                Player2Deck[1][Player2Ptr[1]++] = Player2Card;
                if (WarDeclared) {
                    for (int i = 0; i < k; ++i) {
                        Player2Deck[1][Player2Ptr[1]++] = Player1Deck[2][i];
                        Player2Deck[1][Player2Ptr[1]++] = Player2Deck[2][i];
                    }
                }
            }
        }
    }
}
