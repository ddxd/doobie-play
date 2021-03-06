package org.channing.simple

import cats.Monad
import org.channing.simple.StoreIO._

/**
  * This class does not directly make use of a Store, but works with
  * StoreIO[C, ?].
  *
  * To work with StoreIO in for comprehensions we require that C has a Monad
  * hence the type parameter is C[_]: Monad
  *
  * StoreIO._ is also imported since it has other implicits required for
  * working with StoreIO without having to remember what they are.
  */
class Service[C[_]: Monad](somethingUsingTheStore: Layer1[C]) {

  def greatComplexService: StoreIO[C, String] =
    for {
      a ← somethingUsingTheStore.putThings
      b ← somethingUsingTheStore.getTheThings
    } yield a + b
}
