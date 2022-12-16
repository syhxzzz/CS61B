public class ArrayDequeTest {
    public static boolean checkEmpty(boolean expected , boolean actual){
        if (expected != actual){
            System.out.println("isEmpty() return "+ actual + "but expected: " + expected);
            return false;
        }
        return true;
    }

    public static boolean checkSize(int expected, int actual){
        if (expected != actual){
            System.out.println("Size() return "+ actual + "but expected: " + expected);
            return false;
        }
        return true;
    }
    
    public static void printTestStatus(boolean passed){
        if(passed){
            System.out.println("Congradulation!!! You have passed all the Test!");
        }
        else{
            System.out.println("Sorry, Test failed!");
        }
    }

    private static void addIsEmptySizeTest(){
        System.out.println("Running add/isEmpty/size method.");

        ArrayDeque <String> ard1 = new ArrayDeque<>();

        boolean passed = checkEmpty(true,ard1.isEmpty());
        
        ard1.addFirst("Black Jack");

        passed = checkSize(1, ard1.size()) && passed;
        
        ard1.addLast("Bomb!!!");

        passed = checkSize(2, ard1.size()) && passed;

        ard1.addLast("Last one");

        passed = checkSize(3, ard1.size()) && passed;

        System.out.println("What you should print:");
        System.out.println("Black Jack Bomb!!! Last one");
        System.out.println("This is what you actually print");
        ard1.printDeque();
        printTestStatus(passed);
    }
    private static void addRemoveTest(){
        ArrayDeque <String> ard1 = new ArrayDeque<>();
        ard1.addFirst("First");
        ard1.addLast("Second");
        ard1.addLast("Third");
        boolean passed = checkSize(3, ard1.size());
        ard1.removeFirst();
        passed = passed&&checkSize(2, ard1.size());
        printTestStatus(passed);
    }
    public static void main(String[] args) {
        System.out.println("Test starts running");
        addIsEmptySizeTest();
        addRemoveTest();
    }
}