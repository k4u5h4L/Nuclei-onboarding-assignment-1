package assignment3.services;

import assignment3.constants.Constants;
import assignment3.exceptions.CyclicDependencyException;
import assignment3.exceptions.DependencyNotFoundException;
import assignment3.exceptions.NodeAlreadyExistsException;
import assignment3.exceptions.NodeNotFoundException;
import assignment3.models.GraphModel;
import assignment3.models.NodeModel;
import assignment3.utils.GraphUtils;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphService {

  final static Logger logger = LoggerFactory.getLogger(GraphService.class);

  /**
   * Function which returns the immediate parents of the given node
   *
   * @param id    ID of the node
   * @param graph The graph
   * @return Set of immediate parents to the node
   * @throws NodeNotFoundException If the given node is not found in the graph
   */
  public HashSet<Integer> getImmediateParents(int id, GraphModel graph)
      throws NodeNotFoundException {
    if (!GraphUtils.isNodePresentAlready(id, graph)) {
      throw new NodeNotFoundException(Constants.NODE_NOT_PRESENT_IN_GRAPH);
    }

    return GraphUtils.getNodeFromNodeSet(id, graph).getParents();
  }

  /**
   * Function which returns the immediate children of the given node
   *
   * @param id    ID of the node
   * @param graph The graph
   * @return Set of immediate children to the node
   * @throws NodeNotFoundException If the given node is not found in the graph
   */
  public HashSet<Integer> getImmediateChildren(int id, GraphModel graph)
      throws NodeNotFoundException {
    if (!GraphUtils.isNodePresentAlready(id, graph)) {
      throw new NodeNotFoundException(Constants.NODE_NOT_PRESENT_IN_GRAPH);
    }

    return GraphUtils.getNodeFromNodeSet(id, graph).getChildren();
  }

  /**
   * Function which simply adds a standalone node to the graph
   *
   * @param id    ID of the node (must be unique)
   * @param name  Name given to the node
   * @param graph The graph to add the node to
   * @throws NodeAlreadyExistsException A node with similar ID should not exist
   */
  public void addNode(int id, String name, GraphModel graph) throws NodeAlreadyExistsException {
    if (GraphUtils.isNodePresentAlready(id, graph)) {
      throw new NodeAlreadyExistsException(Constants.NODE_ALREADY_PRESENT_IN_GRAPH);
    }

    graph.getNodes().add(new NodeModel(id, name));
  }

  /**
   * Function which adds a a dependency between the parent node and child node
   *
   * @param parentId ID of the parent node
   * @param childId  ID of the child node
   * @param graph    The graph
   */
  public void addDependency(int parentId, int childId, GraphModel graph)
      throws CyclicDependencyException, NodeNotFoundException {
    if (!GraphUtils.isNodePresentAlready(parentId, graph) || !GraphUtils.isNodePresentAlready(
        childId, graph)) {
      throw new NodeNotFoundException(Constants.NODE_NOT_PRESENT_IN_GRAPH);
    } else if (GraphUtils.checkIfCycleExists(parentId, childId, graph)) {
      throw new CyclicDependencyException(Constants.CYCLE_WILL_APPEAR);
    }

    NodeModel parentNode = new NodeModel(1, "a");
    NodeModel childNode = new NodeModel(2, "b");

    for (NodeModel node : graph.getNodes()) {
      if (node.getId() == parentId) {
        parentNode = node;
      } else if (node.getId() == childId) {
        childNode = node;
      }
    }

    parentNode.addChild(childId);
    childNode.addParent(parentId);
  }

  /**
   * Function which deletes a dependency between a parent node and a child node
   *
   * @param parentId ID of the parent node
   * @param childId  ID of the child node
   * @param graph    The graph
   * @throws NodeNotFoundException       If the at least one of the nodes is not found in the graph
   * @throws DependencyNotFoundException If the dependency does not exist
   */
  public void deleteDependency(int parentId, int childId, GraphModel graph)
      throws NodeNotFoundException, DependencyNotFoundException {
    if (!GraphUtils.isNodePresentAlready(parentId, graph) || !GraphUtils.isNodePresentAlready(
        childId, graph)) {
      throw new NodeNotFoundException(Constants.NODE_NOT_PRESENT_IN_GRAPH);
    }

    NodeModel parentNode = GraphUtils.getNodeFromNodeSet(parentId, graph);
    NodeModel childNode = GraphUtils.getNodeFromNodeSet(childId, graph);

    if (!parentNode.getChildren().contains(childId) || !childNode.getParents().contains(parentId)) {
      throw new DependencyNotFoundException(Constants.DEPENDENCY_NOT_FOUND);
    }

    parentNode.getChildren().remove(childId);
    childNode.getParents().remove(parentId);
  }

  /**
   * Function which deletes a node from the graph. In turn, it's dependencies will also be deleted
   *
   * @param id    ID of the node to delete
   * @param graph The graph to delete it from
   * @throws NodeNotFoundException If the node specified is not found
   */
  public void deleteNode(int id, GraphModel graph) throws NodeNotFoundException {
    if (!GraphUtils.isNodePresentAlready(id, graph)) {
      throw new NodeNotFoundException(Constants.NODE_NOT_PRESENT_IN_GRAPH);
    }

    NodeModel nodeToDelete = GraphUtils.getNodeFromNodeSet(id, graph);

    graph.getNodes().remove(nodeToDelete);

    for (NodeModel node : graph.getNodes()) {
      if (node.getParents().contains(id)) {
        node.getParents().remove(id);
      } else if (node.getChildren().contains(id)) {
        node.getChildren().remove(id);
      }
    }
  }
}
