package main.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class tyreInflationProblem {


    private int maxPressure = 100;
    private int currentMinPressure = Integer.MAX_VALUE;
    private boolean found = false;



    public static void main(String[] args){
        List<Integer[]> range = new ArrayList<>();
        range.add(new Integer[]{75, 30});
        range.add(new Integer[]{45, 55});
        range.add(new Integer[]{80, 95});

        tyreInflationProblem bfs = new tyreInflationProblem();

        bfs.generate(range, new LinkedList<>(), 0, 0, 0);

        if(bfs.found){
            System.out.println("Minimal pressure is " + bfs.currentMinPressure);
        }else{
            System.out.println("Could not find minimal pressure");
        }
    }


    private void generate(List<Integer[]> input, LinkedList<Integer> visited, int current, int min, int max){
        if(input.size() == visited.size()){
            found = true;
            if(currentMinPressure > -min){
                currentMinPressure = -min;
            }
            return;
        }

        for(int i=0; i<input.size(); i++){
            if(!visited.contains(i)){
                visited.push(i);

                int newCurrent = current + input.get(i)[0];
                int newMax = max;
                int newMin = min;
                if(newMax < newCurrent){
                    newMax = newCurrent;
                }

                if(newMax - newMin >= this.maxPressure){
                    continue;
                }

                newCurrent -= input.get(i)[1];
                if(newMin > newCurrent){
                    newMin = newCurrent;
                }

                if(newMin - newMax <= -this.maxPressure){
                    continue;
                }

                generate(input, visited, newCurrent, newMin, newMax);
                visited.pop();
            }
        }
    }





}
