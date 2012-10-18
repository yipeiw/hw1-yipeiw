package Tagger;
/**
 * Support text processing function.
 */

public class Utility {

  public static int CountNonSpaceChar(String text) {
    String[] words = text.split("[ ]");
    return text.length() - (words.length - 1);
  }

}
