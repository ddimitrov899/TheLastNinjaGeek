package com.teamsluis.thelastninjageek;

public class Data {
	public static int ninjas = 1;
	public static int currentPlayer;
	public static int currentRound = 1;
	public static boolean playerUsedShuriken = false;
	public static boolean jokersAllowed = true;
	public static boolean alreadyPlayed = false;
	public static int[][] currentPlayerJokers;
	public static int[] scorePlayer;
	public static int[] playerHasJoker;
	public static String[] namePlayer = { "", "", "", "" };

	public static void setNinja() {

		int[] scorePlayerNew = new int[ninjas];
		int[] playerHasJokerNew = new int[ninjas];
		int[][] currentPlayerJokersNew = new int[ninjas][3];
		// for (int i = 0; i < namePlayer.length; i++) {
		// namePlayer[i] = "";
		// }
		// Setting new Values
		scorePlayer = scorePlayerNew;
		playerHasJoker = playerHasJokerNew;
		currentPlayerJokers = currentPlayerJokersNew;
		Jokers.initJokers();
	}
}