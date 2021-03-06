package com.github.pdalpra.computerdb.model

import eu.timepit.refined.api._
import eu.timepit.refined.numeric._
import io.circe.Encoder
import io.circe.generic.semiauto._
import io.circe.refined._

import scala.annotation.nowarn

object Page {
  type Number = Int Refined Positive
  type Size   = Int Refined NonNegative

  object Number extends RefinedTypeOps[Number, Int]
  object Size   extends RefinedTypeOps[Size, Int]

  val DefaultPage: Number   = Number.unsafeFrom(1)
  val DefaultPageSize: Size = Page.Size.unsafeFrom(10)

  @nowarn // Encoder is detected as unused
  implicit def encoder[A: Encoder]: Encoder[Page[A]] = deriveEncoder
}
final case class Page[A](items: List[A], page: Page.Number, offset: Int, total: Int) {

  def previous: Option[Page.Number] =
    Page.Number.from(page.value - 1).toOption

  def next: Option[Page.Number] =
    Option.when(offset + items.size < total) {
      Page.Number.unsafeFrom(page.value + 1)
    }
}
