// Import necessary Java libraries
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

// Define the main class
public class Prog02_MergeIt {

    // Main method
    public static void main(String[] args) {
        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Get filenames from the user
        String filename1 = getFileNameFromUser(scanner, "Enter the first filename: ");
        String filename2 = getFileNameFromUser(scanner, "Enter the second filename: ");

        // Create two ordered ArrayLists for each movie collection
        ArrayList<Movie> collection1 = new ArrayList<>();
        ArrayList<Movie> collection2 = new ArrayList<>();

        // Read data from the first file and populate the first ordered ArrayList
        readDataFromFile(filename1, collection1);

        // Read data from the second file and populate the second ordered ArrayList
        readDataFromFile(filename2, collection2);

        // Merge the two collections into a single ordered array of Movies
        ArrayList<Movie> mergedArray = mergeCollections(collection1, collection2);

        // Get the output filename from the user
        String outputFilename = getFileNameFromUser(scanner, "Enter the output filename: ");

        // Output the merged array to the specified file
        writeMergedArrayToFile(mergedArray, outputFilename);
    }

    // Method to get a filename from the user
    private static String getFileNameFromUser(Scanner scanner, String message) {
        // Prompt the user for a filename and perform basic input validation
        String filename;
        do {
            System.out.print(message);
            filename = scanner.nextLine().trim();
        } while (filename.isEmpty());
        return filename;
    }

    // Method to read data from a file and populate an ArrayList
    private static void readDataFromFile(String filename, ArrayList<Movie> collection) {
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println("Read line: " + line); // Add this line for debugging
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String operation = parts[0].trim();
                    String title = parts[1].trim();
                    int year = Integer.parseInt(parts[2].trim());
                    String review = parts[3].trim();

                    if (operation.equalsIgnoreCase("A")) {
                        Movie movie = new Movie(title, year, review);
                        System.out.println("Adding movie: " + movie); // Add this line for debugging
                        addMovieToOrderedArrayList(collection, movie);
                    } else if (operation.equalsIgnoreCase("D")) {
                        // Implement deletion logic if needed
                    }
                }
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            System.out.println("Error reading from the file: " + e.getMessage());
        }
    }

    // Method to add a movie to an ordered ArrayList
    private static void addMovieToOrderedArrayList(ArrayList<Movie> collection, Movie movie) {
        int index = 0;
        while (index < collection.size() && collection.get(index).getTitle().compareTo(movie.getTitle()) < 0) {
            index++;
        }
        collection.add(index, movie);
    }

    // Method to merge two collections into a single ordered ArrayList
    private static ArrayList<Movie> mergeCollections(ArrayList<Movie> collection1, ArrayList<Movie> collection2) {
        ArrayList<Movie> mergedArray = new ArrayList<>();
        int index1 = 0, index2 = 0;

        while (index1 < collection1.size() && index2 < collection2.size()) {
            Movie movie1 = collection1.get(index1);
            Movie movie2 = collection2.get(index2);

            if (movie1.getTitle().compareTo(movie2.getTitle()) <= 0) {
                mergedArray.add(movie1);
                index1++;
            } else {
                mergedArray.add(movie2);
                index2++;
            }
        }

        while (index1 < collection1.size()) {
            mergedArray.add(collection1.get(index1));
            index1++;
        }

        while (index2 < collection2.size()) {
            mergedArray.add(collection2.get(index2));
            index2++;
        }

        return mergedArray;
    }

    // Method to write a merged array to a file
    private static void writeMergedArrayToFile(ArrayList<Movie> mergedArray, String outputFilename) {
        try (PrintWriter writer = new PrintWriter(outputFilename)) {
            for (Movie movie : mergedArray) {
                writer.println("A," + movie.toString()); // Format as "A: Add"
            }
            System.out.println("Merged Array successfully written to the file.");
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }
}

// Movie class
class Movie {
    private String title;
    private int year;
    private String review;

    // Constructor for Movie class
    public Movie(String title, int year, String review) {
        this.title = title;
        this.year = year;
        this.review = review;
    }

    // Getter method for the title
    public String getTitle() {
        return title;
    }

    // Getter method for the year
    public int getYear() {
        return year;
    }

    // Getter method for the review
    public String getReview() {
        return review;
    }

    // Override toString method to provide a string representation of the Movie
    @Override
    public String toString() {
        return "Title: " + title + ", Year: " + year + ", Review: " + review;
    }
}
