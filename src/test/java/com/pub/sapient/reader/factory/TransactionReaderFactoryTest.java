package com.pub.sapient.reader.factory;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.pub.sapient.model.FileType;
import com.pub.sapient.reader.ITransactionReader;

public class TransactionReaderFactoryTest {

	@Test
	public void testGetTransactionReaderForCSV() {

		File file = new File(ClassLoader.getSystemClassLoader().getResource("inputFiles/CSVInputFile.csv").getFile());
		file.getPath();
		String fileExtension = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1);
		FileType fileType = FileType.valueOf(fileExtension.toUpperCase());
		ITransactionReader reader = TransactionReaderFactory.getTransactionReader(fileType);
		assertNotNull(reader);
	}
	
	@Test
	public void testGetTransactionReaderForXML() {

		ITransactionReader reader = TransactionReaderFactory.getTransactionReader(FileType.XML);
		assertNotNull(reader);
	}

}
