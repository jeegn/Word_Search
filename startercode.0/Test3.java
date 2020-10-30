public class Test3 {
    public static void main(String[] args) {
	Puzzle p = new Puzzle("puzzle3_test.txt");
	String[] words = new String []
	    {"ALBANY", "AUGUSTA", "ATLANTA", "CARSONCITY",
	     "DESMOINES", "DOVER", "ANNAPOLIS", "SACRAMENTO",
	     "TALLAHASSEE", "INDIANAPOLIS", "OLYMPIA", 
	     "SALTLAKECITY", "PHOENIX", "SANTAFE", "DENVER",
	     "CHEYENNE", "LINCOLN", "SALEM", "BOISE", "HELENA",
	     "STPAUL", "MADISON", "SPRINGFIELD", "LANSING",
	     "COLUMBUS", "HARTFORD", "HARRISBURG", "MONTPELIER",
	     "CONCORD", "PROVIDENCE", "RICHMOND", "CHARLESTON",
	     "COLUMBIA", "RALEIGH", "BISMARCK", "PIERRE", 
	     "MONTGOMERY", "JACKSON", "JEFFERSONCITY", 
	     "BATONROUGE", "AUSTIN", "TOPEKA", "OKLAHOMACITY",
	     "NASHVILLE", "LITTLEROCK", "HONOLULU", "JUNEAU", 
	     "TRENTON", "BOSTON", "FRANKFURT"};
	int[] startX = new int[] 
	    {6, 11, 10, 10, 12, 36, 48, 25, 24, 15, 18, 37, 33,
	     31, 30, 31, 18, 23, 17, 49, 37, 46, 37, 23, 22, 29,
	     2, 33, 41, 33, 35, 27, 43, 35, 39, 27, 46, 49, 30,
	     16, 37, 3, 0, 46, 3, 8, 6, 18, 48, 40};
	int[] startY = new int[] 
	    {40, 34, 38, 6, 9, 5, 47, 16, 12, 17, 28, 25, 49, 45,
	     32, 3, 27, 25, 28, 19, 25, 8, 19, 27, 29, 6, 10, 17,
	     30, 49, 37, 40, 43, 37, 3, 11, 47, 36, 5, 7, 0, 12, 
	     0, 23, 10, 25, 0, 17, 9, 47};
	int[] endX = new int[] 
	    {1, 11, 16, 10, 4, 32, 40, 25, 14, 4, 18, 37, 27, 25,
	     30, 38, 24, 23, 21, 49, 42, 40, 27, 17, 22, 22, 2, 
	     33, 47, 24, 35, 27, 43, 41, 46, 32, 46, 49, 42, 16, 
	     32, 8, 0, 38, 3, 1, 11, 12, 48, 32};
	int[] endY = new int[]
	    {45, 40, 44, 15, 9, 5, 39, 25, 2, 28, 22, 36, 43,
	     45, 27, 10, 27, 29, 28, 14, 30, 14, 19, 33, 36, 6,
	     1, 26, 24, 49, 44, 31, 36, 37, 3, 16, 38, 30, 17, 
	     16, 0, 17, 11, 15, 19, 25, 0, 23, 14, 47};
	
	for(int i = 0; i < words.length; i++) 
	    findWord(p, words[i], startX[i], 
			      startY[i], endX[i], endY[i]);
	
    }
    
    private static void findWord(Puzzle p, String word, int startX,
				int startY, int endX, int endY) {
	int[] search = p.search(word);
	if(search == null) {
	    System.out.println("Did not find " +  word + ".");
	    return;
	}
	if(startX == search[0] && startY == search[1] &&
	   endX == search[2] && endY == search[3]) {
	    System.out.println("Found " + word + "!");
	} else {
	    System.out.println("Did not find " + word + ".");
	}
    }
}