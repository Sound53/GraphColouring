import java.util.*;
import java.util.Random;

public class GraphColouring {
  public static void main(String[] args) {
    
    int[][] graphh = graph(50,2.5);
    
     for (int i = 0; i < graphh.length; i++) {
            for (int j = 0; j < graphh[i].length; j++) {
                System.out.print(graphh[i][j] + " "); // Print graph
            }
            System.out.println(); 
        }
       HashMap<Integer, Integer> map = AnnealingColour(graphh);
        System.out.println(map);   //final colouring

        System.out.println(cost(graphh, map)); //final cost
    }
  

    public static int[][] graph(int graphsize, double avgdeg ) {  //method to create graph 

    int[][] graph = new int[graphsize][graphsize];  // Create a 2d array to regresent graph 
    double prob = (double)avgdeg/(graphsize-1);      //probability of any edge being present 
  

    for(int i=0;i<graphsize;i++){
        for(int j=i;j<graphsize;j++){
            if(i==j){
                graph[i][j]=0;            
            }
            else{
                double randomNumber = Math.random();        //traversing through edges to see if they are present 
                if(randomNumber<=prob){
                    graph[i][j]=1;
                    graph[j][i]=1;
                }
                else{
                    graph[i][j]=0;
                    graph[j][i]=0;
                }
            }

        }
    }


    return graph;
  }

  public static int cost(int[][] graph, HashMap<Integer, Integer> colouring){ //method that calculates the cost of a colouring 
    int cost =0;

    for (int i = 0; i < graph.length; i++) {
            for (int j = i; j < graph[i].length; j++) {
                if(graph[i][j]==1 && colouring.get(i) ==colouring.get(j)){ //traversing through edges and counts edges connecting nodes with the same colour 
                    cost++;
                }
            }
            
        }
    


    return cost;
  }


    public static HashMap<Integer, Integer> AnnealingColour(int[][] graph) {  //method to conduct SA
    HashMap<Integer, Integer> colouring = new HashMap<>();
    HashMap<Integer, Integer> best = new HashMap<>();
    Random random = new Random();

    
    for (int i = 0; i < graph.length; i++) {
        colouring.put(i, random.nextInt(3));  //  add random colour to each node 
    }

    
    best = new HashMap<>(colouring);     // Create a copy of colouring for best solution
    int cost = cost(graph, colouring);
    int bestCost = cost(graph, best);

    
    if (cost == 0) {
        return colouring;           // If the initial solution already has zero cost, return it
    }

    double temp = 100.0;

    // Annealing loop
    for (int i = 0; i < 1000000; i++) {
        
        HashMap<Integer, Integer> copy = new HashMap<>(colouring);

        int node = random.nextInt(graph.length);  // Randomly pick a node to create neighbouring solution 

        
        int color1 = (colouring.get(node) + 1) % 3;  
        int color2 = (colouring.get(node) + 2) % 3;  

        
        copy.put(node, random.nextBoolean() ? color1 : color2);

        
        int neighborCost = cost(graph, copy);

        
        if (neighborCost <= cost) {             // If the new coloring is better (or the same), accept it
            colouring = copy;
            cost = neighborCost;

            
            if (neighborCost < bestCost) {      // Update the best coloring if the new one is better
                best = copy;
                bestCost = neighborCost;
            }
        } 
        
        else if (Math.random() <= Math.exp(-(neighborCost - cost) / temp)) {    // If not, accept it with some probability based on temperature
            colouring = copy;
            cost = neighborCost;
        }

        
        if (cost == 0) {
            return colouring;
        }

        
        temp *= 0.99999;     // Gradually decrease the temperature
    }

    // Return the best coloring found
    return best;
}




}