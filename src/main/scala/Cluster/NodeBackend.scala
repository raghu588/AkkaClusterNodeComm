package Cluster

import java.sql.ResultSet

import com.typesafe.config.ConfigFactory
import akka.actor.{Actor, ActorRef, ActorSystem, Props, RootActorPath}
import com.datastax.driver.core.{BoundStatement, Cluster, PreparedStatement, Session}
import Utils._
import akka.event.Logging
import scala.collection.JavaConversions._


class NodeBackend extends Actor{

  val cluster = akka.cluster.Cluster(context.system)

  val log = Logging(context.system, this)


  def receive = {

    case id: Int => cassandra_connect(Utils.cassandra_host,id)
    case _ => println("Unknow Restults")


  }

  def cassandra_connect(hostname: String, id: Int)  {

    val cluster = Cluster.builder().addContactPoint(hostname).build()
    val metadata = cluster.getMetadata()
    log.info("Connected to cluster: %s\n", metadata.getClusterName())
    metadata.getAllHosts() map {
      case host =>
        log.info("Datatacenter: %s; Host: %s; Rack: %s\n", host.getDatacenter(),
          host.getAddress(), host.getRack())
    }
    var session: Session = cluster.connect(Utils.cassandra_keyspace)
    session = cluster.connect(Utils.cassandra_keyspace);
    val statement : PreparedStatement = session.prepare("SELECT * from "+ Utils.cassandra_keyspace+"."
      +Utils.cassandra_table+" where id="+id)
    val boundStatement: BoundStatement = new BoundStatement(statement)
    val rs = session.execute(boundStatement)
    println(rs.all())
  }


}

object NodeBackend {

  val config = ConfigFactory.load.getConfig("NodeBackend")
  val system = ActorSystem("ClusterSystem", config)
  val _backend = system.actorOf(Props[NodeBackend])

}
