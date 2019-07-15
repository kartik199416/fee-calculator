package com.pub.sapient.reader.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pub.sapient.model.Transaction;
import com.pub.sapient.reader.ITransactionReader;
import com.pub.sapient.utils.Utility;

public class TEXTTransactionReader implements ITransactionReader {

	@Override
	public List<Transaction> read(File file) throws IOException {

		List<Transaction> list = new ArrayList<>();
		String line = "";
		String delimiter = "|";
		

		try (BufferedReader br = new BufferedReader(new FileReader(file));) {
			br.readLine();//ignoreHeader
			while ((line = br.readLine()) != null) {
				String[] transactionAttributes = line.split(delimiter);
				Transaction transaction = Utility.getTransaction(transactionAttributes);
				list.add(transaction);
			}
		}

		return list;
	}

}
