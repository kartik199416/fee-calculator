package com.pub.sapient.reader.factory;

import com.pub.sapient.model.FileType;
import com.pub.sapient.reader.ITransactionReader;
import com.pub.sapient.reader.impl.CSVTransactionReader;
import com.pub.sapient.reader.impl.TEXTTransactionReader;
import com.pub.sapient.reader.impl.XMLTransactionReader;

public class TransactionReaderFactory {

	public static ITransactionReader getTransactionReader(FileType fileType) {

		switch (fileType) {
		case CSV:
			return new CSVTransactionReader();
		case XML:
			return new XMLTransactionReader();
		case TEXT:
			return new TEXTTransactionReader();

		default:
			return null;

		}

	}

}
