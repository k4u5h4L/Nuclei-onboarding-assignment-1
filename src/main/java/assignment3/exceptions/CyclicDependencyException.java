package assignment3.exceptions;

public class CyclicDependencyException extends Exception {

  public CyclicDependencyException(String s) {
    super(s);
  }
}
