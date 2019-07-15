package com.pub.sapient.reader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.pub.sapient.model.Transaction;

public interface ITransactionReader {
	
	List<Transaction> read(File file) throws IOException;

}
