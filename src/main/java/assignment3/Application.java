package assignment3;

import assignment3.exceptions.CyclicDependencyException;
import assignment3.exceptions.NodeAlreadyExistsException;
import assignment3.exceptions.NodeNotFoundException;
import assignment3.models.GraphModel;
import assignment3.models.NodeModel;
import assignment3.services.GraphService;

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

    try {
      graphService.addNode(2, "B", graph);
      graphService.addNode(3, "C", graph);

      graphService.addDependency(1, 2, graph);
      graphService.addDependency(2, 3, graph);
    } catch (CyclicDependencyException e) {
      logger.error("Cycle present in graph");
    } catch (NodeNotFoundException e) {
      logger.error("Node not found in graph");
    } catch (NodeAlreadyExistsException e) {
      logger.error("Node already exists in graph");
    }
  }
}
