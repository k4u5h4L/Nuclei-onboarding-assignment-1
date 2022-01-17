package assignment3.utils;

import assignment3.constants.Constants;
import assignment3.exceptions.NodeNotFoundException;
import assignment3.models.GraphModel;
import assignment3.models.NodeModel;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphUtils {

  final static Logger logger = LoggerFactory.getLogger(GraphUtils.class);

  /**
   * Function which determines if node already exists in the graph
   *
   * @param id    ID of the node to insert
   * @param nodes The graph
   * @return True is node is already present, false otherwise
   */
  public static boolean isNodePresentAlready(int id, GraphModel nodes) {
    for (NodeModel node : nodes.getNodes()) {
      if (node.getId() == id) {
        return true;
      }
    }

    return false;
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
  public static boolean checkIfCycleExists(int parentId, int childId, GraphModel graph) {
    HashSet<Integer> acestorsOfParent = new HashSet<>();
    try {
      acestorsOfParent = getAncestors(parentId, graph);
    } catch (NodeNotFoundException e) {
      logger.error(Constants.NODE_NOT_PRESENT_IN_GRAPH);
    }

    return acestorsOfParent.contains(childId);
  }

  /**
   * Funcion to get the NodeModel object given the ID of the node
   *
   * @param id    ID of the node
   * @param graph The graph
   * @return The NodeModel object having the corresponding ID
   * @throws NodeNotFoundException If given ID is not found
   */
  public static NodeModel getNodeFromNodeSet(int id, GraphModel graph)
      throws NodeNotFoundException {
    if (!GraphUtils.isNodePresentAlready(id, graph)) {
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
   * Function which returns the ancestors of the given node
   *
   * @param id    ID of the node
   * @param graph The graph
   * @return Set of acnestors to the node
   * @throws NodeNotFoundException If the given node is not found in the graph
   */
  public static HashSet<Integer> getAncestors(int id, GraphModel graph)
      throws NodeNotFoundException {
    if (!GraphUtils.isNodePresentAlready(id, graph)) {
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
  private static void ancestorsRecursive(NodeModel node, HashSet<Integer> ancestors,
                                         GraphModel graph) {
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
  public static HashSet<Integer> getDescendents(int id, GraphModel graph)
      throws NodeNotFoundException {
    if (!GraphUtils.isNodePresentAlready(id, graph)) {
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
  private static void descendentsRecursive(NodeModel node, HashSet<Integer> descendents,
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
}
