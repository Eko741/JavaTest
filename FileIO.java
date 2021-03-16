import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Scanner;

//File IO stuff that makes the code look bad

public class FileIO {

	private static Scanner x;

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

		int maxSetSize = 127;
		String[] SpellingSE = new String[maxSetSize];
		String[] SpellingDE = new String[maxSetSize];
		for (int i = 0; i < maxSetSize; i++) {
			SpellingSE[i] = "";
			SpellingDE[i] = "";
		}
		WordSet tempWordSet = new WordSet(SpellingSE, SpellingDE, 0);

		try {
			x = new Scanner(new File(filePath));
			x.useDelimiter(",|\r\n");

			while (x.hasNext()) {
				if (x.next().equals("00" + set + "1")) {
					break;
				}
			}
			int i = 0;
			while (x.hasNext()) {
				if (x.hasNextInt()) {
					x.next();
					break;
				} else {
					tempWordSet.wordsSE[i] = x.next();

				}
				i++;
			}
			tempWordSet.setSize = i;
			i = 0;
			while (!x.hasNextInt()) {
				tempWordSet.wordsDE[i] = x.next();

				i++;

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return (tempWordSet);
	}

	public static String[] getSets(String filePath) {

		try {
			x = new Scanner(new File(filePath));
			x.useDelimiter(",|\r\n");

			int NOS = x.nextInt();

			String[] setList = new String[NOS];

			x.next();

			for (int i = 0; i < NOS; i++) {

				String tempString = (i + 1) + ": ";

				while (!x.hasNextInt()) {
					tempString += x.next() + ", ";
				}

				setList[i] = tempString;
				tempString = "";

				x.next();

				while (!x.hasNextInt()) {
					x.next();
				}
				x.next();
			}
			return setList;

		} catch (FileNotFoundException e) {

			e.printStackTrace();
			String[] tempString = { "ERROR" };
			return tempString;
		}

	}

	public static boolean fileExists(String filePath) {
		File f = new File(filePath);
		
		return f.exists() && !f.isDirectory();

	}

}
