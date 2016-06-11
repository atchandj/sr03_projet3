<h1> SR03 - Projet Web Services </h1>

<p>The project goal is to develop a web service that will manage a directory service announcements throughout France.</p>

<p>In order to use this project, it's necessary to create the database given in the SQL files that can be found in <i>/WebContent/WEB-INF/database_conception</i>.</p>

<p>Then, you will need to add two files in the folder <i>/src/dao</i> the file <i>dao.properties</i> containing 
   <pre><code>url = jdbc:mysql://XXXXX
driver = com.mysql.jdbc.Driver
username = XXXXX
password = XXXXX</pre></code>
</p>

<p> Then you will need to create the web service:
<ol>
  <li>Right click on <i>Main.java</i> </li>
  <li>New=>Other=>Web Services=>Web Service </li>
  <li>Select the "Start Service" levels for the server and "Client Test" for client</li>
  <li>Check : Monitor the Web Service</li>
  <li>Click Finish</li>
</ol>

Two clients have already been created :
<ol>
  <li>https://github.com/daniel-artchounin/sr03_projet3_client1</li>
  <li>https://github.com/daniel-artchounin/sr03_projet3_client2</li>
</ol>

<p>However you will still need to create the web service like we did previously because we need the <i>Main.wsdl</i> that will be created. Thus, you will copy under <i>WebContent/wsdl/</i> that file. Then you right click on the file, Generate Customer (from <i>Main.wsdl</i> context menu). </p>
