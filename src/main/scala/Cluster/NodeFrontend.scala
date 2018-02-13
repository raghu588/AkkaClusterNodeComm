package Cluster

import com.typesafe.config.ConfigFactory
import akka.actor.{Actor, ActorRef, ActorSystem, Props, Terminated}

class NodeFrontend extends Actor {

  def receive = {
    case internalID: Int => NodeBackend._backend ! internalID
    case _ => println ("Nothing Received")
  }
}
object NodeFrontend{

    val config = ConfigFactory.load().getConfig("NodeFrontend")
    val system = ActorSystem("ClusterSystem", config)
    var _frontend = system.actorOf(Props[NodeFrontend], name = "frontend")
}