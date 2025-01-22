

# Graph Colouring Using Simulated Annealing

This program implements a solution to the graph colouring problem using Simulated Annealing (SA). The program generates a random graph, assigns random colours to the graph’s nodes, and then uses the
simulated annealing technique to find the optimal colouring with the least number of conflicts (where two adjacent nodes share the same colour).

The graph is represented by an adjacency matrix, where 1 indicates an edge between two nodes and 0 indicates no edge. The program uses a 3-colour system for the nodes and tries to minimise the cost,
which is defined as the number of adjacent nodes that share the same colour.   

The program will generate a random graph, attempt to colour it using simulated annealing, and display the graph's adjacency matrix, the final colouring result, and the total cost.



## Algorithm Details

The program uses SA to perform a probabilistic search for the optimal solution. In each iteration:

- A random node is selected.
- The colour of the node is changed to one of two new colours.
- The cost of the new colouring is calculated. If the new solution has a lower cost or the same cost, it is accepted.
- If the new solution has a higher cost, it is accepted with a certain probability based on the temperature and the difference in cost.
- The temperature decreases over time, reducing the likelihood of accepting worse solutions.


- The number of iterations and the temperature decay factor can be adjusted for experimentation and performance tuning.
- The program currently uses a fixed size of 50 nodes for the graph, but this can be modified.



