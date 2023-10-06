// imports
import java.io.PrintWriter;

public class CaesarCipher {
  public static void main(String[] args) {
    // make err output
    PrintWriter err = new PrintWriter(System.err, true);

    // check for the right amt of args
    if (args.length != 2) {
      err.println("Incorrect number of parameters");
      System.exit(2);
    } // if

    // get the instruction on whether or not to encode or decode
    String instruction = args[0].toLowerCase();

    // check for valid instruction
    if (!(instruction.equals("encode")
        || instruction.equals("decode"))){
      err.println("Valid options are \"encode\" or \"decode\"");
      System.exit(1);
    } // if

    // make normal output
    PrintWriter out = new PrintWriter(System.out, true);
    // get message to put through cipher
    String msg = args[1];

    // encoding is shifting positively, decoding is shifting negatively
    int fixN = 1;
    if (instruction.equals("decode")) fixN = -1;

    // do the instruction for each possible shift
    for (int i = 0; i < 26; i++) {
      out.println("n = " + i + ": " + xcode(msg, i, fixN));
    } // for
  } // main

  // encode or decode a string
  public static String xcode(String msg, int n, int fixN) {
    // convert msg to array
    char[] toTransform = msg.toCharArray();

    // loop over every letter
    for (int i = 0; i < toTransform.length; i++) {
      // shift current letter
      toTransform[i] = numToAlpha(rebaseChar(toTransform[i]) + (n * fixN));
    } // for

    // return shifted string
    return new String(toTransform); 
  } // xcode

  // rebase character to 0-26 scale
  public static int rebaseChar(char ch) {
    return (int) ch - (int) 'a';
  } // rebaseChar

  // convert number to letter
  public static char numToAlpha(int n) {
    int modN = n % 26;
    // dumb code because oracle has no clue what modulus is
    if (modN < 0) modN = 26 + modN;

    return (char) (modN + (int) 'a');
  } // numToAlpha
} // CaesarCipher