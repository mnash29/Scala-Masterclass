# Object Oriented Programming in Scala

### Declaring an object

```scala
class Person(val name: String, age: Int) {
  val heightInInches = 72
}

val person = new Person("Matthew", 24)

// Class parameters are NOT FIELDS, add the keyword `val` or `var`
// in the constructor to create class fields
println(person.age) // Error
println(person.name) // Matthew

// Values declared in the class block ARE FIELDS
println(person.heightInInches)
```

### Declaring class methods

```scala
class Person(val name: String, val age: Int) {
  def greet(name: String): Unit = println(s"$name says: Hi, ${this.name}")
  
  // overloading
  def greet(): Unit = println(s"Hi, I am $name")
  
  // Can leave out parenthesis if the method has no params
  def increment = count += 1
}
```

### Constructors

```scala
class Person(val name: String, val age: Int = 0) {
  
  // Auxiliary constructors
  // could also add default values to constructor ^^
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}
```

### Method Notations

Methods with a single parameter can be called using the Infix or Operator
notation. Prefix notation can be used with unary operators like "-, +, ~, !"

```scala
object MethodNotations extends App {
  
  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie = favoriteMovie
    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} is with ${person.name}"
    
    // Prefix notation
    def unary_! : String = s"$name, where are you!?"
    
    // Postfix notation
    def isAlive: Boolean = true
    
    // keyword method to run class like a method, e.g. tom()
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
  }
  
  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // Infix or operator notation
  
  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom) // The method `hangOutWith` acts like an operator

  // Mathematical operators are also methods and can be overloaded
  println(mary + tom) // "Mary is with Tom"
  
  // ALL OPERATORS ARE METHODS
  
  println(!mary) // "Mary, where are you!?
  println(mary isAlive) // true
  
  println(mary.apply())
  println(mary()) // equivalent
}
```

### Scala Objects

Scala does not have class-level functionality, e.g. static methods/vars. Objects
are all singleton instances. Can separate instance level functionality from class
(static) level functionality

```scala
object Person { // static/class level (singleton)
  val N_EYES = 2
  def canFly: Boolean = false
  
  // Factory method
  def from(mother: Person, father: Person): Person = new Person("Bobbie")
}

class Person(val name: String) { // instance level
}

println(Person.N_EYES)
println(Person.canFly)

// Singleton
val mary = Person
val john = Person
mary == john // true

val susan = new Person("Susan")
val tom = new Person("Tom")
println(susan == tom) // false

// Call factory method `from()`
val bobbie = Person(susan, tom)
```

### Scala Applications

A Scala Object with a particular method `main(args: Array[String]): Unit`

```scala
class EventsService extends App { } 
  
// Equivalent to EventsService  
class LoggingService {
  def main(args: Array[String]): Unit = { }
}
```

### Inheritance

Preventing overriding by derived classes can be done in three ways:
1. Adding `final` to the method definition `final def method()`
2. Adding `final` to the entire class `final class Service`
3. Sealing the class `sealed class Service`, extends derived classes in the current file but closed it off to external classes.

```scala
class Service(name: String) {
  val service_type: String = this.getClass.toString
  def sendEvent: String = "Sending event"

  // Required if Service is extended without parameters
  def this() = this("Default name")
  
  // Prevent overriding in derived classes
  final def register: Unit = println("Registering service")
}

class LoggingService(name: String, level: String) extends Service {
  // Override
  override val service_type = this.getClass
  override def sendEvent: String = {
    println(super.sendEvent)
    "Pushing to write-ahead-log"
  }
}

// Parent member variables can also be overridden in the child constructor
class QueueService(name: String, override val service_type: String) extends Service {}

// Polymorphism
var unknownService: Service = new QueueService("EventQueue", "QueueService")
println(unknownService.sendEvent) // "Sending event"
println(unknownService.service_type) // QueueService
```

### Abstract Data Types

Abstract data types are made to be implemented later.

```scala
abstract class Service {
  val service_type: String
  def sendEvent: Unit
}

class LoggingService extends Service {
  val service_type: String = this.getClass.toString
  def sendEvent: Unit = println("Pushing to write-ahead-log")
}

// Traits
// Do not have constructor parameters
// Can only extend one class but multiple traits
// Traits define `behavior` while abstract defines `thing`
trait Stream {
  def sendEvent(endpoint: String): Unit
}

class QueueService extends Service with Stream {
  val service_type: String = this.getClass.toString
  def sendEvent: Unit = println("Sending PutMessage event")
  def sendEvent(endpoint: String): Unit = println(s"Sending PutMessage event to $endpoint")
}

val queue = new QueueService
queue.sendEvent // Sending PutMessage event
queue.sendEvent("localhost:8080") // Sending PutMessage event to localhost:8080
```

### Generics

```scala
class MyList[A] {
  // use type A inside definition
}

val listOfIntegers = MyList[Int]
val listOfStrings = MyList[String]

class HashMap[K, V] { } // Can contain multiple types

// Generic methods

object MyList {
  def empty[A]: MyList[A] = ???
  def add[B >: A](element: B): MyList[B] = ???] // Bounded super type A
}
val emptyListOfIntegers = MyList.empty[Int]

// Covariant lists

class Service
class QueueService extends Service
class LoggingService extends Service
class Request

// 1. Covariance - List[QueueService] extends list[Service]
class CovariantList[+S]
val queueList: CovariantList[Service] = new CovariantList[QueueService] // OK
queueList.add(new LoggingService) // ??? Creates a list of Service types

// 2. Invariance
class InvariantList[S]
val invariantList: InvariantList[Service] = new InvariantList[QueueService] // ERROR
val invariantList: InvariantList[Service] = new InvariantList[Service] // OK

// 3. Contravariance
class Orchestrator[-S]
val orchestrator: Orchestrator[QueueService] = new Orchestrator[Service] // OK

// Bounded types - sub <: - super >:
class Event[R <: Request](request: R) // Event only accepts types of subtype Service
val event = new Event(new Request)
val newEvent = new Event(new Service) // ERROR
```

### Anonymous Classes

```scala
object AnonymousClass extends App {
  abstract class Worker {
    def execute: Unit
  }

  // Anonymous class
  var executeTask: Worker = new Worker {
    override def execute: Unit = println("Starting task execution...")
  }
  println(executeTask.getClass) // package.AnonymousClass$$anon$1
  
  /* Compiler creates background anonymous class:
  
  class AnonymousClass$$anon$1 extends Worker {...}
  var executeTask: Worker = new AnonymousClass$$anon$1
   */
}
```

### Case Classes

Solution for reducing boilerplate implementations of standards methods in lightweight
classes
1. Promotes class parameters to fields
2. Implements equals and hashCode methods
3. Copy method
4. Instantiation uses `apply` method
5. Serializable (useful in Akka pattern)
6. Extractor patterns (pattern matching)

```scala
// Promotes all class parameters to fields
case class Worker(serviceName: String, log: String)
val taskWorker = new Worker("Logging", "/service/logs")
println(taskWorker) // Worker(Logging, /service/logs)

// Equals and hashCode implemented
val newWorker = new Worker("Logging", "/service/logs")
println(taskWorker == newWorker) // true

// Handy copy methods
val accessWorker = taskWorker.copy(log="/service/access.logs")

// Instantiation uses `apply()` method
val databaseWorker = Worker("Database", "/db/wal/logs")
```

### Enums

```scala

// Sealed
enum Permissions {
  case READ, WRITE, EXECUTE, NONE
  
  def openDocument(): Unit =
    if (this == READ) println("Opening document")
    else println("Operation not allowed")
}

val readPermission: Permissions = Permissions.READ
readPermission.openDocument()

// Constructor args
enum PermissionWithBits(bits: Int) {
  case READ extends PermissionWithBits(4)
  case WRITE extends PermissionWithBits(2)
  case EXECUTE extends PermissionWithBits(1)
  case NONE extends PermissionWithBits(0)
}

object PermissionWithBits {
  def fromBits(bits: Int): PermissionWithBits = PermissionWithBits.NONE
}

val allPermissions = PermissionWithBits.values // All possible values
val writePermission = Permissions.valueOf("WRITE")
```

### Exceptions

```scala
// Exceptions and Error extend the Throwable class
def getInt(withExceptions: Boolean): Int =
  if (withExceptions) throw new Exception()("No INT for you!")
  
try {
  getInt(true)
} catch {
  case e: RuntimeException => println("caught RuntimeException")
  case e: Exception => println("unhandled Exception caught")
} finally {
  // Code that will be executed but not returned from expression
  println("finally block")
}

// Custom Exceptions
class ServiceException extends Exception
```