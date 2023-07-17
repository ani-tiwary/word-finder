import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
public class Main {
    private static void permute(String prefix, String suffix, List<String> rearrangements, VerifyWord verifier) throws FileNotFoundException {
        int length = suffix.length();
        if (verifier.isWord(prefix)) {
            rearrangements.add(prefix);
        }
        if (length > 1) {
            List<Thread> threads = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                String newPrefix = prefix + suffix.charAt(i);
                String newSuffix = suffix.substring(0, i) + suffix.substring(i + 1);
                Thread thread = new Thread(() -> {
                    try {
                        permute(newPrefix, newSuffix, rearrangements, verifier);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                });
                threads.add(thread);
                thread.start();
            }
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static ArrayList<String> genRearrangements(String input, int numThreads) throws FileNotFoundException {
        ArrayList<String> rearrangements = new ArrayList<>();
        VerifyWord verifier = new VerifyWord();
        permute("", input, rearrangements, verifier);
        return rearrangements;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String input = "abcdef";
        int numThreads = Runtime.getRuntime().availableProcessors();
        ArrayList<String> rearrangements = genRearrangements(input, numThreads);
        System.out.println(rearrangements);
    }
}