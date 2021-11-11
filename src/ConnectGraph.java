//Author: Sofia Bzhilyanskaya (sab4633@rit.edu)
/**
 * Hw5-1: Given an undirected graph what is the smallest number of edges that can be built to connect the whole graph
 */
import java.util.Scanner;

public class ConnectGraph {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //number of matrices
        LinkedList[] adjacencyList = new LinkedList[n + 1];
        int m = sc.nextInt();
        if (m > n * 10) {
            System.out.println(0);
        } else {
            for (int i = 0; i < m; i++) { //iterate through input placing each matrix into the array;
                int v1 = sc.nextInt();//first vertex
                int v2 = sc.nextInt();//second vertex
                if (adjacencyList[v1] == null) { //if no list is available
                    adjacencyList[v1] = new LinkedList(v2); //make a new one
                } else {
                    adjacencyList[v1].add(v2); //otherwise add it to the list
                }
                //repeat for v2->v1 edge
                if (adjacencyList[v2] == null) {
                    adjacencyList[v2] = new LinkedList(v1);
                } else {
                    adjacencyList[v2].add(v1);
                }
            }
            ConnectGraph program = new ConnectGraph();
            boolean[] visited = new boolean[n + 1]; //tracks all visited values
            int roads = -1; //start roads amount
            for (int i = 1; i <= n; i++) {
                visited[i] = false; //set all to false
            }
            int count = 0; //exit when all have been visited
            for (int i = 1; i <= n; i++) {
                if (visited[i] == false) { //if it hasnt been seen yet
                    roads++; //increase road
                    program.DFS(adjacencyList, visited, i, count); //find all connected points
                    if (count == n) { //all have been visited
                        break;
                    }
                }

            }
            System.out.println(roads); //minimum number of roads built

        }
    }

    /**
     * Recursive DFS search tracking the locations visited and number visited
     * @param adjacencyList Graph
     * @param visited Values visited and not visited
     * @param value current value
     * @param count number visited
     */
    public void DFS(LinkedList[] adjacencyList, boolean[] visited, int value, int count){
        visited[value] = true;
        LinkedList temp = adjacencyList[value];
        while(temp != null){ //iterate to the end
            if(visited[temp.val] == false){ //if not visited yet call DFS on it
                DFS(adjacencyList, visited, temp.val, count+1); //increase count
            }
            temp = temp.next;

        }

    }

}
