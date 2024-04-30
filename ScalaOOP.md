# Object Oriented Programming

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