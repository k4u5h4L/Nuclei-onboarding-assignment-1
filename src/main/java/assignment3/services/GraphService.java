package assignment3.services;

import assignment3.constants.Constants;
import assignment3.exceptions.CyclicDependencyException;
import assignment3.exceptions.DependencyNotFoundException;
import assignment3.exceptions.NodeAlreadyExistsException;
import assignment3.exceptions.NodeNotFoundException;
import assignment3.models.GraphModel;
import assignment3.models.NodeModel;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphService {

  final static Logger logger = LoggerFactory.getLogger(GraphService.class);

  /**
   * Function which determines if node already exists in the graph
   *
   * @param id    ID of the node to insert
   * @param nodes The graph
   * @return True is node is already present, false otherwise
   */
  public boolean isNodePresentAlready(int id, GraphModel nodes) {
    for (NodeModel node : nodes.getNodes()) {
      if (node.getId() == id) {
        return true;
      }
    }

    return false;
  }

  /**
   * Funcion to get the NodeModel object given the ID of the node
   *
   * @param id    ID of the node
   * @param graph The graph
   * @return The NodeModel object having the corresponding ID
   * @throws NodeNotFoundException If given ID is not found
   */
  public NodeModel getNodeFromNodeSet(int id, GraphModel graph) throws NodeNotFoundException {
    if (!isNodePresentAlready(id, graph)) {
      throw new NodeNotFoundException(Constants.NODE_NOT_PRESENT_IN_GRAPH);
    }

    NodeModel result = new NodeModel(1, "a");

    for (NodeModel node : graph.getNodes()) {
      if (id == node.getId()) {
        result = node;
        break;
      }
    }

    return result;
  }

  /**
   * Function to check if cycle will occue in the graph if a dependency arises between given
   * parent and child node.
   *
   * @param parentId ID of the parent node
   * @param childId  ID of the child node
   * @param graph    The graph
   * @return True if cycle will occur, false otherwise
   */
  public boolean checkIfCycleExists(int parentId, int childId, GraphModel graph) {
    HashSet<Integer> acestorsOfParent = new HashSet<>();
    try {
      acestorsOfParent = getAncestors(parentId, graph);
    } catch (NodeNotFoundException e) {
      logger.error(Constants.NODE_NOT_PRESENT_IN_GRAPH);
    }

    return acestorsOfParent.contains(childId);
  }


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
    if (!isNodePresentAlready(id, graph)) {
      throw new NodeNotFoundException(Constants.NODE_NOT_PRESENT_IN_GRAPH);
    }

    return getNodeFromNodeSet(id, graph).getParents();
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
    if (!isNodePresentAlready(id, graph)) {
      throw new NodeNotFoundException(Constants.NODE_NOT_PRESENT_IN_GRAPH);
    }

    return getNodeFromNodeSet(id, graph).getChildren();
  }

  /**
   * Function which returns the ancestors of the given node
   *
   * @param id    ID of the node
   * @param graph The graph
   * @return Set of acnestors to the node
   * @throws NodeNotFoundException If the given node is not found in the graph
   */
  public HashSet<Integer> getAncestors(int id, GraphModel graph) throws NodeNotFoundException {
    if (!isNodePresentAlready(id, graph)) {
      throw new NodeNotFoundException(Constants.NODE_NOT_PRESENT_IN_GRAPH);
    }

    HashSet<Integer> ancestors = new HashSet<>();

    NodeModel node = getNodeFromNodeSet(id, graph);

    ancestorsRecursive(node, ancestors, graph);

    return ancestors;
  }

  /**
   * Helper function which recursively searches the ancestors of a given node
   *
   * @param node      NodeModel obejct of a given node
   * @param ancestors The output Set of ancestors
   * @param graph     The graph
   */
  private void ancestorsRecursive(NodeModel node, HashSet<Integer> ancestors, GraphModel graph) {
    ancestors.addAll(node.getParents());

    try {
      for (Integer parent : node.getParents()) {
        ancestorsRecursive(getNodeFromNodeSet(parent, graph), ancestors, graph);
      }
    } catch (NodeNotFoundException e) {
      logger.error(Constants.ERROR_IN_GETTING_ANCESTORS);
    }
  }

  /**
   * FUnction which returns the descendents of the given node
   *
   * @param id    ID of the node
   * @param graph The graph
   * @return Set of descendents to the node
   * @throws NodeNotFoundException If the given node is not found in the graph
   */
  public HashSet<Integer> getDescendents(int id, GraphModel graph) throws NodeNotFoundException {
    if (!isNodePresentAlready(id, graph)) {
      throw new NodeNotFoundException(Constants.NODE_NOT_PRESENT_IN_GRAPH);
    }

    HashSet<Integer> descendents = new HashSet<>();

    NodeModel node = getNodeFromNodeSet(id, graph);

    descendentsRecursive(node, descendents, graph);

    return descendents;
  }

  /**
   * Helper function which recursively searches the descendents of a given node
   *
   * @param node        NodeModel obejct of a given node
   * @param descendents The output Set of descendents
   * @param graph       The graph
   */
  private void descendentsRecursive(NodeModel node, HashSet<Integer> descendents,
                                    GraphModel graph) {
    descendents.addAll(node.getChildren());

    try {
      for (Integer parent : node.getChildren()) {
        descendentsRecursive(getNodeFromNodeSet(parent, graph), descendents, graph);
      }
    } catch (NodeNotFoundException e) {
      logger.error(Constants.ERROR_IN_GETTING_DESCENDENTS);
    }
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
    if (isNodePresentAlready(id, graph)) {
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
    if (!isNodePresentAlready(parentId, graph) || !isNodePresentAlready(childId, graph)) {
      throw new NodeNotFoundException(Constants.NODE_NOT_PRESENT_IN_GRAPH);
    } else if (checkIfCycleExists(parentId, childId, graph)) {
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
    if (!isNodePresentAlready(parentId, graph) || !isNodePresentAlready(childId, graph)) {
      throw new NodeNotFoundException(Constants.NODE_NOT_PRESENT_IN_GRAPH);
    }

    NodeModel parentNode = getNodeFromNodeSet(parentId, graph);
    NodeModel childNode = getNodeFromNodeSet(childId, graph);

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
    if (!isNodePresentAlready(id, graph)) {
      throw new NodeNotFoundException(Constants.NODE_NOT_PRESENT_IN_GRAPH);
    }

    NodeModel nodeToDelete = getNodeFromNodeSet(id, graph);

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
