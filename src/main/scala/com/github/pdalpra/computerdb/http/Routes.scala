package com.github.pdalpra.computerdb.http

import com.github.pdalpra.computerdb.ComputerDatabaseBuildInfo
import com.github.pdalpra.computerdb.service._

import cats.effect.Async
import org.http4s._
import org.http4s.dsl.Http4sDsl
import org.http4s.headers.`Content-Type`
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.staticcontent._

object Routes {

  def apply[F[_]: Async](computerService: ComputerService[F]): HttpApp[F] =
    new Routes[F](computerService).httpApp

  private class Routes[F[_]: Async](computerService: ComputerService[F]) extends Http4sDsl[F] with Extractors {

    def httpApp: HttpApp[F] =
      Router(
        "/"          -> miscRoutes,
        "/computers" -> html.Routes(computerService),
        "/json"      -> json.Routes(computerService),
        "/assets"    -> assetsRoutes
      ).orNotFound

    private def miscRoutes =
      HttpRoutes.of[F] {
        case GET -> Root             => redirectToHome
        case GET -> Root / "version" => Ok(ComputerDatabaseBuildInfo.toJson, `Content-Type`(MediaType.application.json))
      }

    private def assetsRoutes =
      resourceServiceBuilder("assets").withCacheStrategy(MemoryCache[F]()).toRoutes
  }
}
