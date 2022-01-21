package com.local.marchant.util;

import java.util.Random;

public class SudokuGenerator {
    private int[][] board = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    public SudokuGenerator(){}

    public void generate(){
        int value = 0, attemps = 0;
        boolean solved = false, breakLoop = false;

        while(true){
            clear();

            for(int yG = 0; yG < 3; yG++){
                for(int xG = 0; xG < 3; xG++){
                    for(int y = yG * 3; y < yG * 3 + 3; y++){
                        for(int x = xG * 3; x < xG * 3 + 3; x++){
                            solved = false;
    
                            for(int i = 0; i < 81; i++){
                                value = getRandomInt(1, 9);
    
                                if(repeatX(value, y) < 1 && repeatY(value, x) < 1 && repeatGroup(value, x, y) < 1){
                                    board[y][x] = value;
                                    solved = true;
                                    break;
                                }
                            }
    
                            if(!solved){
                                clearGroup(x, y);
                                attemps++;

                                if(attemps >= 81) breakLoop = true;
                                y = yG * 3 - 1;
                                x = xG * 3;
                                break;
                            } 
                        }

                        if(breakLoop) break;
                    }

                    if(breakLoop) break;
                }

                if(breakLoop) break;
            }

            if(isComplete()){
                break;
            }

            if(breakLoop) {
                breakLoop = false;
                attemps = 0;
            }
        }
    }

    public void printBoard(){
        System.out.println();
        for(int[] y : board){
            for(int x : y) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private int getRandomInt(int min, int max){
        Random random = new Random();

        return random.nextInt((max - min) + 1) + min;
    }

    private boolean isComplete(){
        boolean complete = true;

        for(int[] y : board){
            for (int x : y) {
                if (x == 0){
                    complete = false;
                    break;
                }
            }

            if(!complete) break;
        }

        return complete;
    }

    private void clear(){
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++) board[y][x] = 0;
        }
    }

    private int repeatX(int value, int y){
        int repeat = 0;

        for (int x = 0; x < 9; x++){
            if(board[y][x] == value) repeat++;
        }

        return repeat;
    }

    private int repeatY(int value, int x){
        int repeat = 0;

        for (int y = 0; y < 9; y++){
            if(board[y][x] == value) repeat++;
        }

        return repeat;
    }

    private int repeatGroup(int value, int x, int y){
        int xG = 0, yG = 0, repeat = 0;

        if(x > 0) xG = x / 3;
        if(y > 0) yG = y / 3;

        for(int yY = yG * 3; yY < yG * 3 + 3; yY++){
            for(int xX = xG * 3; xX < xG * 3 + 3; xX++){
                if (board[yY][xX] == value) repeat++;
            }
        }

        return repeat;
    }

    private void clearGroup(int x, int y){
        int xG = 0, yG = 0;

        if(x > 0) xG = x / 3;
        if(y > 0) yG = y / 3;

        for(int yY = yG * 3; yY < yG * 3 + 3; yY++){
            for(int xX = xG * 3; xX < xG * 3 + 3; xX++){
                board[yY][xX] = 0;
            }
        }
    }

    public int[][] getBoard(){
        return this.board;
    }

    public String toJavaCode(){
        String java = "";

        int yC = 0;
        int xC = 0;

        java += "int[][] board = {\n";
        for(int[] y : board){
            xC = 0;
            java += "\t{";

            for (int x : y) {
                java += x;
                
                if(xC < 8) java += ", ";

                xC++;
            }

            java += "}";

            if(yC < 8) java += ",";

            java += "\n";

            yC++;
        }

        java += "};";

        return java;
    }

    @Override
    public String toString(){
        String string = "";

        int yC = 0;
        int xC = 0;

        string += "[\n";
        for(int[] y : board){
            xC = 0;
            string += "\t[";

            for (int x : y) {
                string += x;
                
                if(xC < 8) string += ", ";

                xC++;
            }

            string += "]";

            if(yC < 8) string += ",";

            string += "\n";

            yC++;
        }

        string += "];";

        return string;
    }
}
