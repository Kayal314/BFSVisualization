import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainFrame
{
    int moveCount;
    int startVertex;
    Node startNode;
    Node endNode;
    JPanel panel;
    JSlider speedControl;
    Graph graph;
    JButton searchButton;
    MainFrame()
    {
        moveCount=1;
        startVertex=-1;
        searchButton=null;
        startNode=null;
        speedControl=null;
        endNode=null;
        panel=new JPanel();
        graph=new Graph(792);
    }
    public static void main(String[] args)
    {
        MainFrame mainFrame=new MainFrame();
        mainFrame.initializeFrame();
    }
    void addButtons() // adding the nodes as buttons
    {
        int count=0;
        int i,j;
        //792 buttons/nodes
        for (j=20; j < 900; j += 40)
        {
            for(i=20;i<1460;i+=40){
                JButton button = new JButton();
                button.setBounds(i, j, 40, 40);
                button.setBackground(Color.WHITE);
                graph.adjList[count].get(0).button=button;
                int finalCount = count;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(moveCount==1)
                        {
                            button.setBackground(new Color(90, 55, 166));
                            startVertex=finalCount;
                            startNode=graph.adjList[finalCount].get(0);
                        }
                        else if(moveCount==2)
                        {
                            button.setBackground(new Color(255, 31, 31));
                            endNode=graph.adjList[finalCount].get(0);
                            searchButton.setBackground(new Color(50, 155, 168));
                        }
                        else
                        {
                            if(button.getBackground().equals(Color.WHITE)) // for creating 'walls'
                                button.setBackground(Color.BLACK);
                            else if(button.getBackground().equals(Color.BLACK)) // for erasing 'walls'
                                button.setBackground(Color.WHITE);
                        }
                        moveCount++;

                    }
                });
                count++;
                panel.add(button);

            }
        }

    }
    void addControls()
    {
        searchButton= new JButton("SEARCH");
        searchButton.setBackground(Color.GRAY);
        searchButton.setBounds(680,920,140,50);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int delay=310-speedControl.getValue(); // delay in milliseconds
                if(startNode!=null && endNode!=null)
                    new BFS().search(graph, startVertex, endNode, delay);

            }
        });
        panel.add(searchButton);
    }
    void initializeGraph() //adding the edges by connected the different nodes present as buttons
    {
        int i,j;
        //do the vertical attachments
        for(i=0;i<36;i++)
            for(j=0;j<21;j++)
                graph.addEdge(i+36*j,i+36*(j+1));
        //do the horizontal attachments
        for(j=0;j<35;j++)
            for(i=0;i<22;i++)
                graph.addEdge(36*i+j,36*i+j+1);

    }
    void initializeFrame()
    {
        JFrame frame=new JFrame("MainFrame");
        frame.setSize(1560,1020);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        frame.add(panel);
        addButtons();
        initializeGraph();
        addControls();
        speedControl=new JSlider(JSlider.VERTICAL,0,300,250);
        speedControl.setBounds(1490,230,30,400); // for speeding up/down the visualization
        panel.add(speedControl);
        JLabel speedLabel=new JLabel("SPEED");
        speedLabel.setBounds(1485,170,70,50);
        panel.add(speedLabel);
        frame.setVisible(true);

    }
}
