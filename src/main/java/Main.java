import java.io.FileNotFoundException;
import java.io.IOException;

class Main {
    public static void main(String[] args) {
        String fileName = "operations.txt";
        FileUtility fileUtility = new FileUtility();
        try {
            fileUtility.readFile(fileName);
            fileUtility.printResults();
            fileUtility.saveResults(fileName);
        } catch (FileNotFoundException e) {
            System.err.println("Brak pliku o nazwie: " + fileName);;
        } catch (IOException e) {
            System.err.println("Nie udało się dopisać nowych danych do pliku: " + fileName);
        }
    }
}