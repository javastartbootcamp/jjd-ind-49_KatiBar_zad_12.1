import java.io.*;
import java.util.Scanner;

public class FileUtility {
    private double[] results;
    private String[] wordsInLine;

    public void readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        int lines = countLines(fileName);
        wordsInLine = new String[lines];
        for (int i = 0; i < lines; i++) {
            wordsInLine[i] = scanner.nextLine();
        }
    }

    public void printResults() throws NumberFormatException {
        results = new double[wordsInLine.length];
        for (int i = 0; i < wordsInLine.length; i++) {
            String[] split = wordsInLine[i].split(" ");
            double number1 = Double.parseDouble(split[0]);
            String option = split[1];
            double number2 = Double.parseDouble(split[2]);
            double result = calculateResult(number1, option, number2);
            results[i] = result;
            String line = number1 + " " + option + " " + number2;
            System.out.println(line + " = " + result);
        }
    }

    public void saveResults(String fileName) throws IOException {
        try (
                var fileWriter = new FileWriter(fileName);
                var bufferedWriter = new BufferedWriter(fileWriter);
                ) {
            for (int i = 0; i < results.length; i++) {
                bufferedWriter.write(wordsInLine[i] + " = " + results[i]);
                bufferedWriter.newLine();
            }
        }
    }

    private static double calculateResult(double number1, String option, double number2) {
        double result = 0;
        switch (option) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                result = number1 / number2;
                break;
            default:
                throw new WrongOptionException();
        }
        return result;
    }

    private static int countLines(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        int lines = 0;
        while (scanner.hasNextLine()) {
            lines++;
            scanner.nextLine();
        }
        return lines;
    }
}

