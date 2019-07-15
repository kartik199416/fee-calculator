package com.pub.sapient.reader.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.pub.sapient.model.Transaction;

public class CSVTransactionReaderTest {

	@Test
	public void testReadFile() throws IOException {
		File file = new File( ClassLoader.getSystemClassLoader().getResource("inputFiles/CSVInputFile.csv").getFile());
		List<Transaction> trans = (new CSVTransactionReader()).read(file);
		assertNotNull(trans);
		assertEquals("Total transaaction are 20 ", 20, trans.size());
	}

}
