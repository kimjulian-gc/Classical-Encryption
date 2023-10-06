// imports
import java.io.PrintWriter;

public class VigenereCipher {
  public static void main(String[] args) {
    // make err output
    PrintWriter err = new PrintWriter(System.err, true);

    // check for the right amt of args
    if (args.length != 3) {
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
    // get key to cipher msg with
    String key = args[2];

    // encoding is shifting positively, decoding is shifting negatively
    int direction = 1;
    if (instruction.equals("decode")) direction = -1;

    // apply the instruction on the msg
    out.println(xcode(msg, key, direction));
  } // main

  // encode or decode a string
  public static String xcode(String msg, String key, int direction) {
    // avoiding division by zero
    if (key.length() == 0) return msg;

    // // convert msg and key to arrays
    char[] toTransform = msg.toCharArray();
    char[] keyArr = key.toCharArray();

    // loop over every letter
    for (int i = 0; i < toTransform.length; i++) {
      // cipher current letter
      toTransform[i] = numToAlpha(rebaseChar(toTransform[i]) + (rebaseChar(keyArr[i % keyArr.length]) * direction));
    } // for

    // return ciphered string
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
} // VignereCipher
