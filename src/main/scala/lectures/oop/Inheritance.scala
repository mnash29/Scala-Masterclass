package lectures.oop

object Inheritance extends App {
  class Service(name: String) {
    val service_type: String = this.getClass.toString
    def sendEvent: String = "Sending event"

    // Required if Service is extended without parameters
    def this() = this("Default name")
  }

  class LoggingService(name: String, level: String) extends Service {
    // Override
    override val service_type: String = this.getClass.toString
    override def sendEvent: String = {
      println(super.sendEvent)
      "Pushing to write-ahead-log"
    }
  }

  val loggingService = new LoggingService("ExecutionWAL", "INFO")
  println(loggingService.sendEvent)

  // Parent member variables can also be overridden in the child constructor
  class QueueService(name: String, override val service_type: String) extends Service {}

  // Polymorphism
  var unknownService: Service = new QueueService("EventQueue", "QueueService")
  println(unknownService.sendEvent)
  println(unknownService.getClass.toString)
}
