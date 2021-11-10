import java.util.Scanner;

public class StronglyConnectWithNewEdge {
    static int time = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        LinkedList[] adjacencyList = new LinkedList[n+1];
        for(int i =1; i<=n; i++){
            String vals = sc.nextLine();
            String[] nums = vals.split(" ");
            adjacencyList[i] = new LinkedList(Integer.parseInt(nums[0]));
            LinkedList temp = adjacencyList[i];
            for(int j = 1; j<nums.length;j++){
                temp.next = new LinkedList(Integer.parseInt(nums[j]));
                temp = temp.next;
            }
        }
        boolean[] seen = new boolean[n+1];
        int[] fin = new int[n+1];
        for(int i = 1; i<=n; i++){
            seen[i] = false;
            fin[i] = 0;
        }

        StronglyConnectWithNewEdge program = new StronglyConnectWithNewEdge();
        int oof = 0;
        int[] stack = new int[n+1];
        for(int i = 1; i<=n; i++){
            if(seen[i] == false){
                program.DFS(adjacencyList, seen, i, fin, stack);
            }
        }
        LinkedList[] gt = new LinkedList[n+1];
        for(int i = 1; i<=n; i++){
            LinkedList temp = adjacencyList[i];
            while(temp.val!=0){
                if(gt[temp.val]==null){
                    gt[temp.val] = new LinkedList(i);
                }else{
                    gt[temp.val].add(i);
                }
                temp = temp.next;
            }

        }
        for(int i = 1; i<=n; i++){
            if(gt[i]==null){
                gt[i] = new LinkedList(0);
            }else{
                gt[i].add(0);
            }


        }
        for(int i = 1; i<=n; i++){
            seen[i] = false;
        }
        int comp = 0;
        int last = 0;
        for(int i = 1; i<=n; i++){
            if(seen[stack[i]] == false){
                program.outputDFS(gt, seen, stack[i]);
                last = stack[i];
                comp++;
            }

        }
        for(int i = 1; i<=n; i++){
            seen[i] = false;
        }
        boolean[] vis = new boolean[n+1];
        boolean[] visfirst = new boolean[n+1];
        program.outputDFS(adjacencyList, vis, last);

        program.outputDFS(gt, visfirst, stack[1]);
        int min = Integer.MAX_VALUE;
        int minfirst = Integer.MAX_VALUE;
        for(int i =1; i<=n; i++){
            if(visfirst[i] && minfirst>i){
                minfirst = i;
            }else if(vis[i]==true && min>i){
                min = i;
            }
        }
        comp = 0;
        LinkedList temp = gt[minfirst];
        while(temp.val !=0){
            temp = temp.next;
        }
        temp.val = min;
        temp.add(0);
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
    public void outputDFS(LinkedList[] adjacencyList, boolean[] visited, int value){
        visited[value] = true;
        LinkedList temp = adjacencyList[value];
        while(temp.val != 0){
            if(visited[temp.val] == false){
                outputDFS(adjacencyList, visited, temp.val);
            }
            temp = temp.next;

        }


    }

    public void smallestDFS(LinkedList[] adjacencyList, boolean[] visited, int value, int smallest){
        visited[value] = true;
        LinkedList temp = adjacencyList[value];
        while(temp.val != 0){
            if(visited[temp.val] == false){
                outputDFS(adjacencyList, visited, temp.val);
            }
            temp = temp.next;

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
