package xmu.graph.entity;

/**
 * @author 张铭翔
 * @date 1:13 2020/1/14
 */
public class Node extends Object{
    /**
     * 节点id
     */
    private String id;

    /**
     * 节点名
     */
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Node{" +
                "content='" + content + '\'' +
                '}';
    }
}
