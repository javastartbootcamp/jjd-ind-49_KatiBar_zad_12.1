import java.io.*;
import java.util.Scanner;

public class FileUtility {
    public String[] readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        try ( Scanner scanner = new Scanner(file)) {
            int lines = countLines(fileName);
            String[] wordsInLine = new String[lines];
            for (int i = 0; i < lines; i++) {
                wordsInLine[i] = scanner.nextLine();
            }
            return wordsInLine;
        }
    }

    public double[] calculateResults(String[] lines) throws NumberFormatException {
        double[] results = new double[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] split = lines[i].split(" ");
            double number1 = Double.parseDouble(split[0]);
            String option = split[1];
            double number2 = Double.parseDouble(split[2]);
            double result = calculateResultFromOption(number1, option, number2);
            results[i] = result;
            printResult(number1, option, number2, result);
        }
        return results;
    }

    private static void printResult(double number1, String option, double number2, double result) {
        String line = number1 + " " + option + " " + number2;
        System.out.println(line + " = " + result);
    }

    public void saveResults(String[] lines, String fileName, double[] results) throws IOException {
        try (
                var fileWriter = new FileWriter(fileName);
                var bufferedWriter = new BufferedWriter(fileWriter);
                ) {
            for (int i = 0; i < results.length; i++) {
                bufferedWriter.write(lines[i] + " = " + results[i]);
                bufferedWriter.newLine();
            }
        }
    }

    private static double calculateResultFromOption(double number1, String option, double number2) {
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
        try (Scanner scanner = new Scanner(file)) {
            int lines = 0;
            while (scanner.hasNextLine()) {
                lines++;
                scanner.nextLine();
            }
            return lines;
        }
    }
}

