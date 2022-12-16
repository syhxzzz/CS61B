public class OffByN implements CharacterComparator{
    int NumberToCompare=1;
    public OffByN(int N){
        NumberToCompare = N;
    }
    @Override
    public boolean equalChars(char x, char y){
        return Math.abs((int) x-y)==NumberToCompare;
    }
    
}
