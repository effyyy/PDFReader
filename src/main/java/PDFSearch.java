
import com.asprise.ocr.Ocr;
import java.io.File;
import java.util.*;

public class PDFSearch {

    /**
     * This function returns a hashtable with all words in input string as keys and their frequencies as values.
     * This method also handles the NullPointerException.
     * @param fullString This is the Entire string which is recognised by the OCR
     * @return Hashtable with words and their frequencies
     */
    public static Hashtable<String, Integer> getHashtable(String fullString){
        Hashtable<String,Integer> hashtable = new Hashtable<>();
        try {
            StringTokenizer st = new StringTokenizer(fullString);
            while(st.hasMoreTokens()){
                String test = st.nextToken();
                if(hashtable.containsKey(test)){
                    int a = hashtable.get(test);
                    a = a + 1;
                    hashtable.put(test,a);
                }else{
                    hashtable.put(test,1);
                }
            }
        }catch (NullPointerException ex){
            System.out.println("Please re-enter file name and make sure file has readable content");
            String newString = getStringFromPDF();
            return getHashtable(newString);

        }

        return hashtable;
    }


    /**The function takes a filename as an input from the user and reads the image to recognise a string using an OCR.
     *
     * @return String : String with all recognizable text from the input file
     */
    public static String getStringFromPDF() {
        Scanner in = new Scanner(System.in);
        String f = in.next();
        //Starting up the OCR and getting text string from the image
        Ocr.setUp();
        Ocr ocr = new Ocr();
        ocr.startEngine("eng",Ocr.SPEED_FASTEST); //Selecting the language "English"
        String s = ocr.recognize(new File[] {new File(f)}, Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);
        ocr.stopEngine();
        //Stop the OCR engine
        return s;
    }

    public static void main(String[] args) {

        System.out.println("Enter Name of file with extension: (Make sure file is in the project directory)");
        String s = getStringFromPDF();
        //Getting hashtable with all the words as keys
        Hashtable<String, Integer> hashtable = getHashtable(s);
        //Simple variable to decide if while loop keeps running
        String toCont = "Y";

        //Main loop, allows user to search for frequency of multiple words
        Scanner in = new Scanner(System.in); //Taking filename as input
        while(toCont.equals("Y")) {
            System.out.println("Enter Word to find frequency for : ");
            String toFind = in.next();
            System.out.println("Frequency of the Word is : " + hashtable.get(toFind));
            System.out.println("Do you want to search or another word? Y/N");
            toCont = in.next().toUpperCase();
        }
    }
}
