package com.pub.sapient;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.pub.sapient.fee.FeeCalculator;
import com.pub.sapient.model.FileType;
import com.pub.sapient.model.Transaction;
import com.pub.sapient.reader.ITransactionReader;
import com.pub.sapient.reader.factory.TransactionReaderFactory;

public class SapeFeeCalcKartikMain {

	private static final Logger LOGGER = Logger.getLogger(SapeFeeCalcKartikMain.class.getName());
	private static List<Transaction> transactionList;
	private static FeeCalculator feeCalculator;

	public static void main(String[] args) throws Exception {

		LOGGER.info("Reading file from classpath input folder");
		Set<Path> filePaths = getResourceFolderFiles("inputFiles");

		if (filePaths != null && !filePaths.isEmpty()) {
			LOGGER.info("Total files loaded" + filePaths.size());
			transactionList = new ArrayList<>();
			try {
				for ( Path fpath : filePaths) {
					File f = new File(fpath.toUri());
					LOGGER.info("Reading File --> " + f.getName() + "separtor : " + File.separator);
					String fileExtension = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf(".") + 1);
					FileType fileType = FileType.valueOf(fileExtension.toUpperCase());
					ITransactionReader reader = TransactionReaderFactory.getTransactionReader(fileType);
					if (reader != null) {
						transactionList.addAll(reader.read(f));
					} else {
						LOGGER.info("No Reader found for given file type" + fileType);
					}
				}
			} catch (Exception e) {
				LOGGER.warning("Error ocurred while fetching file : " + e.getMessage());
			}
		}
		if (transactionList != null) {
			feeCalculator = new FeeCalculator();
			feeCalculator.addTransaction(transactionList);
			feeCalculator.displayTransactionReport();
		} else {
			LOGGER.info("No transaction being uploaded");
		}
	}

	private static Set<Path> getResourceFolderFiles(String folder) throws Exception {

		// ClassLoader loader = Thread.currentThread().getContextClassLoader();
		// URL url = loader.getResource(folder);
		File directory = new File(ClassLoader.getSystemClassLoader().getResource(folder).getFile());
		return Files.list(directory.toPath()).collect(Collectors.toSet());
		/*
		 * String path = url.getPath(); return new File(path).listFiles();
		 */
	}

	// private static File[] getFolderFiles(String folder) throws IOException,
	// URISyntaxException {
	// Path path = Paths.get(uri);
	//
	// String path = url.getPath();
	// return new File(path).listFiles();
	// }
}
