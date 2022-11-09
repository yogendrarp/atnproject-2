#Run on jupyter
#https://jupyter.org/try-jupyter/retro/notebooks/?path=notebooks/Intro.ipynb

import matplotlib.pyplot as plt
import networkx as networkx
adjacency_matrix = [[0,0,1,1,0,0,0,0,0,0,0,1,1,0,0],[0,0,0,0,0,1,0,0,1,0,1,1,0,1,0],[1,0,0,1,0,0,0,0,0,0,0,0,1,0,1],[1,0,1,0,0,0,0,0,0,0,0,1,1,0,1],[0,0,0,0,0,0,0,1,0,1,1,0,0,0,1],[0,1,0,0,0,0,1,1,1,0,1,0,0,1,0],[0,0,0,0,0,1,0,1,1,1,0,0,0,0,0],[0,0,0,0,1,1,1,0,0,1,1,0,0,0,0],[0,1,0,0,0,1,1,0,0,1,0,0,0,0,0],[0,0,0,0,1,0,1,1,1,0,0,0,0,0,0],[0,1,0,0,1,1,0,1,0,0,0,0,0,1,0],[1,1,0,1,0,0,0,0,0,0,0,0,0,1,0],[1,0,1,1,0,0,0,0,0,0,0,0,0,0,1],[0,1,0,0,0,1,0,0,0,0,1,1,0,0,0],[0,0,1,1,1,0,0,0,0,0,0,0,1,0,0]]
edges = [[] for i in range(15)]
graph = networkx.Graph()
for i in range(15):
    for j in range(15):
        if(adjacency_matrix[i][j]==1):
            graph.add_node(i)
            graph.add_node(j)
            graph.add_edge(i,j)
networkx.draw(G,with_labels=True, node_color="cyan",  edge_color="tab:orange")
plt.show()