import java.io.PrintWriter;

public class VigenereCipher {
  public static void main(String[] args) {
    PrintWriter err = new PrintWriter(System.err, true);

    if (args.length != 3) {
      err.println("Incorrect number of parameters");
      System.exit(2);
    }

    String instruction = args[0].toLowerCase();

    if (!(instruction.equals("encode") || instruction.equals("decode"))){
      err.println("Valid options are \"encode\" or \"decode\"");
      System.exit(1);
    }

    PrintWriter out = new PrintWriter(System.out, true);
    String msg = args[1];
    String key = args[2];

    int fixN = 1;
    if (instruction.equals("decode")) fixN = -1;

    out.println(xcode(msg, key, fixN));
  }

  public static String xcode(String msg, String key, int fixN) {
    if (key.length() == 0) return msg;

    char[] toTransform = msg.toCharArray();
    char[] keyArr = key.toCharArray();

    for (int i = 0; i < toTransform.length; i++) {
      toTransform[i] = numToAlpha(rebaseChar(toTransform[i]) + (rebaseChar(keyArr[i % keyArr.length]) * fixN));
    }

    return new String(toTransform); 
  }

  public static int rebaseChar(char ch) {
    return (int) ch - (int) 'a';
  }

  public static char numToAlpha(int n) {
    int modN = n % 26;
    if (modN < 0) modN = 26 + modN;

    return (char) (modN + (int) 'a');
  }
}
