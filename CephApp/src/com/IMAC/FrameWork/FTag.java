package com.IMAC.FrameWork;

import java.util.Random;

public enum FTag {
	FRAGMENT_TOPFRAGMENT(false), 
	FRAGMENT_BOTTOMFRAGMENT();

	private final String text;
	private boolean isOnBack = true;

	private FTag() {
		this.text = random();
	}

	private FTag(boolean isOnBack) {
		this.text = random();
		this.isOnBack = isOnBack;
	}

	public String toString() {
		return text;
	}

	public boolean isOnBack() {
		return isOnBack;
	}

	public String random() {
		Random generator = new Random();
		StringBuilder randomStringBuilder = new StringBuilder();
		int randomLength = generator.nextInt(20);
		char tempChar;
		for (int i = 0; i < randomLength; i++) {
			tempChar = (char) (generator.nextInt(96) + 32);
			randomStringBuilder.append(tempChar);
		}
		return randomStringBuilder.toString();
	}
}