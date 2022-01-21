package com.local.marchant.app;

//import java.util.Random;
import com.local.marchant.util.*;

public class App{
    public static void main(String[] args){
        SudokuGenerator sudoku = new SudokuGenerator();

        sudoku.generate();
        sudoku.printBoard();

        System.out.println(sudoku);
    }
}