import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
public class Main {
    private static void permute(String prefix, String suffix, List<String> rearrangements) throws FileNotFoundException{
        VerifyWord verifier = new VerifyWord();
        int length = suffix.length();
        if(verifier.isWord(prefix)) {
            rearrangements.add(prefix);
        }
        for (int i = 0; i < length; i++) {
            String newPrefix = prefix + suffix.charAt(i);
            String newSuffix = suffix.substring(0, i) + suffix.substring(i + 1);
            permute(newPrefix, newSuffix, rearrangements);
        }
    }
    public static ArrayList<String> genRearrangements(String input) throws FileNotFoundException{
        ArrayList<String> rearrangements = new ArrayList<>();
        permute("", input, rearrangements);
        return rearrangements;
    }
    public static void main(String[] args) throws FileNotFoundException{
        String input = "abcd";
        ArrayList<String> rearrangements = genRearrangements(input);
        System.out.println(rearrangements);
    }
}