/**
  * Created by rhys.algar on 5/18/16.
  */

import doodle.core.Line.Cap.Square
import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._

object Hello extends App{

//    def parametricCircle(angle: Angle, radius: Int): Point =
//      Point.cartesian(radius * angle.cos, radius * angle.sin)
//
//    def rose(angle: Angle) =
//        Point.cartesian((angle * 7).cos * angle.cos, (angle * 7).cos * angle.sin)
//
//    def sample(start: Angle, increment: Angle): Image = {
//      if(start > Angle.One){
//          Image.empty
//      } else {
//          var pt = rose(start)
//          triangle(10,10) at (Point.polar(pt.r * 100, pt.angle).toVec) on sample(start + increment, increment)
//      }
//    }
//
//    def manyShapes(n: Int, singleShape: Int => Image): Image = {
//      if(n == 1) {
//        singleShape(n)
//      } else {
//        singleShape(n) on manyShapes(n - 1, singleShape)
//      }
//    }
//
//    def triangleSize(size: Int): Image = {
//      val triDim = size * 25
//      Triangle(triDim, triDim)
//    }
//
//
//    def colorConcentricCircles(n: Int, initialColor: Color): Image = {
//      n match {
//        case 0 => Image.empty
//        case n => (circle(10 * n) lineColor initialColor) on colorConcentricCircles(n-1, initialColor.spin(10.degrees))
//      }
//    }
//
//    def sierpinskiTris(n: Int, triangleSize: Double, myColor: Color): Image = {
//      val baseTriangle = triangle(triangleSize, triangleSize) lineColor myColor
//      n match {
//        case 0 => baseTriangle
//        case n => sierpinskiTris(n-1, triangleSize, myColor) above (sierpinskiTris(n-1, triangleSize, myColor) beside sierpinskiTris(n-1, triangleSize, myColor))
//      }
//    }
//  def setShape(ihue: Int, ilightness): Color = {
//    val hue = ihue.degrees
//
//    val lightness = ilightness.normalized
//
//    val thisColor = Color.hsl(hue, 1.0.normalized, lightness)
//
//    val square = Rectangle(2.0, 2.0) fillColor thisColor lineColor thisColor
//  }
//
//  def colorChart(): Image = {
//    allAbove(allBesides((0 until 360).toList.map(i -> setShapeFromHue(i)))
//  }
//
//    def chessboard(n: Int, baseSize: Double, color1: Color, color2: Color): Image = {
//      val baseRect = rectangle(baseSize, baseSize)
//      val baseImage = ((baseRect fillColor color1) beside (baseRect fillColor color2)) above
//        ((baseRect fillColor color2) beside (baseRect fillColor color1))
//      n match {
//        case 0 => baseImage
//        case n =>
//          val smallerChess = chessboard(n-1, baseSize, color1, color2)
//          (smallerChess beside smallerChess) above (smallerChess beside smallerChess)
//      }
//    }

//  def doTwice(x: Double, f: Double => Double): Double = {
//    f(f(x))
//  }
//
//  def square(x: Double): Double = {
//    x * x
//  }
//
//  var f = square _
//
//  print(f)
//
//  print(doTwice(2.0, square))


  def rose(angle: Angle): Point = {
    Point.cartesian((angle * 7).cos * angle.cos, (angle * 7).cos * angle.sin)
  }

  def drawParametric(n: Int): Image = {
    n match {
      case 0 => circle(5.0) at rose(0.degrees).toVec
      case n => (circle(5.0) at rose(n.degrees).toVec) on drawParametric(n-1)
    }
  }

  def clover(angle: Angle): Point = {
    Point.cartesian((angle * 3).cos * angle.cos, (angle * 3).cos * angle.sin)
  }


  def scalePoint(point: Point, factor: Double): Point = {
    Point.polar(point.toPolar.r * factor, point.toPolar.angle)
  }

  def drawCustomParametric(n: Int, radius: Double, parameticFunction: (Angle) => Point): Image = {
    n match {
      case 0 => circle(5.0) at scalePoint(parameticFunction(0.degrees), radius).toVec
      case n => (circle(5.0) at scalePoint(parameticFunction(n.degrees), radius).toVec) on drawCustomParametric(n-1, radius, parameticFunction)
    }
  }

//  def drawDoubleParametric(n: Int, radius: Double, parameticFunction1: (Angle, Double) => Point, parameticFunction2: (Angle, Double) => Point): Image = {
//    n match {
//      case 0 => (circle(5.0) at parameticFunction1(0.degrees, radius).toVec) on (triangle(5.0, 5.0) at parameticFunction1(0.degrees, radius).toVec)
//      case n => ((circle(5.0) at parameticFunction1(n.degrees, radius).toVec) on drawCustomParametric(n-1, radius, parameticFunction)
//    }
//  }

//  drawParametric(360, 300).draw


  var myRose = rose _

  drawCustomParametric(360, 500, rose).draw

}
