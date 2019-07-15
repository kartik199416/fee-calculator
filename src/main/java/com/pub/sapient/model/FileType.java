package com.pub.sapient.model;

public enum FileType {
	CSV("CSV"), XML("XML"), TEXT("TEXT");
	String type;

	FileType(String type) {
		this.type = type;
	}
}
