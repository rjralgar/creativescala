/**
  * Created by rhys.algar on 6/7/16.
  */

import doodle.core._
import doodle.core.Image._
import doodle.core.Point._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._

import doodle.turtle._
import doodle.turtle.Instruction._

object Turtles extends App {

  val instructions = List(forward(10), turn(90.degrees), forward(10), turn(90.degrees), forward(10), turn(90.degrees), forward(10))
  val path = Turtle.draw(instructions)

//  def polygon(sides: Int, sideLength: Double): Image = {
//    val angle = (360/sides).degrees
//    def iter(n: Int): List[Instruction] = {
//      n match {
//        case 0 => Nil
//        case n => forward(sideLength) :: turn(angle) :: iter(n-1)
//      }
//    }
//    Turtle.draw(iter(sides))
//  }
//
//  polygon(7, 200).draw

//  def squareSpiral(n: Int, finalSize: Int): List[Instruction] = {
//    n match {
//      case 0 => Nil
//      case i => forward((finalSize-n+1)*3) :: turn(89.5.degrees) :: squareSpiral(n-1, finalSize)
//    }
//  }
//
//  Turtle.draw(squareSpiral(300, 300)).draw


  val stepSize = 10
  // stepSize: Int = 10
  def rule(i: Instruction): List[Instruction] =
    i match {
      case Forward(_) => List(forward(5.0), forward(stepSize))
      case NoOp =>
        List(branch(turn(45.degrees), forward(stepSize), noop),
          branch(turn(-45.degrees), forward(stepSize), noop))
      case other => List(other)
    }

  val seed = forward(20) :: NoOp :: Nil

  println(seed)

  var inst = seed.map(i => rule(i)).flatten//.map(i => rule(i)).flatten

  println(inst)

  Turtle.draw(inst).draw

  def tree(depth: Int, seed: List[Instruction], rule: Instruction => List[Instruction]): List[Instruction] = {
    def iter(n: Int): List[Instruction] = {

      n match {
        case 0 => seed
        case n => iter(n-1).map(i => rule(i)).flatten
      }
    }
    iter(depth)
  }

  // Issue - doesn't get inside the branches

  def double[A](a: List[A]): List[A] = {
    a.flatMap(a => List(a, a))
  }

  println(double(List(1,2,3,4)))

  def nothing[A](a: List[A]): List[A] = {
    a.flatMap(a => Nil)
  }

  println(nothing(List(1,2,3,4)))

  def rewrite(instructions: List[Instruction], rule: Instruction => List[Instruction]): List[Instruction] = {
    instructions.flatMap(instruction => {
      instruction match {
        case Branch(instructions) => List(branch(rewrite(instructions, rule):_*))
        case instruction => rule(instruction)
      }
    })
  }

  Turtle.draw(rewrite(rewrite(seed, rule),rule)).draw
}
