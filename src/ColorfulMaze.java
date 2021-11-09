import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class ColorfulMaze {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int s = sc.nextInt();
        int t = sc.nextInt();
        LinkedListColor[] adjacencyList = new LinkedListColor[n + 1];
        for(int i = 1; i<=m; i++){
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int col = sc.nextInt();
            if (adjacencyList[v1] == null) {
                adjacencyList[v1] = new LinkedListColor(v2, col);
            }else{
                adjacencyList[v1].add(v2, col);
            }
            if(adjacencyList[v2] == null){
                adjacencyList[v2] = new LinkedListColor(v1, col);
            }else{
                adjacencyList[v2].add(v1, col);
            }

        }
        boolean[] seen = new boolean[n+1];

        int[] dist = new int[n+1];
        for(int i =1; i<=n; i++){
            seen[i] = false;
            dist[i] = Integer.MAX_VALUE;
        }
     //   dist[s] = 0;
       // ColorfulMaze program = new ColorfulMaze();
        //program.longDFS(adjacencyList,seen, s, dist, 0,t);
     //   System.out.println(dist[t]);
//
//        int[] stack = new int[n+1];
//        int[] stackColor = new int[n+1];
//        int top = 0;
//        stack[top] = s;
//        stackColor[top] = 1;
//        while(top>=0){
//            seen[stack[top]] = true;
//            LinkedListColor temp = adjacencyList[stack[top]];
//            while(temp != null){
//                if(seen[temp.val] == false && temp.color == (stackColor[top]%3)+1){
//                    seen[temp.val] = true;
//                    top++;
//                    stack[top] = temp.val;
//                    stackColor[top] = temp.color;
//                    if(dist[temp.val] > dist[stack[top-1]]+1){
//                        dist[temp.val] = dist[stack[top-1]]+1;
//                    }
//                }
//                temp = temp.next;
//
//            }
//
//            top--;
//        }
        boolean[][] seenedges = new boolean[n+m+2][n+m+2];

        int beg = 1;
        int end = 2;
        int[] queue = new int[3*(m+1)];
        queue[1] = s;
        seen[s] = true;
        int[][] distedges = new int[n+1][n+1];
        dist[s]=0;
        int[] queueColor = new int[3*(m+1)];
        queueColor[1] = 3;
        int mindist = Integer.MAX_VALUE;
        int[] queuedist = new int[3*(m+1)];
        while(beg<end){
            int head = queue[beg];
            int headcolor = queueColor[beg];
            int prevdist = queuedist[beg];
            LinkedListColor temp = adjacencyList[head];
            while(temp!= null){
                if((seenedges[head][temp.val] == false)&& temp.color == (headcolor%3)+1){

                    queue[end] = temp.val;
                    queueColor[end] = temp.color;
                    queuedist[end] = prevdist+1;
                    distedges[head][temp.val] = prevdist+1;
                    seenedges[head][temp.val] = true;
                    end++;
                    if(temp.val == t && mindist> distedges[head][temp.val] && temp.color == 3){
                        mindist = distedges[head][temp.val];
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
//    public void longDFS(LinkedListColor[] adjacencyList, boolean[] seen, int value, int[] dist, int prevcolor, int t){
//        seen[value] = true;
//        LinkedListColor temp = adjacencyList[value];
//        while(temp != null){
//            if(seen[temp.val] == false && temp.color == (prevcolor%3)+1){
//                seen[temp.val] = true;
//                if(temp.val == t ){
//                    if(temp.color == 3){
//                        dist[t] = dist[value]+1;
//                    }
//                }else{
//                    if(dist[temp.val] > dist[value]+1){
//                        dist[temp.val] = dist[value]+1;
//                    }
//                    longDFS(adjacencyList, seen, temp.val, dist, temp.color, t);
//                }
//
//
//
//            }
//
//            temp = temp.next;
//
//
//
//
//        }
//        seen[value]= false;
//
//
//    }

}
