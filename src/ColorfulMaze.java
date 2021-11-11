//Author: Sofia Bzhilyanskaya (sab4633@rit.edu)
/**
 * Hw5-3: Given a maze with three different colors, find the shortest path from vertex s to vertex t going in the
 * order of red, yellow, blue. It also must end on a blue.
 */
import java.util.Scanner;

public class ColorfulMaze {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //number of vertices
        int m = sc.nextInt(); //number of edges
        int s = sc.nextInt(); //starting vertex
        int t = sc.nextInt(); //ending vertex
        LinkedListColor[] adjacencyList = new LinkedListColor[n + 1];
        for(int i = 1; i<=m; i++){
            int v1 = sc.nextInt(); //first vertex
            int v2 = sc.nextInt(); //second vertex
            int col = sc.nextInt(); //associated color
            if (adjacencyList[v1] == null) { //if non-existent create list
                adjacencyList[v1] = new LinkedListColor(v2, col);
            }else{
                adjacencyList[v1].add(v2, col);//otherwise add it
            }
            //repeat in other direction
            if(adjacencyList[v2] == null){
                adjacencyList[v2] = new LinkedListColor(v1, col);
            }else{
                adjacencyList[v2].add(v1, col);
            }

        }

        boolean[][] seenedges = new boolean[n+1][n+1]; //edges that have been seen
        int beg = 1; //beginning of queue
        int end = 2; //end of queue
        int[] queue = new int[2*(m+1)]; //keeps track of BFS elements
        queue[1] = s; //starter
        int[][] distedges = new int[n+1][n+1]; //distance from start of edge
        int[] queueColor = new int[2*(m+1)]; //color associated qith item in queue
        queueColor[1] = 3; //start with blue bc first is red
        int mindist = Integer.MAX_VALUE; //smallest distance at the end
        int[] queuedist = new int[2*(m+1)]; //distance associated with item in queue
        while(beg<end){ //while queue is not empty
            int head = queue[beg]; //
            int headcolor = queueColor[beg];
            int prevdist = queuedist[beg];
            LinkedListColor temp = adjacencyList[head];
            while(temp!= null){ //iterate to end
                if((seenedges[head][temp.val] == false)&& temp.color == (headcolor%3)+1){ //if not seen and color is correct
                    queue[end] = temp.val; //add to queue
                    queueColor[end] = temp.color; //add color to queue
                    queuedist[end] = prevdist+1; //increment distance
                    distedges[head][temp.val] = prevdist+1;
                    seenedges[head][temp.val] = true;
                    end++; //increase queue
                    if(temp.val == t && mindist> distedges[head][temp.val] && temp.color == 3){ //if its at the end and its shorter
                        mindist = distedges[head][temp.val]; //track smallest value
                    }
                }
                temp = temp.next;
            }

            beg++;
        }
        if(mindist== Integer.MAX_VALUE){
            System.out.println("-1");
        }else{
            System.out.println(mindist);
        }

    }


}
