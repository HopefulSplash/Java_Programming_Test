import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class Java_Programming_Test {

    //total word count
    public  int word_count;
    //total number of chars
    public  int total_chars;
    //average word length
    public  float avg_word_length;
    //an array of length count (index 0 = length of 0, index 1 = how many words of length 1, index 11 = over 10)
    public  int[] word_length_count = new int[12];
    //location of file to read
    public  String file_path = "src/main/resources/Example File.txt";
    //text to process from the file
    public  String text_to_process;
    //lists of the index and the max value
    public  ArrayList<Integer> max = new ArrayList<>();
    public  ArrayList<Integer> max_index = new ArrayList<>();

    //getter for file path
    public String get_file_path() {
        return file_path;
    }

    //a function to return the string from a file
    public String get_file_as_string(String file_path) throws IOException {
        //turn the string path into a path to get the file as a string
        Path fileName = Path.of(file_path);
        text_to_process = Files.readString(fileName);
        return text_to_process;
    }

    //a function to split words according to the rule of a new word is when there is a space
    public int  process_file(String string) {
        String[] data = string.split(" ");

        //for each for we then add to the count of total words and process it for averages etc
        Arrays.stream(data).forEach(datum -> {
            add_count(1);
            process_word(datum);
        });
        return word_count;
    }

    //a function to add to work count
    public  int add_count(int add) {
        return word_count = (word_count + add);
    }

    //a function to check the total length and track the size of the word
    public  String  process_word(String word) {
        //if the word  has any punctuation we remove it
        //regex for removing the special chars and punctuation but keeping selected chars - Dependent on clients need
        //To add complexity we can use a more detailed search but checking if it's a number, date, etc. (brief said don't over complicate it :) )
        word = word.replaceAll("[\\p{Punct}&&[^-/:&$+=%#@]]+", "");

        int word_length = word.length();
        total_chars = total_chars + word_length;
        track_length(word_length);

        return word;
    }

    //a function to add to the count of words that have the same length
    public void  track_length(int word_len) {
        int temp;
        //if the length is more than 0 and less than 10
        if (word_len != 0 && word_len <= 10) {
            temp = word_len;
            word_length_count[temp]++;
        }
        //extra functionality to track words with a length hire than 10
        else if (word_len != 0) {
            word_length_count[11]++;
        }
    }

    //a function to print out the number of words length x
    public void print_word_length_count() {
        for (int i = 0; i < word_length_count.length; i++) {
            if (i < 11) {
                if (word_length_count[i] >= 1) {
                    System.out.printf("Number of words of length %d is %d \n", i, word_length_count[i]);
                }
            } else {
                if (word_length_count[i] > 0) {
                    System.out.printf("Number of words of greater than length 10 is %d \n", word_length_count[i]);
                }
            }
        }
    }

    // a function to print the most frequently occurring lengths
    public  void print_most_freq_occur() {
        int i;

        //setting the first max as the first element in the array of counts
        max.add(word_length_count[0]);

        for (i = 0; i < word_length_count.length; i++) {

            if (word_length_count[i] > max.get(0)) {
                //higher than max clear and set index 0
                max.clear();
                max_index.clear();
                max.add(word_length_count[i]);
                max_index.add(i);
            } else if (word_length_count[i] == max.get(0)) {
                //same as max then just add to the list
                max.add(word_length_count[i]);
                max_index.add(i);
            }
            //lower than max do nothing
        }

        StringBuilder most_freq_ocur_length = new StringBuilder("The most frequently occurring word length is " + max.get(0) + ", for word lengths of ");
        for (i = 0; i < max_index.size(); i++) {
            if (i == 0) most_freq_ocur_length.append(max_index.get(i));
            else {
                most_freq_ocur_length.append(" & ").append(max_index.get(i));
            }
        }

        System.out.println(most_freq_ocur_length);
    }

    // main method for testing you can overload the call for use too
    public static void main(String[] args) {
        var jpt = new Java_Programming_Test();
        jpt.run_application(jpt.get_file_path());
    }

    //overloaded main method
    public  void run_application(String user_entered_file_path) {
        // reading the file into the application as a string
        try {
            get_file_as_string(user_entered_file_path);
        } catch (IOException e) {
            System.out.println("File Path Is Not Valid, Please Try Again!");

        }

        //getting the word count in the file
        process_file(text_to_process);
        System.out.println("Word count = " + word_count);
        //calculating the average work count
        avg_word_length = ((float) total_chars) / word_count;
        System.out.printf("Average word length = %.3f\n", avg_word_length);
        //printing a list of the length of words
        print_word_length_count();
        //printing the most frequently occurring word lengths
        print_most_freq_occur();
    }
}






