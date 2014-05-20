package com.teamsluis.thelastninjageek;

public enum MenuOption {
	SINGLEPLAYER(0), MULTIPLAYER(1), OPTIONS(2), RULES(3), EXIT(4);

	private final int value;

	private MenuOption(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}