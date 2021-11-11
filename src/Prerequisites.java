//Author: Sofia Bzhilyanskaya (sab4633@rit.edu)
/**
 * Hw5-2: Given n courses and their respective prerequisites find the longest prerequisites chain
 */
import java.util.Scanner;

public class Prerequisites {
    static int time = 0; //keeps track of finishing times for dfs

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine()); //number of matrices
        LinkedList[] adjacencyList = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            String nums = sc.nextLine();
            String[] numar = nums.split(" ");
            LinkedList row = new LinkedList(Integer.parseInt(numar[0]));
            adjacencyList[i] = row;
            for (int j = 1; j < numar.length; j++) {
                row.next = new LinkedList(Integer.parseInt(numar[j]));
                row = row.next;
            }
        }
        boolean[] visited = new boolean[n + 1]; //tracks visited array
        int[] fin = new int[n + 1]; //tracks finishing times
        for (int i = 1; i <= n; i++) {
            visited[i] = false; //set all to false
            fin[i] = Integer.MAX_VALUE; //set to max
        }
        Prerequisites program = new Prerequisites();
        int[] stack = new int[n+1];
        for(int i = 1; i<=n; i++){
            if(visited[i]==false){ //if not seen
               program.DFS(adjacencyList, visited, i, fin, stack); //DFS
            }
        }
        for (int i = 1; i <= n; i++) {
            visited[i] = false; //set all to false again
        }
        int[] dist =new int[n+1];
        for(int i =1; i<=n; i++){ //iterate in decreasing finish time
            if(visited[stack[i]] == false){ //if not seen call it
                program.longDFS(adjacencyList, visited, stack[i], dist, 1);
            }
        }
        int maxval = 0; //initial max value
        for(int i =1; i<=n; i++){ //find max distance
            if(dist[i]>maxval){
                maxval = dist[i];

            }
        }
        System.out.println(maxval);

    }

    /**
     * Keeps track of distances from first call of DFS
     * @param adjacencyList graph
     * @param visited vertices visited
     * @param value current value
     * @param dist distance array fo all points
     * @param curdist current distance
     */
    public void longDFS(LinkedList[] adjacencyList, boolean[] visited, int value, int[] dist, int curdist){
        visited[value] = true;
        LinkedList temp = adjacencyList[value];
        while(temp.val != 0){ //giterate to end
            if(visited[temp.val] == false || curdist+1> dist[temp.val]){ //if not seen or the distance is further
                longDFS(adjacencyList, visited, temp.val, dist, curdist+1); //call longDFS again
            }
            temp = temp.next;

        }
        if(dist[value]<curdist){ //update distance if it is smaller then the current distance
            dist[value] = curdist;
        }
    }

    /**
     * Normal DFS that tracks finishing times in the stack
     * @param adjacencyList graph
     * @param visited locations visited
     * @param value current value
     * @param fin finishing times array
     * @param stack stack of items in decreasing finishing times
     */
    public void DFS(LinkedList[] adjacencyList, boolean[] visited, int value, int[] fin, int[] stack){
        visited[value] = true;
        LinkedList temp = adjacencyList[value];
        while(temp.val != 0){ //iterate to end
            if(visited[temp.val] == false){ //if not seen
                DFS(adjacencyList, visited, temp.val, fin, stack); //call next DFS
            }
            temp = temp.next;

        }
        time++; //increase time
        fin[value] = time; //set new finishing time
        stack[adjacencyList.length-time] = value; //add to stack
    }
}
