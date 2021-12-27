package xx.yy.hou.type;

public class JobType implements Comparable<JobType> {

  private int id = 0;
  private int parentId;
  private String description;

  @Override
  public int compareTo(JobType o) {
    return this.id - o.getParentId();
  }
  public JobType(int id, int parentId, String description) {
    this.id = id;
    this.parentId = parentId;
    this.description = description;
  }

  @Override
  public String toString() {
    return "JobType{" +
      "id=" + id +
      ", parentId=" + parentId +
      ", description='" + description + '\'' +
      '}';
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getParentId() {
    return parentId;
  }

  public void setParentId(int parentId) {
    this.parentId = parentId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
