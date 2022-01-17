package assignment3;

import assignment2.constants.Constants;
import assignment3.exceptions.CyclicDependencyException;
import assignment3.exceptions.DependencyNotFoundException;
import assignment3.exceptions.NodeAlreadyExistsException;
import assignment3.exceptions.NodeNotFoundException;
import assignment3.models.GraphModel;
import assignment3.models.NodeModel;
import assignment3.services.GraphService;
import assignment3.utils.GraphUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

  final static Logger logger = LoggerFactory.getLogger(GraphService.class);

  /**
   * Driver function
   */
  public static void init() {
    GraphModel graph = new GraphModel(new NodeModel(1, "A"));
    GraphService graphService = new GraphService();

    try (Scanner scan = new Scanner(System.in)) {

      boolean stopsignal = false;

      while (!stopsignal) {
        System.out.println(
            "1. Get the immediate parents of a node\n" + "2. Get the immediate children of a node\n"
                + "3. Get the ancestors of a node, passing the node id as input parameter.\n"
                + "4. Get the descendants of a node, passing the node id as input parameter.\n"
                + "5. Delete dependency from a tree, passing parent node id and child node id.\n"
                + "6. Delete a node from a tree, passing node id as input parameter\n"
                + "7. Add a new dependency to a tree\n"
                + "8. Add a new node to tree. This node will have no parents and children\n"
                + "9. Exit\n\n");

        int choice = scan.nextInt();

        switch (choice) {
          case 1: {
            try {
              System.out.println("Enter the node ID:");
              int nodeId = scan.nextInt();
              HashSet<Integer> parents = graphService.getImmediateParents(nodeId, graph);
              System.out.println("Parents are: " + parents.toString());
            } catch (NodeNotFoundException e) {
              logger.info("Node not found");
            }

            break;
          }

          case 2: {
            try {
              System.out.println("Enter the node ID:");
              int nodeId = scan.nextInt();
              HashSet<Integer> children = graphService.getImmediateParents(nodeId, graph);
              System.out.println("Children are: " + children.toString());
            } catch (NodeNotFoundException e) {
              logger.info("Node not found");
            }

            break;
          }

          case 3: {
            try {
              System.out.println("Enter the node ID:");
              int nodeId = scan.nextInt();
              HashSet<Integer> ancestors = GraphUtils.getAncestors(nodeId, graph);
              System.out.println("Children are: " + ancestors.toString());
            } catch (NodeNotFoundException e) {
              logger.info("Node not found");
            }

            break;
          }

          case 4: {
            try {
              System.out.println("Enter the node ID:");
              int nodeId = scan.nextInt();
              HashSet<Integer> descendents = GraphUtils.getDescendents(nodeId, graph);
              System.out.println("Descendents are: " + descendents.toString());
            } catch (NodeNotFoundException e) {
              logger.info("Node not found");
            }

            break;
          }

          case 5: {
            try {
              System.out.println("Enter parentID:");
              int parentId = scan.nextInt();
              System.out.println("Enter childID:");
              int childId = scan.nextInt();
              graphService.deleteDependency(parentId, childId, graph);
            } catch (NodeNotFoundException e) {
              logger.info("Node not found");
            } catch (DependencyNotFoundException e) {
              logger.info("Dependency not found");
            }

            break;
          }

          case 6: {

            try {
              System.out.println("Enter node ID to delete:");
              int nodeId = scan.nextInt();

              graphService.deleteNode(nodeId, graph);
            } catch (NodeNotFoundException e) {
              logger.info("Node not found");
            }
            break;
          }

          case 7: {

            try {
              System.out.println("Enter parentID:");
              int parentId = scan.nextInt();
              System.out.println("Enter childID:");
              int childId = scan.nextInt();

              graphService.addDependency(parentId, childId, graph);

            } catch (NodeNotFoundException e) {
              logger.info("Node not found");
            } catch (CyclicDependencyException e) {
              logger.info("Cyclic dependency is formed. Cannot add dependency");
            }

            break;
          }

          case 8: {

            try {
              System.out.println("Enter the node ID:");
              int nodeId = scan.nextInt();
              System.out.println("Enter the node name:");
              String nodeName = scan.next();

              graphService.addNode(nodeId, nodeName, graph);
            } catch (NodeAlreadyExistsException e) {
              logger.info("Node already exists in the graph, cannot add node.");
            }

            break;
          }

          case 9: {
            stopsignal = true;
            break;
          }

          default: {
            System.out.println("Choose a valid option.");
            break;
          }
        }
      }

    } catch (InputMismatchException e) {
      logger.error("Error happened while scanning inputs", e);
    }
  }
}
