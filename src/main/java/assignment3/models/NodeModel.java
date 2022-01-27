package assignment3.models;

import java.util.HashMap;
import java.util.HashSet;

public class NodeModel {

  private int id;
  private String name;
  private HashMap<String, Object> additionalInfo;
  private HashSet<Integer> children;
  private HashSet<Integer> parents;

  public NodeModel(final int id, final String name) {
    this.id = id;
    this.name = name;
    this.additionalInfo = new HashMap<>();
    this.children = new HashSet<>();
    this.parents = new HashSet<>();
  }

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public HashMap<String, Object> getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(final HashMap<String, Object> additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  public HashSet<Integer> getChildren() {
    return children;
  }

  public void setChildren(final HashSet<Integer> children) {
    this.children = children;
  }

  public HashSet<Integer> getParents() {
    return parents;
  }

  public void setParents(final HashSet<Integer> parents) {
    this.parents = parents;
  }

  public void addParent(final int id) {
    parents.add(id);
  }

  public void removeParent(final int id) {
    parents.remove(id);
  }

  public void addChild(final int id) {
    children.add(id);
  }

  public void removeChild(final int id) {
    children.remove(id);
  }
}
