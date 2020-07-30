package Graph;

/**
 * @author Jianshu
 * @time 20200729
 * 最小生成树每条边类，这个类保存了图的每两个节点之间的关系
 */
public class GraphEdge implements Comparable<GraphEdge>{

    Integer head;//该边的头节点
    Integer tail;//该边的尾节点
    Integer lowCost;//该边的权重

    public GraphEdge(int head, int tail, int lowCost) {
        this.head = head;
        this.tail = tail;
        this.lowCost = lowCost;
    }

    public Integer getHead() {
        return head;
    }

    public void setHead(Integer head) {
        this.head = head;
    }

    public Integer getTail() {
        return tail;
    }

    public void setTail(Integer tail) {
        this.tail = tail;
    }

    public Integer getLowCost() {
        return lowCost;
    }

    public void setLowCost(Integer lowCost) {
        this.lowCost = lowCost;
    }

    @Override
    public int compareTo(GraphEdge graphEdge){
        return this.getLowCost().compareTo((graphEdge.getLowCost()));
    }
}
