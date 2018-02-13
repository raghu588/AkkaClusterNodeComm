package Cluster

import akka.cluster._
import commons._
import akka.actor.{ Actor, ActorRef, ActorSystem, Props }


object MainApp extends App{

    NodeFrontend._frontend ! 1
}
