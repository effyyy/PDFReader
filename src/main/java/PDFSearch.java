
import com.asprise.ocr.Ocr;
import java.io.File;
import java.util.*;

public class PDFSearch {

    /**
     * This function returns a hashtable with all words in input string as keys and their frequencies as values.
     * @param fullString This is the Entire string which is recognised by the OCR
     * @return Hastable with words and their frequencies
     */
    public static Hashtable<String, Integer> getHashtable(String fullString){
        Hashtable<String,Integer> hashtable = new Hashtable<>();
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
        return hashtable;
    }

    public static void main(String[] args) {


        System.out.println("Enter Name of file with extension: (Make sure file is in the project directory)");
        Scanner in = new Scanner(System.in);
        String f = in.next();
        Ocr.setUp();
        Ocr ocr = new Ocr();
        ocr.startEngine("eng",Ocr.SPEED_FASTEST);
        String s = ocr.recognize(new File[] {new File(f)}, Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);
        ocr.stopEngine();
        Hashtable<String, Integer> hashtable = getHashtable(s);
        String toCont = "Y";
        while(toCont.equals("Y")) {
            System.out.println("Enter Word to find frequency for : ");
            String toFind = in.next();
            System.out.println("Frequency of the Word is : " + hashtable.get(toFind));
            System.out.println("Do you want to search or another word? Y/N");
            toCont = in.next().toUpperCase();
        }
    }
}
