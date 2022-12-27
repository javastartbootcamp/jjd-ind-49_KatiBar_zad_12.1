import java.io.FileNotFoundException;
import java.io.IOException;

class Main {
    public static void main(String[] args) {
        String fileName = "operations.txt";
        FileUtility fileUtility = new FileUtility();
        try {
            String[] lines = fileUtility.readFile(fileName);
            double[] results = fileUtility.calculateResults(lines);
            fileUtility.saveResults(lines, fileName, results);
        } catch (FileNotFoundException e) {
            System.err.println("Brak pliku o nazwie: " + fileName);;
        } catch (IOException e) {
            System.err.println("Nie udało się dopisać nowych danych do pliku: " + fileName);
        } catch (WrongOptionException e) {
            System.err.println("Z pliku: " + fileName + " wczytano błędną opcję do kalkulatora wyników");
        } catch (NumberFormatException e) {
            System.err.println("W pliku: " + fileName + " podano zły format danych do kalkulatora, liczby powinny " +
                    "być typu double lub int");
        }
    }
}