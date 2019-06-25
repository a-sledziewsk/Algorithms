package main.algorithms;

public class TyreInflationProblem {


    public static class MyLinkedList<T>{

        private Node<T> head;

        public class Node<T>{
            public T obj;
            public Node<T> next;
        }

        public int size(){
            int count = 0;
            if(head == null){
                return 0;
            }else{
                Node<T> currNode = head;
                while(currNode != null){
                    count++;
                    currNode = currNode.next;
                }
                return count;
            }
        }

        public boolean contains(T obj){
            if(head == null){
                return false;
            }else{
                Node<T> currNode = head;
                while(currNode != null){
                    if(currNode.obj.equals(obj)){
                        return true;
                    }
                    currNode = currNode.next;
                }
            }
            return false;
        }

        public void push(T obj){
            if(head == null){
                head = new Node<T>();
                head.obj = obj;
            }else{
                Node<T> currNode = head;
                while(currNode.next != null){
                    currNode = currNode.next;
                }
                currNode.next = new Node<T>();
                currNode.next.obj = obj;
            }
        }

        public T pop(){
            if(this.head == null) {
                return null;
            }
            Node<T> currNode = this.head;
            if(currNode.next == null){
                this.head = null;
                return currNode.obj;
            }
            while(currNode.next.next != null){
                currNode = currNode.next;
            }
            T ret = currNode.next.obj;
            currNode.next = null;
            return ret;

        }

    }

    private int maxPressure = 100;
    private int currentMinPressure = Integer.MAX_VALUE;
    private boolean found = false;



    public static void main(String[] args){
        Integer[][] range = new Integer[3][];
        range[0] = new Integer[]{75, 30};
        range[1] = new Integer[]{45, 55};
        range[2] = new Integer[]{80, 95};

        TyreInflationProblem bfs = new TyreInflationProblem();

        bfs.generate(range, new Integer[range.length], 0, 0, 0, 0);

        if(bfs.found){
            System.out.println("Minimal pressure is " + bfs.currentMinPressure);
        }else{
            System.out.println("Could not find minimal pressure");
        }
    }

    private boolean contains(Integer[] visited, int visitCounter, Integer obj){
        for(int i=0; i<visitCounter; i++){
            if(visited[i].equals(obj)){
                return true;
            }
        }
        return false;
    }

    private void generate(Integer[][] input, Integer[] visited, int visitCounter, int current, int min, int max){
        if(input.length == visitCounter){
            found = true;
            if(currentMinPressure > -min){
                currentMinPressure = -min;
            }
            return;
        }

        for(int i=0; i<input.length; i++){
            if(!contains(visited, visitCounter, i)){
                visited[visitCounter++] = i;

                int newCurrent = current + input[i][0];
                int newMax = max;
                int newMin = min;
                if(newMax < newCurrent){
                    newMax = newCurrent;
                }

                if(newMax - newMin >= this.maxPressure){
                    continue;
                }

                newCurrent -= input[i][1];
                if(newMin > newCurrent){
                    newMin = newCurrent;
                }

                if(newMin - newMax <= -this.maxPressure){
                    continue;
                }

                generate(input, visited, visitCounter, newCurrent, newMin, newMax);
                visitCounter--;
            }
        }
    }





}
