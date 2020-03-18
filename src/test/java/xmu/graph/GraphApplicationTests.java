package xmu.graph;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xmu.graph.entity.Edge;
import xmu.graph.entity.Node;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author 张铭翔
 * @date 10:26 2020/1/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GraphApplication.class)
class GraphApplicationTests {

	@Autowired
	private Session session;

	private static String regEx="[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？\t《》]";
	@Test
	void contextLoads() throws Exception {
		readTriple("C:\\Users\\ASUS\\Desktop\\ownthink_v2(清洗后)(法律术语全).csv");
	}

	@Test
	void contextLoads1() throws Exception {
		Node node=new Node();
		node.setContent("犯罪嫌疑人");
		Node node1=new Node();
		node1.setContent("嫌疑人");
		Edge edge=new Edge();
		String s="近 义 词";
		s=s.replaceAll(" ","");
		edge.setContent(s);
		createEdge(node,node1,edge);
	}
	/**
	 * 连接
	 */
	public void connect(){
//		System.out.println("连接是否成功:"+session.isOpen());
	}

	/**
	 * 搜索节点
	 * @param node
	 * @return
	 */
	public Node selectNode(Node node)throws Exception{
		Node node1=null;
		String s=node.getContent();
		try{
		String cql="match(n:node) where n.content='"+s+"' return n";
		StatementResult result=session.run(cql);
		if(result.hasNext()){
			node1=node;
		}
		}
		catch (Throwable e){
			System.err.println("执行语句发生错误");
			e.printStackTrace();
		}
		return node1;
	}

	/**
	 * 创建节点
	 */
	public void createNode(Node node)throws Exception{
		Node node1=selectNode(node);
		if(node1==null){
			try {
				String cql = "create(:node{content:'" + node.getContent() + "'})";
//			System.out.println(cql);
				session.run(cql);
//			System.out.println("创建节点成功");
			}
			catch (Throwable e){
				System.err.println("执行语句发生错误");
				e.printStackTrace();
			}
		}
//		else {
////			System.out.println("创建失败");
//		}
	}

	/**
	 * 查询关系
	 */
	public Edge selectEdge(Node node1,Node node2,Edge edge)throws Exception{
		Edge edge1=null;
		String cql="match(a)-[c:"+edge.getContent()+"]-(b) where a.content='"+node1.getContent()+"' and b.content='"+node2.getContent()+"' return c";
//		System.out.println(cql);
		try {
		StatementResult result=session.run(cql);
		if(result.hasNext()){
			edge1=edge;
		}}
		catch (Throwable e){
			System.err.println("执行语句发生错误");
			e.printStackTrace();
		}
		return edge1;
	}

	/**
	 * 创建关系
	 */
	public void createEdge(Node node1,Node node2,Edge edge) throws Exception {
		Edge edge1=selectEdge(node1,node2,edge);
		if(edge1==null){
			try{
			String cql="match (a:node),(b:node) where a.content='"+node1.getContent()+"' and b.content='"+node2.getContent()+"' create (a)-[c:"+edge.getContent()+"]->(b)";
			System.out.println(cql);
			session.run(cql);
//			System.out.println("关系创建成功");
			}
			catch (Throwable e){
				System.err.println("执行语句发生错误");
				e.printStackTrace();
			}
		}
//		else {
////			System.out.println("关系创建失败");
//		}
	}

	/**
	 * 创建三元组
	 */
	public void createTriple(String a,String b,String c) throws Exception {
		Node node1=new Node();
		node1.setContent(a);
		Node node2=new Node();
		node2.setContent(b);
		Edge edge=new Edge();
		c=c.replaceAll(regEx,"");
		c=c.replaceAll(" ","");
		edge.setContent(c);
		try{
		createNode(node1);
		createNode(node2);
		createEdge(node1,node2,edge);}
		catch (Throwable e){
			System.err.println("创建三元组发生错误");
			e.printStackTrace();
		}
	}

	/**
	 * 从csv中读取三元组
	 */
	public void readTriple(String url){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(url));
			reader.readLine();
			String line = null;
			while((line=reader.readLine())!=null){
				String item[] = line.split(",");
				if(item.length==3){
					createTriple(item[0],item[2],item[1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
