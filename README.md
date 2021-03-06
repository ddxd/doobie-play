# doobie-play

When using [Doobie](https://github.com/tpolecat/doobie) in a real system you will probably want to
abstract away from *ConnectionIO*, a Doobie type, so it
doesn't leak out all over your codebase. The problem then is how to work with the resulting abstraction.

This project has a simple [Store](src/main/scala/org/channing/simple/Store.scala), a trait
parameterised by C[_], some context containing the result
of operations on the store.

To support the idea of side-effects performed after a
successful store operation, a post commit, the operations
on Store return a *StoreIO[C[_], A]*, a type alias for
 *WriterT[C, List[PostCommit], A]*. This enables post commit
operations to be freely mixed in with store operations. 

See [Store](src/main/scala/org/channing/simple/Store.scala) and 
[SomethingUsingTheStore](src/main/scala/org/channing/simple/SomethingUsingTheStore.scala) first, then 
look at how [Service](src/main/scala/org/channing/simple/Service.scala), how something not using Store directly
but working with StoreIO, summon the implicits it needs.
 
Finally, see [DoobieStore](src/main/scala/org/channing/simple/DoobieStore.scala).