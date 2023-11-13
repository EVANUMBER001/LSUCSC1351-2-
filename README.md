Movie Library Merger
This Java program, Prog02_MergeIt, is designed to merge two unordered movie collections into a single ordered movie library. The program takes as input two text files, each containing movie information in the format "A: Add" or "D: Delete," followed by movie details: title, year, and review.

How to Run
Compile the Java program:

bash
Copy code
javac Prog02_MergeIt.java
Run the compiled program:

bash
Copy code
java Prog02_MergeIt
Follow the prompts to enter the filenames for the two movie collections and the output file.

Input File Format
Each line in the input files should follow the format:

For adding a movie: A,Title,Year,Review
For deleting a movie: D,Title,Year,Review
Example:

css
Copy code
A,Spiderman,2018,PG-4
D,Spiderman,2018,PG-4
A,Inception,2010,PG-13
...
Output
The merged list of movies is written to the specified output file in alphabetical order by title.

Code Structure
Prog02_MergeIt.java: Main program containing the main method.
Movie.java: Class representing a movie with title, year, and review.
Functions:
getFileNameFromUser: Prompts the user for a filename with input validation.
readDataFromFile: Reads movie data from a file and populates an ordered ArrayList.
addMovieToOrderedArrayList: Adds a movie to an ordered ArrayList.
mergeCollections: Merges two ordered ArrayLists into a single ordered array.
writeMergedArrayToFile: Writes the merged array to the output file.
