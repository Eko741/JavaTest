import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.util.Scanner;

//File IO stuff that makes the code look bad

public class FileIO {

	private static Scanner x;
	public static PrintWriter pw;

	public static String[] getTextFileList(String dir) {
		File[] tempFileList = FileIO.fileFinder("tests");

		String[] fileList = new String[tempFileList.length];

		for (int i = 0; i < tempFileList.length; i++) {
			fileList[i] = tempFileList[i].getName();

		}

		return (fileList);
	}

	public static File[] fileFinder(String dirName) {
		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".txt");
			}
		});

	}

	public static WordSet loadTest(int set, String filePath) {

		String[][] tempWordSet = new String[UI.maxSetSize][UI.maxSetSize];
		String[][] finalWordSet;
		int attributeIndex = 0;
		int wordIndex = 0;

		try {

			x = new Scanner(new File(filePath));
			x.useDelimiter(",|\r\n");
			String tempWord = findSet(x, set);

			while (compareSetnumberToListIndex(set, tempWord)) {
				wordIndex = 0;
				while (x.hasNext()) {

					tempWord = x.next();

					if (tempWord.equals("nl")) {
						attributeIndex++;
						tempWord = x.next();
						break;
					}
					tempWordSet[wordIndex][attributeIndex] = tempWord;
					wordIndex++;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		finalWordSet = new String[wordIndex][attributeIndex];

		for (int i = 0; i < attributeIndex; i++) {
			for (int j = 0; j < wordIndex; j++) {

				finalWordSet[j][i] = tempWordSet[j][i];

			}
		}

		return new WordSet(finalWordSet);
	}

	public static String[] getSets(String filePath) {

		try {
			x = new Scanner(new File(filePath));
			x.useDelimiter(",|\r\n");

		} catch (FileNotFoundException e) {

			e.printStackTrace();
			String[] tempString = { "ERROR" };
			return tempString;
		}

		int NOS = x.nextInt();

		String[] setList = new String[NOS];

		String tempString = x.next(); // Skips first number in document

		for (int i = 0; i < NOS; i++) {

			tempString = x.next();

			setList[i] = String.valueOf(i + 1) + ": ";

			while (!tempString.equals("nl")) {
				setList[i] += tempString + ",";

				tempString = x.next();
			}

			while (x.hasNext()) {
				if (x.hasNextInt()) {
					tempString = x.next();
					if (compareSetnumberToListIndex(i + 2, tempString)) {
						break;
					}
				}
				x.next();
			}
		}

		/*
		 * while (!x.hasNextInt()) { tempString += x.next() + ", "; }
		 * 
		 * setList[i] = tempString; tempString = "";
		 * 
		 * x.next();
		 * 
		 * while (!x.hasNextInt()) { x.next(); } x.next(); }
		 */
		
		return setList;

	}

	public static boolean fileExists(String filePath) {
		File f = new File(filePath);

		return f.exists() && !f.isDirectory();

	}

	public static void saveWordData(String filePath, WordSet wordSet) {

		String tempFile = "temp.txt";

		File oldFile = new File(filePath);
		File newFile = new File(tempFile);

		try {
			// Sets up file writer
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);

			// Sets up file reader
			x = new Scanner(new File(filePath));
			x.useDelimiter(",|\r\n");

		} catch (Exception e) {
			System.out.print(e);
		}

		int NOS = x.nextInt();
		pw.println(NOS + 1);

		boolean newLine = true;
		while (x.hasNext()) {

			String tempWord = x.next();

			if (newLine) {
				newLine = false;
				if (compareSetnumberToListIndex(NOS + 1, tempWord)) {
					break;
				}

			}

			if (tempWord.equals("nl")) {
				pw.println("nl");
				newLine = true;

			} else {
				pw.print(tempWord + ",");
			}
		}
		printWordSetData(wordSet, NOS + 1);

		pw.print(setToString(NOS + 2) + "0");

		x.close();
		pw.flush();
		pw.close();
		oldFile.delete();
		File dump = new File(filePath);
		newFile.renameTo(dump);
		System.out.println("Save Succsesful");
	}

	public static String findSet(Scanner x, int setToFind) {
		x.next();
		while (x.hasNext()) {

			if (x.hasNextInt()) {
				String tempString = x.next();

				if (compareSetnumberToListIndex(setToFind, tempString)) {
					return tempString;
				}
			}
			x.next();

		}
		return "ERROR";
	}

	public static boolean compareSetnumberToListIndex(int set, String listIndex) {
		if (setToString(set).equals(listIndex.substring(0, 3))) {
			return true;
		}

		return false;
	}

	public static String setToString(int set) {
		return String.valueOf(set / 100 + "" + set / 10 + "" + set % 10);

	}

	public static void printWordSetData(WordSet wordSet, int set) {
		for (int i = 0; i < wordSet.NOA; i++) {
			pw.print(setToString(set) + (i + 1) + ",");
			for (int j = 0; j < wordSet.NOW; j++) {
				pw.print(wordSet.wordSet[i][j] + ",");
			}
			pw.println("nl");
		}

	}

}
