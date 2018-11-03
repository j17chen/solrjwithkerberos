# solrjwithkerberos
use solrj to access kerberized solr cloud 

for Java JDK
java -Djava.security.auth.login.config=solr_client.jaas -Dsun.security.spnego.debug=true -Dsun.security.debug=true -jar solrj-with-kerberos-1.0-SNAPSHOT-jar-with-dependencies.jar

for IBM JDK:
java -Djava.security.auth.login.config=solr_client_ibm.jaas -Dcom.ibm.security.jgss.debug=all -Dcom.ibm.security.krb5.Krb5Debug=all -Djava.security.debug=configfile,configparser,gssloginconfig  -Djavax.security.auth.useSubjectCredsOnly=false  -Djava.security.krb5.conf=krb5.conf  -jar solrj-with-kerberos-1.0-SNAPSHOT-jar-with-dependencies.jar
