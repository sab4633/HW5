//Author: Sofia Bzhilyanskaya (sab4633@rit.edu)
/**
 * Hw5-4: Given a directed graph of a town this program calculates whether you can build a single road to make the
 * town strongly connected and if so what is the u, v edge that makes it so.
 */
import java.util.Scanner;

public class StronglyConnectWithNewEdge {
    static int time = 0; //keeps track of dfs time
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        LinkedList[] adjacencyList = new LinkedList[n+1];
        for(int i =1; i<=n; i++){ //intialize adjacency list from graph input
            String vals = sc.nextLine();
            String[] nums = vals.split(" ");
            adjacencyList[i] = new LinkedList(Integer.parseInt(nums[0]));
            LinkedList temp = adjacencyList[i];
            for(int j = 1; j<nums.length;j++){
                temp.next = new LinkedList(Integer.parseInt(nums[j]));
                temp = temp.next;
            }
        }
        boolean[] seen = new boolean[n+1]; //tracks elements that have been visited
        int[] fin = new int[n+1]; //finishing times
        for(int i = 1; i<=n; i++){
            seen[i] = false;
            fin[i] = 0;
        }

        StronglyConnectWithNewEdge program = new StronglyConnectWithNewEdge();
        int[] stack = new int[n+1]; //tracks decreasing finishing times
        for(int i = 1; i<=n; i++){
            if(seen[i] == false){ //if not seen
                program.DFS(adjacencyList, seen, i, fin, stack); //calculate finishing time
            }
        }
        LinkedList[] gt = new LinkedList[n+1]; //create an inverse of the orginal graph
        for(int i = 1; i<=n; i++){
            LinkedList temp = adjacencyList[i];
            while(temp.val!=0){ //iterate to the end
                if(gt[temp.val]==null){ //if it doesnt exist create it
                    gt[temp.val] = new LinkedList(i);
                }else{ //otherwise add it
                    gt[temp.val].add(i);
                }
                temp = temp.next;
            }

        }
        //add zeros to the end
        for(int i = 1; i<=n; i++){
            if(gt[i]==null){
                gt[i] = new LinkedList(0);
            }else{
                gt[i].add(0);
            }


        } //reset seen arrays
        for(int i = 1; i<=n; i++){
            seen[i] = false;
        }
        int comp = 0; //component count
        int last = 0; //an element from the last component
        for(int i = 1; i<=n; i++){
            if(seen[stack[i]] == false){ //if ut hasnt been seen
                program.outputDFS(gt, seen, stack[i]);
                last = stack[i]; //track last DFS call
                comp++; //increase component count
            }
        }
        for(int i = 1; i<=n; i++){
            seen[i] = false; //reset seen array
        }
        boolean[] vis = new boolean[n+1]; //visit array for last component
        boolean[] visfirst = new boolean[n+1]; //for first
        program.outputDFS(adjacencyList, vis, last); //run DFS for last

        program.outputDFS(gt, visfirst, stack[1]); //for first
        int min = Integer.MAX_VALUE;
        int minfirst = Integer.MAX_VALUE;
        for(int i =1; i<=n; i++){
            if(visfirst[i] && minfirst>i){
                minfirst = i; //smallest value visited by first components
            }else if(vis[i]==true && min>i){
                min = i; //smallest value visited by last
            }
        }
        comp = 0; //reset comp
        LinkedList temp = gt[minfirst]; //add an edge
        while(temp.val !=0){ //iterate to the end
            temp = temp.next;
        }
        temp.val = min; //add value
        temp.add(0); //re add 0
        //see if its a strongly connected component with new edge
        for(int i = 1; i<=n; i++){
            if(seen[stack[i]] == false){
                program.outputDFS(adjacencyList, seen, stack[i]);
                comp++;
            }

        }
        if(comp ==1){
            System.out.println("YES");
            System.out.println(min+" "+stack[1]);
        }else{
            System.out.println("NO");
        }
    }

    /**
     * Simply iterates the DFS and sees what elements have been visited
     * @param adjacencyList graph
     * @param visited seen values
     * @param value current valye
     */
    public void outputDFS(LinkedList[] adjacencyList, boolean[] visited, int value){
        visited[value] = true;
        LinkedList temp = adjacencyList[value];
        while(temp.val != 0){ //iterate to end
            if(visited[temp.val] == false){ //if not seen yet
                outputDFS(adjacencyList, visited, temp.val);
            }
            temp = temp.next;

        }
    }

    /**
     * Normal DFS that tracks finishing time in decreasing order in the stack
     * @param adjacencyList graph
     * @param visited seen values
     * @param value current value
     * @param fin finishing times
     * @param stack decreasing elements in order of finishing times
     */
    public void DFS(LinkedList[] adjacencyList, boolean[] visited, int value, int[] fin, int[] stack){
        visited[value] = true;
        LinkedList temp = adjacencyList[value];
        while(temp.val != 0){ //iterate to end
            if(visited[temp.val] == false){ //if not seen call DFS
                DFS(adjacencyList, visited, temp.val, fin, stack);
            }
            temp = temp.next;

        }
        time++; //increment time
        fin[value] = time; //add finishing time
        stack[adjacencyList.length-time] = value; //add to stack
    }
}
