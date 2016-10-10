package org.channing.simple

import cats.data.Xor

abstract class Store[C[_]] {

  def runStoreIO[T](storeIO: StoreIO[C, T]): Throwable Xor  T

  def get(k: String): StoreIO[C, Option[String]]

  def put(k: String, v: String): StoreIO[C, Int]

  def postCommit(pc: PostCommit): StoreIO[C, Unit]
}