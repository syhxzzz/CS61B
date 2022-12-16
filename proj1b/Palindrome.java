public class Palindrome{
    public Deque<Character> wordToDeque(String word){
        Deque<Character> res = new LinkedListDeque<Character>();
        int length = word.length();
        for(int i=0;i<length;i++){
            res.addLast(word.charAt(i));
        }
        return res;
    }
    public boolean isPalindrome(String word){
        Deque res = wordToDeque(word);
        if(isPalindromeWithRecursion(res)==false){
            return false;
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque res = wordToDeque(word);
        if(isPalindromeWithRecursion(res,cc)==false){
            return false;
        }
        return true;
    }
    public boolean isPalindromeWithRecursion(Deque res){
        if(res.size()<=1){
            return true;
        }
        Character first1 = (Character)res.removeFirst();
        Character last1 =  (Character)res.removeLast();
        if(first1!=last1){
            return false;
        }
        return isPalindromeWithRecursion(res);
    }
    public boolean isPalindromeWithRecursion(Deque res,CharacterComparator cc){
        if(res.size()<=1){
            return true;
        }
        Character first1 = (Character)res.removeFirst();
        Character last1 =  (Character)res.removeLast();
        if(!cc.equalChars(first1, last1)){
            return false;
        }
        return isPalindromeWithRecursion(res,cc);
    }
}

//使用charAt(int index)方法以获得该位置处的字符 
//用递归处理回文字串