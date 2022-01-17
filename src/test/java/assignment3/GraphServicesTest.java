package assignment3;

import assignment3.exceptions.CyclicDependencyException;
import assignment3.exceptions.DependencyNotFoundException;
import assignment3.exceptions.NodeAlreadyExistsException;
import assignment3.exceptions.NodeNotFoundException;
import assignment3.models.GraphModel;
import assignment3.models.NodeModel;
import assignment3.services.GraphService;
import assignment3.utils.GraphUtils;

import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GraphServicesTest {

  GraphModel graph;
  GraphService graphService;

  @BeforeEach
  public void runBeforeEach() {
    graph = new GraphModel(new NodeModel(1, "A"));

    graph.getNodes().add(new NodeModel(2, "B"));
    graph.getNodes().add(new NodeModel(3, "C"));

    graphService = new GraphService();
  }

  @Test
  @DisplayName("Graph does not contain cycles")
  public void verifyCyclesNotInGraph() {
    Assertions.assertThrows(CyclicDependencyException.class, () -> {
      graphService.addDependency(1, 2, graph);
      graphService.addDependency(2, 3, graph);
      graphService.addDependency(3, 1, graph);
    });
  }

  @Test
  @DisplayName("Graph should contain nodes before adding dependency")
  public void verifyIfNodeIsNotPresent() {
    Assertions.assertThrows(NodeNotFoundException.class, () -> {
      graphService.addDependency(1, 4, graph);
    });
  }

  @Test
  @DisplayName("Graph is able to add new nodes")
  public void verifyGraphCanAddNodes() {
    Assertions.assertThrows(NodeAlreadyExistsException.class, () -> {
      graphService.addNode(1, "D", graph);
    });
  }

  @Test
  @DisplayName("Immediate parents of node are returned")
  public void verifyImmediateParentsOfChild() {
    try {
      graphService.addDependency(1, 2, graph);
      graphService.addDependency(2, 3, graph);
      graphService.addDependency(1, 3, graph);

      HashSet<Integer> nodes = new HashSet<>();
      nodes.add(1);
      nodes.add(2);

      Assertions.assertEquals(graphService.getImmediateParents(3, graph), nodes);
    } catch (CyclicDependencyException | NodeNotFoundException e) {
      Assertions.fail("Cycle found or node not found");
    }
  }

  @Test
  @DisplayName("Immediate children of node are returned")
  public void verifyImmediateChildrenOfParent() {
    try {
      graphService.addDependency(1, 2, graph);
      graphService.addDependency(2, 3, graph);
      graphService.addDependency(1, 3, graph);

      HashSet<Integer> nodes = new HashSet<>();
      nodes.add(2);
      nodes.add(3);

      Assertions.assertEquals(graphService.getImmediateChildren(1, graph), nodes);
    } catch (CyclicDependencyException | NodeNotFoundException e) {
      Assertions.fail("Cycle found or node not found");
    }
  }

  @Test
  @DisplayName("Ancestors of node are returned")
  public void verifyAncestorsOfNode() {
    try {
      graphService.addDependency(1, 2, graph);
      graphService.addDependency(2, 3, graph);

      HashSet<Integer> nodes = new HashSet<>();
      nodes.add(1);
      nodes.add(2);

      Assertions.assertEquals(GraphUtils.getAncestors(3, graph), nodes);
    } catch (CyclicDependencyException | NodeNotFoundException e) {
      Assertions.fail("Cycle found or node not found");
    }
  }

  @Test
  @DisplayName("Descendents of node are returned")
  public void verifyDescendentsOfNode() {
    try {
      graphService.addDependency(1, 2, graph);
      graphService.addDependency(2, 3, graph);

      HashSet<Integer> nodes = new HashSet<>();
      nodes.add(2);
      nodes.add(3);

      Assertions.assertEquals(GraphUtils.getDescendents(1, graph), nodes);
    } catch (CyclicDependencyException | NodeNotFoundException e) {
      Assertions.fail("Cycle found or node not found");
    }
  }

  @Test
  @DisplayName("Dependency can be deleted")
  public void verifyDependendyCanBeDeleted() {
    Assertions.assertThrows(DependencyNotFoundException.class, () -> {
      graphService.addDependency(1, 2, graph);
      graphService.addDependency(2, 3, graph);

      graphService.deleteDependency(3, 1, graph);
    });

    try {
      graphService.deleteDependency(2, 3, graph);
    } catch (DependencyNotFoundException | NodeNotFoundException e) {
      Assertions.fail("Dependency not found or node not found");
    }
  }

  @Test
  @DisplayName("Graph is able to delete nodes")
  public void verifyGraphCanDeleteNodes() {
    Assertions.assertThrows(NodeNotFoundException.class, () -> {
      graphService.deleteNode(4, graph);
    });

    try {
      graphService.addDependency(1, 2, graph);
      graphService.addDependency(2, 3, graph);

      graphService.deleteNode(2, graph);

      HashSet<Integer> nodeIds = new HashSet<>();

      nodeIds.add(1);
      nodeIds.add(3);

      HashSet<Integer> nodesFromGraph = new HashSet<>();

      for (NodeModel node : graph.getNodes()) {
        nodesFromGraph.add(node.getId());
      }

      Assertions.assertEquals(nodesFromGraph, nodeIds);
    } catch (CyclicDependencyException | NodeNotFoundException e) {
      Assertions.fail("Cycle present or node not found");
    }
  }
}