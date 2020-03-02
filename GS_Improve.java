import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class GS_Improve {
    /**/
    static int N = 5;

    //function for engement
    public static int[] Engaements(int[][] menPreferTable, int[][] womenPreferTable) {
        //list of free man
        Queue<Integer> freeMen = new LinkedList<Integer>();
        for (int i = 0; i < N; i++) {
            freeMen.add(i); //intial add all free man in the queue
        }

        int[] wife = new int[N];  //wife match table
        for (int i = 0; i < N; i++) {
            wife[i] = -1; //initial every women is free now
        }
        int[] husband = new int[N]; //husband math table
        for (int i = 0; i < N; i++) {
            husband[i] = -1; //initial every women is free now
        }

        int[] count = new int[N];
        for (int i = 0; i < N; i++) {
            count[i] = 0; //initial every women is free now
        }

        while (!freeMen.isEmpty()) {
            int m; //wife index == men
            int w; //husband index ==women

            m = freeMen.peek(); //man 0

            for (int i = 0; i < N && (wife[m] == -1); i++) {
                //check if women is free.
                w = menPreferTable[m][i + count[m]];

                if (husband[w] == -1) { //if women is free
                    wife[m] = w; //assign to the each ot    her
                    husband[w] = m;
                    freeMen.poll();
                    count[m] = count[m] + 1;
                } else {
                    int m1 = husband[w];
                    if (womenChoice(womenPreferTable, w, m, m1) == true) {
                        wife[m] = w;
                        husband[w] = m;
                        wife[m1] = -1;
                        freeMen.poll();
                        freeMen.add(m1);
                        count[m] = count[m] + 1;
                    }
                }
            }
        }



       /* System.out.print("The Match table is: \nMen  Women \n");
        for (int i = 0; i < N; i++) {
            System.out.println(i + "    " + husband[i]);

        }*/
        return husband;
    }


    public static boolean womenChoice(int[][] womenPreferTable, int w, int m, int m1) {

        int[] inverse = new int[N];
        for (int i = 0; i < N; i++) {
            inverse[womenPreferTable[w][i]] = i;
            // System.out.print(inverse[i]+" ");
        }
        if (inverse[m] < inverse[m1]) {
            return true;
        }
        return false;

    }

    public static void main(String args[]) {


        //assume men are named 1...n, here we generate 5
        int[][] menPreferTable;

        //assume women are named 1...n, here we generate 5
        int[][] womenPreferTable;

        //initial the preference table
        menPreferTable = new int[][]{
                {1, 0, 3, 4, 2},
                {3, 1, 0, 2, 4},
                {1, 4, 2, 3, 0},
                {0, 3, 2, 1, 4},
                {1, 3, 0, 4, 2}
        };
        womenPreferTable = new int[][]{
                {4, 0, 1, 3, 2},
                {2, 1, 3, 0, 4},
                {1, 2, 3, 4, 0},
                {0, 4, 3, 2, 1},
                {3, 1, 4, 2, 0}
        };
        Map<Integer, String> menMap = new HashMap<Integer, String>();
        menMap.put(0, "Victor");
        menMap.put(1, "Wyatt");
        menMap.put(2, "Xavier");
        menMap.put(3, "Yancey");
        menMap.put(4, "Zeus");

        Map<Integer, String> womenMap = new HashMap<Integer, String>();
        womenMap.put(0, "Amy");
        womenMap.put(1,"Bertha");
        womenMap.put(2,"Clare");
        womenMap.put(3, "Diane");
        womenMap.put(4, "Erika");


      //  Engaements(menPreferTable, womenPreferTable);


        for (int i = 0; i < N; i++) {
            System.out.println(womenMap.get(i) + "   MATCH " + menMap.get(Engaements(menPreferTable, womenPreferTable)[i]));

        }
    }
}
