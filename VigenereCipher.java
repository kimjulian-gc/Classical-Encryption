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
    }

    // get the instruction on whether or not to encode or decode
    String instruction = args[0].toLowerCase();

    // check for valid instruction
    if (!(instruction.equals("encode")
        || instruction.equals("decode"))){
      err.println("Valid options are \"encode\" or \"decode\"");
      System.exit(1);
    }

    // make normal output
    PrintWriter out = new PrintWriter(System.out, true);
    // get message to put through cipher
    String msg = args[1];
    // get key to cipher msg with
    String key = args[2];

    // encoding is shifting positively, decoding is shifting negatively
    int fixN = 1;
    if (instruction.equals("decode")) fixN = -1;

    // apply the instruction on the msg
    out.println(xcode(msg, key, fixN));
  }

  // encode or decode a string
  public static String xcode(String msg, String key, int fixN) {
    // avoiding division by zero
    if (key.length() == 0) return msg;

    // // convert msg and key to arrays
    char[] toTransform = msg.toCharArray();
    char[] keyArr = key.toCharArray();

    // loop over every letter
    for (int i = 0; i < toTransform.length; i++) {
      // cipher current letter
      toTransform[i] = CaesarCipher.numToAlpha(CaesarCipher.rebaseChar(toTransform[i]) + (CaesarCipher.rebaseChar(keyArr[i % keyArr.length]) * fixN));
    }

    // return ciphered string
    return new String(toTransform); 
  }
}
