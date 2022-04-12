import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Java_Programming_Test Tester.
 */
public class Java_Programming_Test_Test {

    public Java_Programming_Test jpt = new Java_Programming_Test();
    public String test_file_path_ok = "src/main/resources/Example File.txt";
    public String test_file_path_no = "resource folder/example file";
    private static final double DELTA = 1e-15;

    /**
     * Check if the file path given is valid, and check if the string is not empty.
     * Method: get_file_as_string(String file_path)
     */
    @Test
    public void test_get_file_as_string_ok() {
        String s = "";

        //check if file can be read from file path.
        try {
            s = jpt.get_file_as_string(test_file_path_ok);
        } catch (IOException e) {
            Assert.fail("File Path ERROR: No File Was Found");
        }

        //check if the string read from the file is empty or not.
        Assert.assertFalse("File ERROR: String Is Blank", s.isBlank());
    }

    /**
     * Check if the file path given is not valid, and check if the string is empty
     * Method: get_file_as_string(String file_path)
     */
    @Test
    public void test_get_file_as_string_no() {
        String s = "";

        //check if file can be read from file path.
        try {
            s = jpt.get_file_as_string(test_file_path_no);
        } catch (IOException e) {
            Assert.assertTrue("File Path ERROR: No File Was Found", s.isBlank());
        }

        //check if the string read from the file is empty or not.
        Assert.assertTrue("File ERROR: String Is Blank", s.isBlank());
    }

    /**
     * Check if the count of different words if correct
     * Method: process_file(String)
     */
    @Test
    public void test_process_file() {
        jpt.word_count = 0;
        Assert.assertEquals(3, jpt.process_file("one two three"));
        jpt.word_count = 0;
        Assert.assertEquals(7, jpt.process_file("I am a teapot short and stout"));
        jpt.word_count = 0;
        Assert.assertEquals(4, jpt.process_file("I am ateapot shortandstout"));
    }

    /**
     * Check if the add to word count method works correctly
     * Method: add_count(int a)
     */
    @Test
    public void test_add_count() {
        jpt.word_count = 0;
        Assert.assertEquals(2, jpt.add_count(2));
        jpt.word_count = 0;
        Assert.assertNotEquals(4, jpt.add_count(2));
    }

    /**
     * Check that the regex doesn't change a majority of the inputs it could take (Can Be Improved)
     * Method: process_word(String word)
     */
    @Test
    public void test_process_word() {
        Assert.assertEquals("im a little Tea pot & stuff", jpt.process_word("i'm a little T,.ea p!ot & stuff?"));
        Assert.assertEquals("15/03/2022", jpt.process_word("15/03/2022"));
        Assert.assertEquals("15:03:2022", jpt.process_word("15:03:2022"));
        Assert.assertEquals("15-03-2022", jpt.process_word("15-03-2022"));
        Assert.assertEquals("$109", jpt.process_word("$109"));
        Assert.assertEquals("-/:&$+=%#@", jpt.process_word("-/:&$+=%#@"));
    }

    /**
     * Checks that the tracking array accepts and displays the correct values and for null pointers
     * Method: track_length(int word_len)
     */
    @Test
    public void test_track_length() {
        jpt.word_length_count = new int[12];
        jpt.track_length(1);
        jpt.track_length(1);
        jpt.track_length(1);
        jpt.track_length(3);
        jpt.track_length(3);
        jpt.track_length(3);
        jpt.track_length(3);
        jpt.track_length(3);
        jpt.track_length(5);
        jpt.track_length(7);
        jpt.track_length(7);
        jpt.track_length(7);
        jpt.track_length(7);
        jpt.track_length(9);
        jpt.track_length(9);
        jpt.track_length(11);
        jpt.track_length(12);
        jpt.track_length(13);

        Assert.assertEquals(3, jpt.word_length_count[1]);
        Assert.assertEquals(5, jpt.word_length_count[3]);
        Assert.assertEquals(1, jpt.word_length_count[5]);
        Assert.assertEquals(4, jpt.word_length_count[7]);
        Assert.assertEquals(2, jpt.word_length_count[9]);
        Assert.assertEquals(3, jpt.word_length_count[11]);

        Assert.assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> {
                    int test_val = jpt.word_length_count[12];
                    System.out.println(test_val);
                });
    }

    /**
     * Checks the code works and the results against predefined values
     * Method: run_application(String user_entered_file_path)
     */
    @Test
    public void test_run_application_user_entered_file_path() {
        jpt.run_application(test_file_path_ok);
        Assert.assertEquals(9, jpt.word_count);
        //full value before rounding for print
        Assert.assertEquals(4.55555534362793, jpt.avg_word_length, DELTA);
        Assert.assertEquals(1, jpt.word_length_count[1]);
        Assert.assertEquals(1, jpt.word_length_count[2]);
        Assert.assertEquals(1, jpt.word_length_count[3]);
        Assert.assertEquals(2, jpt.word_length_count[4]);
        Assert.assertEquals(2, jpt.word_length_count[5]);
        Assert.assertEquals(1, jpt.word_length_count[7]);
        Assert.assertEquals(1, jpt.word_length_count[10]);
        int[] test_max = {2, 2};
        Assert.assertEquals(test_max[0], (int) jpt.max.get(0));
        int[] test_index = {4, 5};
        Assert.assertEquals(test_index[0], (int) jpt.max_index.get(0));
        Assert.assertEquals(test_index[1], (int) jpt.max_index.get(1));
    }
}
