import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } 
    @Test
    public void testisPalindrome(){
        assertTrue(palindrome.isPalindrome("noon"));
        assertFalse(palindrome.isPalindrome("persiflage"));
        assertTrue(palindrome.isPalindrome("a"));
        CharacterComparator obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake",obo));
        assertFalse(palindrome.isPalindrome("alake",obo));
        CharacterComparator obn = new OffByN(5);
        assertTrue(palindrome.isPalindrome("abchgf",obn));
        assertFalse(palindrome.isPalindrome("aadaaaa",obn));
    }

}
