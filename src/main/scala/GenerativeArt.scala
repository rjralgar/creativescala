/**
  * Created by rhys.algar on 7/8/16.
  */

import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._
import doodle.random._

import cats.syntax.cartesian._

object GenerativeArt extends App {

  def concentricCircles(n: Int): Image =
    n match {
      case 0 => circle(10)
      case n => concentricCircles(n - 1) on circle(n * 10)
    }

  val randomDouble = Random.double

  val randomAngle: Random[Angle] = {
    Random.double.map(x => x.turns)
  }

  def randomColor(s: Normalized, l: Normalized): Random[Color] =
    randomAngle.map(hue => Color.hsl(hue, s, l))

  def randomCircle(r: Double, color: Random[Color]): Random[Image] =
    color.map(fill => Image.circle(r) fillColor fill)

  val randomPastel = randomColor(0.7.normalized, 0.7.normalized)

  def randomConcentricCircles(n: Int): Random[Image] =
    n match {
      case 0 => randomCircle(10, randomPastel)
      case n =>
        randomConcentricCircles(n-1) |@| randomCircle(n * 10, randomPastel) map {
          (circles, circle) => circles on circle
        }
    }

  var a = randomConcentricCircles(10)

}
