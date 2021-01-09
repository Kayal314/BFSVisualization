import java.awt.*;

import java.util.LinkedList;

class BFS {
    void search(Graph graph, int startIndex,Node endNode,int delay)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int[] pred = new int[graph.adjList.length];
                LinkedList<Node> queue=new LinkedList<>();
                Node s=graph.adjList[startIndex].get(0);
                s.explored=true;
                Color pink=new Color(230, 149, 221);
                queue.add(s);
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
                            pred[w.val] = v;
                            queue.add(w);
                            w.explored=true;
                            if(w==endNode)
                            {
                                tracePath(graph, pred, startIndex,endNode.val, delay);
                                return;
                            }
                            w.button.setBackground(pink);
                        }
                    }
                }
            }
        }).start();
    }
    void tracePath(Graph graph, int[] pred, int startIndex, int endIndex, int delay)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = endIndex;
                int red = 140;
                graph.adjList[endIndex].get(0).button.setBackground(new Color(red, 6, 83));
                do
                {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    red = (red+2)%255;
                    i = pred[i];
                    graph.adjList[i].get(0).button.setBackground(new Color(red, 6, 83));
                } while (i!=startIndex);
            }
        }).start();

    }
}
