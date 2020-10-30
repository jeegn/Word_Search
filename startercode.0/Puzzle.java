import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Puzzle {
    char[][] puzzle;
    int N;
    LinkedList<Pair>[] locations = new LinkedList[26];

    /***
     *constructor: fn is the filename
     *where the puzzle is stored
     ***/
    public Puzzle(String fn) {
        //TO BE IMPLEMENTED
        try {
            File file = new File(fn);
            Scanner sc = new Scanner(file);
            N = sc.nextInt();
            puzzle = new char[N][N];
            sc.nextLine();
            String row;
            for (int i = 0; i < N; i++) {
                row = sc.nextLine();
                for (int j = 0; j < N; j++) {
                    char c = row.charAt(j);
                    Pair pair = new Pair(i, j);
                    if (locations[c - 65] == null)
                        locations[c - 65] = new LinkedList<Pair>();
                    locations[c - 65].add(pair);
                    puzzle[i][j] = row.charAt(j);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 26; i++) {
            if (locations[i] == null)
                locations[i] = new LinkedList<Pair>();
        }
    }

    /***
     *search the puzzle for the given word
     *return {a, b, x, y} where (a, b) is
     *the starting location and (x, y) is 
     *the ending location
     *return null if the word can't be found
     *in the puzzle
     ***/
    public int[] search(String word) {
        //TO BE IMPLEMENTED\
        int[] result = {-1, -1, -1, -1};
        char c = word.charAt(0);
        Iterator<Pair> iterator = locations[c - 65].iterator();
        for (Pair p : locations[c - 65]) {
            result = horizontalFront(p.i, p.j, word);
            if ((result[2] != -1) && (result[3] != -1)) {
                return result;
            }
            result = horizontalBack(p.i, p.j, word);
            if ((result[2] != -1) && (result[3] != -1)) {
                return result;
            }
            result = verticalFront(p.i, p.j, word);
            if ((result[2] != -1) && (result[3] != -1)) {
                return result;
            }
            result = verticalBack(p.i, p.j, word);
            if ((result[2] != -1) && (result[3] != -1)) {
                return result;
            }
            result = diagonalSouthEast(p.i, p.j, word);
            if ((result[2] != -1) && (result[3] != -1)) {
                return result;
            }
            result = diagonalNorthWest(p.i, p.j, word);
            if ((result[2] != -1) && (result[3] != -1)) {
                return result;
            }
            result = diagonalSouthWest(p.i, p.j, word);
            if ((result[2] != -1) && (result[3] != -1)) {
                return result;
            }
            result = diagonalNorthEast(p.i, p.j, word);
            if ((result[2] != -1) && (result[3] != -1)) {
                return result;
            }
            p = iterator.next();
        }
        return null;
    }

    private int[] horizontalFront(int i, int j, String word) {
        int[] result = {i, j, -1, -1};
        if ((j + word.length()) > N)
            return result;
        int index = j + 1;
        for (int k = 1; k < word.length(); k++) {
            if (word.charAt(k) != puzzle[i][index])
                break;
            if (k == word.length() - 1){
                result[2] = i;
                result[3] = index;
            }
            index++;
        }
        return result;
    }
    private int[] horizontalBack(int i, int j, String word) {
        int[] result = {i, j, -1, -1};
        if (((j + 1) - word.length()) < 0)
            return result;
        int index = j - 1;
        for (int k = 1; k < word.length(); k++) {
            if (word.charAt(k) != puzzle[i][index])
                break;
            if (k == word.length() - 1){
                result[2] = i;
                result[3] = index;
            }
            index--;
        }
        return result;
    }
    private int[] verticalFront(int i, int j, String word) {
        int[] result = {i, j, -1, -1};
        if ((i + word.length()) > N)
            return result;
        int index = i + 1;
        for (int k = 1; k < word.length(); k++) {
            if (word.charAt(k) != puzzle[index][j])
                break;
            if (k == word.length() - 1){
                result[2] = index;
                result[3] = j;
            }
            index++;
        }
        return result;
    }
    private int[] verticalBack(int i, int j, String word) {
        int[] result = {i, j, -1, -1};
        if (((i + 1) - word.length()) < 0)
            return result;
        int index = i - 1;
        for (int k = 1; k < word.length(); k++) {
            if (word.charAt(k) != puzzle[index][j])
                break;
            if (k == word.length() - 1){
                result[2] = index;
                result[3] = j;
            }
            index--;
        }
        return result;
    }
    private int[] diagonalSouthEast(int i, int j, String word) {
        int[] result = {i, j, -1, -1};
        if (((i + word.length())) > N || ((j + word.length()) > N))
            return result;
        int iIndex = i + 1;
        int jIndex = j + 1;
        for (int k = 1; k < word.length(); k++) {
            if (word.charAt(k) != puzzle[iIndex][jIndex])
                break;
            if (k == word.length() - 1){
                result[2] = iIndex;
                result[3] = jIndex;
            }
            iIndex++;
            jIndex++;
        }
        return result;
    }
    private int[] diagonalNorthWest(int i, int j, String word) {
        int[] result = {i, j, -1, -1};
        if (((i + 1) - word.length()) < 0 || ((j + 1) - word.length()) < 0)
            return result;
        int Iindex = i - 1;
        int Jindex = j - 1;
        for (int k = 1; k < word.length(); k++) {
            if (word.charAt(k) != puzzle[Iindex][Jindex])
                break;
            if (k == word.length() - 1){
                result[2] = Iindex;
                result[3] = Jindex;
            }
            Iindex--;
            Jindex--;
        }
        return result;
    }
    private int[] diagonalSouthWest(int i, int j, String word) {
        int[] result = {i, j, -1, -1};
        if (((i + word.length())) > N || ((j + 1) - word.length()) < 0)
            return result;
        int iIndex = i + 1;
        int jIndex = j - 1;
        for (int k = 1; k < word.length(); k++) {
            if (word.charAt(k) != puzzle[iIndex][jIndex])
                break;
            if (k == word.length() - 1){
                result[2] = iIndex;
                result[3] = jIndex;
            }
            iIndex++;
            jIndex--;
        }
        return result;
    }
    private int[] diagonalNorthEast(int i, int j, String word) {
        int[] result = {i, j, -1, -1};
        if (((i + 1) - word.length()) < 0 || ((j + word.length()) > N))
            return result;
        int Iindex = i - 1;
        int Jindex = j + 1;
        for (int k = 1; k < word.length(); k++) {
            if (word.charAt(k) != puzzle[Iindex][Jindex])
                break;
            if (k == word.length() - 1){
                result[2] = Iindex;
                result[3] = Jindex;
            }
            Iindex--;
            Jindex++;
        }
        return result;
    }
}

class Pair {
    int i;
    int j;
    public Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }
}