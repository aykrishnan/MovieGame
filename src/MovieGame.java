import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileNotFoundException;
public class MovieGame {
    public static void main(String[]args){
        String[] movieTitles = movieArray();
        String selectedMovie = randomChoice(movieTitles);
        guessingTheMovie(selectedMovie);

    }//Storing movies in an array.
    private static  String[] movieArray(){
        File file = new File("movies.txt");
        Scanner inp = null;
        try{
             inp = new Scanner(file);
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());

        }finally {
            ArrayList<String> moviesFromFile = readMoviesFromFile(inp);//Create a readMovies method and read movies.
            String[] movieNames = moviesFromFile.toArray(new String[0]);//Copy the movie names in an array.
        return movieNames;
        }

    }
//Read movies from the file method.
    private static ArrayList<String> readMoviesFromFile(Scanner inp) {
        ArrayList<String> movies = new ArrayList<String>();
        while (inp.hasNextLine()){
            movies.add(inp.nextLine());
        }
        return movies;
    }
    //Random movie Generator
    private static String randomChoice(String[] movieTitles){
        int randomIndex = movieTitles.length;
        Random rand = new Random();
        int selectedMovieIndex = rand.nextInt(randomIndex);
        return movieTitles[selectedMovieIndex];
    }
    //Guessing the movie and changing it to underscore.
    private static void guessingTheMovie(String selectedMovie){
        int numOfWrongGuesses = 0;
        int flag = 0;
        String maskedTitle = selectedMovie.replaceAll(".", "_");
        char[] maskCharArray = maskedTitle.toCharArray();
        while (numOfWrongGuesses<10){
            System.out.println("You are guessing: "+maskedTitle);
            System.out.println("You have guessed ("+numOfWrongGuesses+") wrong letters:");
            System.out.println("Guess a letter: ");
            Scanner input = new Scanner(System.in);
            //Convert the guessing to a char by char
            char userGuessedChar = input.nextLine().charAt(0);
            //Scan through the movie title to find a match of the guessed char
            for(int i =0;i<selectedMovie.length();i++){
                if(selectedMovie.charAt(i)==userGuessedChar){
                    maskCharArray[i]=userGuessedChar;
                    flag++;
                }
            }
            maskedTitle = String.valueOf(maskCharArray);
            if(flag==0){
                numOfWrongGuesses++;
            }else{
                flag = 0;
                if(maskedTitle.contains("_")){
                    continue;
                }else{
                    break;
                }
            }
        }
        if(numOfWrongGuesses<10){
            System.out.println("You Win!");
            System.out.println("You have guessed '"+selectedMovie+"' correctly.");
        }
        else{
            System.out.println("Sorry you have lost.");
            System.out.println("The correct movie title is '"+selectedMovie+"'.");
        }
    }
}
