/**
  * Created by rhys.algar on 6/6/16.
  */

import java.awt.Shape

import doodle.core._
import doodle.core.Image._
import doodle.core.Point._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._

object ShapesSequencesStars extends App {

  /* Question 1 */

  var radius = 100
  var pi = Math.PI

  // Triangle
/*
  val triangle = List(
    MoveTo(polar(100, 0.degrees)),
    LineTo(polar(100, 120.degrees)),
    LineTo(polar(100, 240.degrees)),
    LineTo(polar(100, 0.degrees))
  )

  openPath(triangle).draw
  */
  // Square
/*
  val square = List(
    MoveTo(polar(100, (360/(4*2)).degrees)),
    LineTo(polar(100, (3*360/(4*2)).degrees)),
    LineTo(polar(100, (5*360/(4*2)).degrees)),
    LineTo(polar(100, (7*360/(4*2)).degrees)),
    LineTo(polar(100, (9*360/(4*2)).degrees))
  )

  openPath(square).draw
  */
  // Pentagon
/*
  val pentagon = List(
    MoveTo(polar(100, 0.degrees)),
    LineTo(polar(100, (360/5).degrees)),
    LineTo(polar(100, (2*360/5).degrees)),
    LineTo(polar(100, (3*360/5).degrees)),
    LineTo(polar(100, (4*360/5).degrees)),
    LineTo(polar(100, 0.degrees))
  )

  openPath(pentagon).draw
*/
  // General Polygon

//  def polygon(sides: Int, radius: Int): Image = {
//    val initialAngle = (pi/(2.0*sides.toDouble)).degrees
//    val spinAmount = (pi/sides.toDouble).degrees
//
//    def iterateSides(n: Int, r: Int) = {
//      n match {
//        case 0 => MoveTo(polar(r, initialAngle))
//        case n => LineTo(polar(r, initialAngle)) :: iterateSides(n-1, r)
//      }
//    }
//
//    iterateSides(sides, radius)
//
//  }
/*
  def ones(n: Int): List[Int] = {
    n match {
      case 0 => Nil
      case n => 1 :: ones(n-1)
    }
  }

  print(ones(5))

  def descending(n: Int): List[Int] = {
    // TODO: Deal with n<0 ?
    n match {
      case 0 => Nil
      case n => n :: descending (n-1)
    }
  }

  print(descending(15))

  def ascending(n: Int): List[Int] = {
    // TODO: Deal with n<0 ?
    def loop(len: Int, i: Int): List[Int] = {
      i match {
        case 0 => Nil
        case i => len - i + 1 :: loop(len, i-1)
      }
    }
    loop(n,n)
  }

  print(ascending(7))
  */

  /*
  def fill[A](n: Int, a:A): List[A] = {
    n match {
      case 0 => Nil
      case n => a :: fill(n-1, a)
    }
  }

  print(fill(5,13))

  print(fill(5,"Hi"))
  */

  /*
  def double(list: List[Int]): List[Int] = {
    list match {
      case Nil => Nil
      case hd :: tl => hd*2 :: double(tl)
    }
  }

  print(double(List(10,15,20)))
  */

  /*
  def product(list: List[Int]): Int = {
    list match {
      case Nil => 1
      case hd :: tl => hd * product(tl)
    }
  }

  print(product(List(1,2,3)))
  */
/*
  def contains[A](list: List[A], a:A): Boolean = {
    list match {
      case Nil => false
      case hd :: tl => hd == a | contains(tl, a)
    }
  }

  print(contains(List("Hello","World","this","is","a","list"),"Missing"))
  print(contains(List("Hello","World","this","is","a","list"),"this"))
  */

  /*
  def first[A](list: List[A], a:A): A = {
    list match {
      case Nil => a
      case hd :: tl => hd
    }
  }

  print(first(Nil, 4))
  print(first(List(1,2,3), 4))
  print(first(List("This","is","a","list"),"!"))
  */

  /*
  def reverse[A](list: List[A]): List[A] = {
    def loop(head: List[A], tail: List[A]): List[A] = {
      tail match {
        case Nil => head
        case hd :: tl => loop(hd :: head, tl)
      }
    }
    loop(Nil, list)
  }

  print(reverse(List(1,2,3,4,5)))
  */

  /*
  def polygon(sides: Int, radius: Int): Image = {
    def loop(n: Int, angle: Angle): List[PathElement] = {
      n match {
        case 0 => Nil
        case n => LineTo(polar(radius, angle * n)) :: loop(n-1, angle)
      }
    }
    closedPath(MoveTo(polar(radius, 0.degrees)) :: loop(sides, (360/sides).degrees))
  }
  */

  def colorIteration(n: Int, initialColor: Color): Color = {
    n match {
      case 0 => initialColor
      case n => initialColor.spin((n*5).degrees)
    }
  }

  /*
  def multiplePolygons(n: Int, initialRadius: Int, initialColor: Color): Image = {
    n match {
      case 0 => polygon(3, initialRadius) fillColor initialColor
      case n => (polygon(n+3, initialRadius + 15*n) fillColor colorIteration(n, initialColor)) under multiplePolygons(n-1, initialRadius, initialColor)
    }
  }

  multiplePolygons(15, 20, Color.red).draw
  */


  def star(p: Int, n: Int, radius: Double): Image = {
    def loop(i: Int): List[PathElement] = {
      i match {
        case 0 => Nil
        case i => LineTo(polar(radius, (i * n * 360 / p).degrees)) :: loop(i-1)
      }
    }
    closedPath(MoveTo(polar(radius, 0.degrees)) :: loop(p))
  }

  //star(11, 7, 100).draw

  def allBeside(images: List[Image]): Image = {
    images match {
      case Nil => Image.empty
      case hd :: tl => hd beside allBeside(tl)
    }
  }

//  allBeside(
//    (1 to 5).toList map { skip =>
//      star(11, skip, 100)
//    }
//  ).draw

  def allAbove(images: List[Image]): Image = {
    images match {
      case Nil => Image.empty
      case hd :: tl => hd above allAbove(tl)
    }
  }

  allAbove(
    (1 to 15).toList map { row =>
      allBeside(
        (1 to row).toList map { skip =>
          star(2*row + 1, skip, 20) fillColor colorIteration(row, Color.red)
        }
      )
    }
  ).draw

}
