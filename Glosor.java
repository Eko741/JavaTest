

/*
	public static void createNewSet() {
		input = new Scanner(System.in);
		
		int maxSetSize = 127;
		String[] SpellingSE = new String[maxSetSize];
		String[] SpellingDE = new String[maxSetSize];
		for (int i = 0; i < maxSetSize; i++) {
			SpellingSE[i] = "";
			SpellingDE[i] = "";
		}
		wordSet tempWordSet = new wordSet(SpellingSE, SpellingDE, 0);
		String tempWord;
		int arrayLength = 0;
		for (int i = 0, j = 0; i < 127 && j != 1; i++) {
			tempWordArray[i] = new word("", "", 0);
			System.out.println("Ord " + (i + 1) + ": Svenska");
			tempWord = input.nextLine();
			if (tempWord.equals("END")) {
				j = 1;

			} else {
				tempWordArray[i].spellingSE = tempWord;
				System.out.println("Ord " + (i + 1) + ": Tyska");

				tempWordArray[i].spellingDE = input.nextLine();
			}
			arrayLength++;
		}
		saveWordData(tempWordArray, arrayLength, "test.txt");

	}



	static void saveWordData(WordSet tempWordSet, int arrayLength, String filePath) {

		String tempFile = "temp.txt";
		File oldFile = new File(filePath);
		File newFile = new File(tempFile);

		try {
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			x = new Scanner(new File(filePath));
			x.useDelimiter(",|\r\n");
			char NOT = x.next().charAt(0);
			String currentString;
			int NOTInt = Character.getNumericValue(NOT);
			pw.println(NOTInt + 1);

			while (x.hasNext()) {
				if (x.hasNextInt()) {
					currentString = x.next();
					if (currentString.charAt(2) == NOT + 1) {
						int currentInt = Integer.parseInt(currentString);
						if (currentInt / 100 == 0) {
							pw.print("00" + (currentInt + 1));
						}

						for (int i = 0; i < arrayLength - 1; i++) {
							pw.print("," + tempWordSet.spellingSE[i]);
						}
						pw.println();
						pw.print("00" + (currentInt + 2));
						for (int i = 0; i < arrayLength - 1; i++) {
							pw.print("," + tempWordSet.spellingDE[i]);
						}
						pw.println();
						pw.println("00" + (currentInt + 10));

					} else {
						pw.print(currentString + ",");

					}

				} else {
					pw.print(x.next());
					if (x.hasNextInt()) {
						pw.println();
					} else {

						pw.print(",");

					}

				}

			}

			x.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(filePath);
			newFile.renameTo(dump);
			System.out.println("Save Succsesful");
		} catch (

		Exception e) {
			System.out.println(e);

		}

	}	
*/