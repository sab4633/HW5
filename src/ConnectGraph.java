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
                int v1 = sc.nextInt();
                int v2 = sc.nextInt();
                if (adjacencyList[v1] == null) {
                    adjacencyList[v1] = new LinkedList(v2);
                } else {
                    adjacencyList[v1].add(v2);
                }
                if (adjacencyList[v2] == null) {
                    adjacencyList[v2] = new LinkedList(v1);
                } else {
                    adjacencyList[v2].add(v1);
                }
            }
            ConnectGraph program = new ConnectGraph();
            boolean[] visited = new boolean[n + 1];
            int roads = -1;
            int[] DFS = new int[n + m + 1];
            int top = -1;
            for (int i = 1; i <= n; i++) {
                visited[i] = false;
            }
            int count = 0;
            for (int i = 1; i <= n; i++) {
                if (visited[i] == false) {
                    roads++;
                    program.DFS(adjacencyList, visited, i, count);
                    if (count == n) {
                        break;
                    }
                }

            }
//        for(int i =1; i<=n; i++){
//            if(visited[i] ==false){
//                roads++;
//                top++;
//                DFS[top] = i;
//                while(top!=-1){
//                    visited[DFS[top]] = true;
//                    LinkedList temp = adjacencyList[DFS[top]];
//                    while(temp != null && visited[temp.val]){
//                        temp = temp.next;
//                    }
//                    if(temp!= null){
//                        top++;
//                        DFS[top] = temp.val;
//                    }else{
//                        top--;
//                    }
//
//                }
//
//            }
//
//        }
            System.out.println(roads);

        }
    }

    public void DFS(LinkedList[] adjacencyList, boolean[] visited, int value, int count){
        visited[value] = true;
        LinkedList temp = adjacencyList[value];
        while(temp != null){
            if(visited[temp.val] == false){
                DFS(adjacencyList, visited, temp.val, count+1);
            }
            temp = temp.next;

        }

    }

}
