import java.util.*;

public class GraphImplementation implements Graph {

    int vertices;
    int adjMatrix[][];

    public GraphImplementation(int v)
    {
        vertices = v;
        adjMatrix = new int[vertices][vertices];
        int[] vertexID = new int[vertices];
    }

    public void addEdge(int src, int tar)
    {
        //add edge from src to tar
        //make the matrix spot [src][tar] == 1
        adjMatrix[src][tar] = 1;
        //graph is directed so we do not need to add edge from tar to src
    }

    public int[] neighbors(int vertex)
    {
        int[] neighbors = new int[vertices]; // create an array to be returned
        int counter = 0; // how I go through the array to put it in the first indices
        for (int i = 0; i < vertices; i++)
        {
            if (adjMatrix[i][vertex] == 1) // if there is an edge
            {
                neighbors[counter] = i; // add it into the neighbors
                counter++;
            }
        }
        return neighbors; // return the array of neighbors
    }

    public List<Integer> topologicalSort()
    {
        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<Integer>(); // create the stack to return
        for (int i = 0; i < vertices; i++)
        {
            visited[i] = false; // set all of them to false
        }
        for (int i = 0; i < vertices; i++)
        {
            if (!visited[i]) // if we have not visited
            {
                topologicalSort(i, visited, stack); // call the real sort
            }
        }

        return stack; // return the stack that is sorted
    }

    private void topologicalSort(int start, boolean[] visited, Stack<Integer> stack)
    {
        visited[start] = true; // we have now visited
        int[] copy = neighbors(start); // this was to make the next line easier
        Iterator<Integer> iter = Arrays.stream(copy).iterator(); // create an iterator
        while (iter.hasNext())
        {
            int i = iter.next(); // get the next in the neighbors
            if (!visited[i]) // if we have not visited
                topologicalSort(i, visited, stack); // recursively call the next
        }

        stack.push(new Integer(start)); // push the next int onto the stack
    } // end function

}