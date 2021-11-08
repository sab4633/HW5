import java.sql.PreparedStatement;
import java.util.Scanner;

public class Prerequisites {
    static int time = 0;

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
        boolean[] visited = new boolean[n + 1];
        int[] fin = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            visited[i] = false;
            fin[i] = Integer.MAX_VALUE;
        }
        Prerequisites program = new Prerequisites();
        int[] stack = new int[n+1];
        for(int i = 1; i<=n; i++){
            if(visited[i]==false){
               program.DFS(adjacencyList, visited, i, fin, stack);
            }
        }
        for (int i = 1; i <= n; i++) {
            visited[i] = false;
        }
        int[] dist =new int[n+1];
        for(int i =1; i<=n; i++){
            if(visited[stack[i]] == false){
                program.longDFS(adjacencyList, visited, stack[i], dist, 1);
            }
        }
        int maxval = 0;
        for(int i =1; i<=n; i++){
            if(dist[i]>maxval){
                maxval = dist[i];

            }
        }
        System.out.println(maxval);


    }
    public void longDFS(LinkedList[] adjacencyList, boolean[] visited, int value, int[] dist, int curdist){
        visited[value] = true;
        LinkedList temp = adjacencyList[value];
        while(temp.val != 0){
            if(visited[temp.val] == false || curdist+1> dist[temp.val]){
                longDFS(adjacencyList, visited, temp.val, dist, curdist+1);
            }
            temp = temp.next;

        }
        if(dist[value]<curdist){
            dist[value] = curdist;
        }



    }


    public void DFS(LinkedList[] adjacencyList, boolean[] visited, int value, int[] fin, int[] stack){
        visited[value] = true;
        LinkedList temp = adjacencyList[value];
        while(temp.val != 0){
            if(visited[temp.val] == false){
                DFS(adjacencyList, visited, temp.val, fin, stack);
            }
            temp = temp.next;

        }
        time++;
        fin[value] = time;
        stack[adjacencyList.length-time] = value;


    }


}
