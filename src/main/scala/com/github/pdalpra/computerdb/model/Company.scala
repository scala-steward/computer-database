package com.github.pdalpra.computerdb.model

import cats.syntax.all._
import cats.{ Eq, Show }
import eu.timepit.refined.cats._
import io.circe.{ Decoder, Encoder }
import io.circe.generic.semiauto._
import io.circe.refined._

object Company {
  implicit val encoder: Encoder[Company] = deriveEncoder
  object Id {
    implicit val eq: Eq[Id]           = Eq[UniqueId].contramap(_.value)
    implicit val show: Show[Id]       = Show[UniqueId].contramap(_.value)
    implicit val decoder: Decoder[Id] = Decoder[UniqueId].map(Id.apply)
    implicit val encoder: Encoder[Id] = Encoder[UniqueId].contramap(_.value)
  }
  final case class Id(value: UniqueId)
}
final case class Company(
    id: Company.Id,
    name: NonEmptyString
)
