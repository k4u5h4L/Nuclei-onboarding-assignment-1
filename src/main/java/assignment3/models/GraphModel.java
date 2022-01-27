package assignment3.models;

import java.util.HashSet;

public class GraphModel {

  private HashSet<NodeModel> nodes;
  private NodeModel root;

  public GraphModel(final NodeModel root) {
    this.root = root;
    this.nodes = new HashSet<>();
    this.nodes.add(root);
  }

  public HashSet<NodeModel> getNodes() {
    return nodes;
  }

  public void setNodes(final HashSet<NodeModel> nodes) {
    this.nodes = nodes;
  }

  public NodeModel getRoot() {
    return root;
  }

  public void setRoot(final NodeModel root) {
    this.root = root;
  }
}
