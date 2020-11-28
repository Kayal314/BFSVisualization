import javax.swing.*;
import java.util.LinkedList;

class Graph {
    LinkedList<Node>[] adjList; // adjacency List representation
    Graph(int n)
    {
        adjList=new LinkedList[n];
        for(int i=0;i<n;i++) {
            adjList[i] = new LinkedList<>();
            adjList[i].add(new Node(i));
        }
    }
    void addEdge(int v,int w)
    {
        adjList[v].add(adjList[w].get(0));
        adjList[w].add(adjList[v].get(0));
    }
}
class Node
{
    JButton button; // representative button
    boolean explored;
    int val;
    Node(int val)
    {
        this.val=val;
        explored=false;
    }
}
