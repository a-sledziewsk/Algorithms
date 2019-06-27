package main.algorithms;

import java.util.Queue;

public class LargestPermutation {

    static int[] inputArr;

    public static void main(String[] args){

    }


    private static boolean contains(int[] visited, int visitedCounter, int obj){

        for(int i=0; i<visitedCounter; i++){
            if(visited[i] == obj){
                return true;
            }
        }
        return false;

    }


    static void findPermutation(int[] inputArr, int[] visited, int visitedCounter){

        if(visitedCounter == inputArr.length){
            for(int i=0;i<visitedCounter; i++){
                System.out.print(visited[i]);
            }
            System.out.println();
        }

        for(int i=0; i<inputArr.length; i++){
            if(!contains(visited, visitedCounter, i)){
                visited[visitedCounter++] = i;

//                findPermutation();
            }
        }

    }

}
