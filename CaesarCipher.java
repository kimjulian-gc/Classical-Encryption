import java.io.PrintWriter;

public class CaesarCipher {
  public static void main(String[] args) {
    PrintWriter err = new PrintWriter(System.err, true);

    if (args.length != 2) {
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

    int fixN = 1;
    if (instruction.equals("decode")) fixN = -1;

    for (int i = 0; i < 26; i++) {
      out.println("n = " + i + ": " + xcode(msg, i, fixN));
    }
  }

  public static String xcode(String msg, int n, int fixN) {
    char[] toTransform = msg.toCharArray();

    for (int i = 0; i < toTransform.length; i++) {
      toTransform[i] = numToAlpha(rebaseChar(toTransform[i]) + (n * fixN));
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