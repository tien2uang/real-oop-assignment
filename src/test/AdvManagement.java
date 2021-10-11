package test;

import cmd.Dictionary;
import cmd.Word;
import cmd.DictionaryManagement;
import cmd.DictionaryCommandline;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;



public class AdvManagement {
    private static int SINGLE_SPELLINGS = 2;
    private static int MULTIPLE_SPELLINGS = 4;
    private Dictionary dictionaryData;

    public AdvManagement(Dictionary dictionaryData) {
        this.dictionaryData = dictionaryData;
    }

    public void insertFromFile(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (checkTypeOfLine(line) == SINGLE_SPELLINGS) {
                    StringTokenizer stringTokenizer = new StringTokenizer(line, "/");
                    String target = stringTokenizer.nextToken().trim();
                    String spellings = "/" + stringTokenizer.nextToken() + "/";
                    String temp = stringTokenizer.nextToken().trim();
                    stringTokenizer = new StringTokenizer(temp, "*");
                    String temp1 = stringTokenizer.nextToken();
                    stringTokenizer = new StringTokenizer(temp1, "-");
                    String wordClass = "*  " + stringTokenizer.nextToken().trim();
                    String explain = "";
                    while (stringTokenizer.hasMoreTokens()) {
                        String text = stringTokenizer.nextToken();
                        if (text.contains("=")) {
                            StringTokenizer tempStringTokenizer = new StringTokenizer(text, "=");
                            explain += "-" + tempStringTokenizer.nextToken() + "\n";
                            while (tempStringTokenizer.hasMoreTokens()) {
                                String similar = tempStringTokenizer.nextToken().trim();
                                explain += "  = " + similar + "\n";

                            }
                        } else {
                            explain += "-" + text + "\n";
                        }
                    }
                    ArrayList<String>spellingsArray= new ArrayList<>();
                    spellingsArray.add(spellings);


                } else if (checkTypeOfLine(line) == MULTIPLE_SPELLINGS) {

                    StringTokenizer stringTokenizer = new StringTokenizer(line, "/");
                    String target = stringTokenizer.nextToken().trim();
                    String firstSpellings = "/" + stringTokenizer.nextToken() + "/";
                    String tmp=stringTokenizer.nextToken().trim();
                    String tmp1="/" + stringTokenizer.nextToken() + "/";
                    String secondSpellings=tmp1+"  "+tmp;
                    String temp = stringTokenizer.nextToken().trim();
                    stringTokenizer = new StringTokenizer(temp, "*");
                    String temp1 = stringTokenizer.nextToken();
                    stringTokenizer = new StringTokenizer(temp1.trim(), "\t");
                    String wordClass = "* "+  stringTokenizer.nextToken().trim();
                    String explain = "";
                    while (stringTokenizer.hasMoreTokens()) {
                        String text = stringTokenizer.nextToken();
                        if (text.contains("=")) {
                            explain+="  "+text+"\n";
                        } else {
                            explain +=  text + "\n";
                        }
                    }
                    ArrayList<String>spellingsArray= new ArrayList<>();
                    spellingsArray.add(firstSpellings);
                    spellingsArray.add(secondSpellings);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file database");
            e.printStackTrace();
        }

    }

    private static int checkTypeOfLine(String line) {
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '/') {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        String line ="abranchiate\t/ə'bræɳkiəl/ (abranchiate)\t/ə'bræɳkiit/\t*  tính từ\t- (động vật học) không mang";

        if (checkTypeOfLine(line) == SINGLE_SPELLINGS) {
            StringTokenizer stringTokenizer = new StringTokenizer(line, "/");
            String target = stringTokenizer.nextToken().trim();
            String spellings = "/" + stringTokenizer.nextToken() + "/";
            String temp = stringTokenizer.nextToken().trim();
            stringTokenizer = new StringTokenizer(temp, "*");
            String temp1 = stringTokenizer.nextToken();
            stringTokenizer = new StringTokenizer(temp1.trim(), "\t");
            String wordClass = "* "+  stringTokenizer.nextToken().trim();
            String explain = "";
            while (stringTokenizer.hasMoreTokens()) {
                String text = stringTokenizer.nextToken();
                if (text.contains("=")) {
                    explain+="  "+text+"\n";
                } else {
                    explain +=  text + "\n";
                }
            }
            System.out.println(target);
            System.out.println(spellings);
            System.out.println(wordClass);
            System.out.println(explain);


        } else if (checkTypeOfLine(line) == MULTIPLE_SPELLINGS) {
            StringTokenizer stringTokenizer = new StringTokenizer(line, "/");
            String target = stringTokenizer.nextToken().trim();
            String firstSpellings = "/" + stringTokenizer.nextToken() + "/";
            String tmp=stringTokenizer.nextToken().trim();
            String tmp1="/" + stringTokenizer.nextToken() + "/";
            String secondSpellings=tmp1+" "+tmp;
            String temp = stringTokenizer.nextToken().trim();
            stringTokenizer = new StringTokenizer(temp, "*");
            String temp1 = stringTokenizer.nextToken();
            stringTokenizer = new StringTokenizer(temp1.trim(), "\t");
            String wordClass = "* "+  stringTokenizer.nextToken().trim();
            String explain = "";
            while (stringTokenizer.hasMoreTokens()) {
                String text = stringTokenizer.nextToken();
                if (text.contains("=")) {
                    explain+="  "+text+"\n";
                } else {
                    explain +=  text + "\n";
                }
            }
            System.out.println(target);
            System.out.println(firstSpellings);
            System.out.println(secondSpellings);
            System.out.println(wordClass);
            System.out.println(explain);

        }




    }
}

