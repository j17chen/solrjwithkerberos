import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.client.solrj.impl.Krb5HttpClientConfigurer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

/**
 * Created by jchen on 01/11/2018.
 */
public class Main {
    public static void main(String[] args) {
        final String COLLECTION_NAME="ranger_audits";

        try {

            String zkHostString = "node1.openstacklocal.com:2181,node2.openstacklocal.com:2181,node3.openstacklocal.com:2181/solr";
            HttpClientUtil.addConfigurer(new Krb5HttpClientConfigurer());
            SolrClient solr = new CloudSolrClient.Builder().withZkHost(zkHostString).build();
            String id = String.valueOf(System.currentTimeMillis());
            //test write data
            {
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id", id);
                document.addField("name", "Gouda cheese wheel");
                document.addField("price", "49.99");
                UpdateResponse response = solr.add(COLLECTION_NAME,document);
                System.out.println(response.toString());
                solr.commit(COLLECTION_NAME);
            }
            //test query data
            {
                SolrQuery query = new SolrQuery();
                query.setQuery("id:"+id);
                QueryResponse response = solr.query(COLLECTION_NAME,query);
                System.out.println(response.toString());
            }
            solr.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
