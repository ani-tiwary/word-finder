import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class VerifyWord {
    public boolean isWord(String testWord) throws FileNotFoundException {
        File myObj = new File("src/words_alpha.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if (testWord.equals(data)){
                myReader.close();
                return true;
            }
        }
        myReader.close();
        return false;
    }
}
