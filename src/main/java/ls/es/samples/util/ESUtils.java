package ls.es.samples.util;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;

/**
 * elasticsearch工具类
 * @author zhouyong
 */
public class ESUtils {
	// 集群名称：ES-HOME//config/elasticsearch.yml文件中cluster.name
	private static String clusterName = "lishuai";
	//private static String[] esHosts = {"es.zfwx.com"};
    private static String[] esHosts = {"master.hadoop","slave1.hadoop","slave2.hadoop"};
	private static int esPort = 9300;

    // 设置client.transport.sniff为true来使客户端去嗅探整个集群的状态，把集群中其它机器的ip地址加到客户端中，
    static Settings settings = Settings.settingsBuilder().put("cluster.name", clusterName).put("client.transport.sniff", true).build();
    
    private static TransportClient createClient(){
    	TransportClient client = null;
    	try {
        	client = TransportClient.builder().settings(settings).build();
            for(String host : esHosts){
            	client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), esPort));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return client;
    }

    // 取得实例
    public static TransportClient getTransportClient() {
        return createClient();
    }
}