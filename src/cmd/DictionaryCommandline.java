package cmd;

import java.util.Scanner;

public class DictionaryCommandline {
    private Dictionary dictionary = new Dictionary();

    public void dictionaryAdvanced() {
        Scanner input = new Scanner(System.in);
        System.out.println("Loading dictionaryData from dictionaries.txt");
        DictionaryManagement start = new DictionaryManagement(this.dictionary);
        start.insertFromFile("resource/dictionaries/dictionaries.txt");
        while(true) {
            int option;
            System.out.println("Type in option you want(1-addWord, 2-removeWord, 3-editWord, 4-searchWord, 5-exportToFile, 6-showWord): ");
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1: {
                    System.out.println("Enter the new word: ");
                    String target = input.nextLine();
                    System.out.println("Enter the meaning: ");
                    String meaning = input.nextLine();
                    this.dictionary.addWord(new Word(target, meaning));
                    break;
                }
                case 2: {
                    System.out.println("Enter the word you want to remove: ");
                    String target = input.nextLine();
                    (new DictionaryManagement(this.dictionary)).removeWordInDictionary(target);
                    break;
                }
                case 3: {
                    System.out.println("Enter the word you want to edit: ");
                    String target = input.nextLine();
                    (new DictionaryManagement(this.dictionary)).editWordInDictionary(target);
                    break;
                }
                case 4: {
                    System.out.println("Enter the word you want to search: ");
                    String target = input.nextLine();
                    (new DictionaryManagement(this.dictionary)).dictionarySearcher(target);
                    break;
                }
                case 5: {
                    System.out.println("Exporting data to dictionaries.txt!");
                    (new DictionaryManagement(this.dictionary)).dictionaryExportToFile(this.dictionary.getWordList());
                    break;
                }
                case 6: {
                    new DictionaryManagement(this.dictionary).showAllWords();
                    break;
                }
                default: {
                    System.out.println("Type in option 1 - 6");
                }

            }
        }

    }

    public static void main(String[] args) {
        DictionaryCommandline dictionaryCommandline= new DictionaryCommandline();
        dictionaryCommandline.dictionaryAdvanced();
    }

}
