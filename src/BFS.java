import java.awt.*;
import java.util.LinkedList;

class BFS {
    void search(Graph graph, int startIndex,Node endNode,int delay)  {
        LinkedList<Node> queue=new LinkedList<>();
        Node s=graph.adjList[startIndex].get(0);
        s.explored=true;
        Color pink=new Color(230, 149, 221);
        queue.add(s);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(queue.size()>0)
                {
                    int v=queue.remove().val;
                    for(int i=1;i<graph.adjList[v].size();i++)
                    {
                        Node w=graph.adjList[v].get(i);
                        if(!w.explored && !(w.button.getBackground()).equals(Color.BLACK))
                        {
                            try {
                                Thread.sleep(delay);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            queue.add(w);
                            w.explored=true;
                            if(w==endNode)
                                return;
                            w.button.setBackground(pink);
                        }
                    }
                }
            }
        }).start();

    }
}
