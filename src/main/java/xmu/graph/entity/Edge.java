package xmu.graph.entity;

/**
 * @author 张铭翔
 * @date 1:13 2020/1/14
 */
public class Edge extends Object{
    /**
     * 关系id
     */
    private String id;

    /**
     * 关系名
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
        return "Edge{" +
                "content='" + content + '\'' +
                '}';
    }
}
