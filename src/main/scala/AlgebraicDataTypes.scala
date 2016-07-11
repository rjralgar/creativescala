/**
  * Created by rhys.algar on 7/11/16.
  */

import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._

object AlgebraicDataTypes extends App {

  sealed abstract class Instruction extends Product with Serializable
  final case class Forward(distance: Double) extends Instruction
  final case class Turn(angle: Angle) extends Instruction
  final case class Branch(instructions: List[Instruction]) extends Instruction
  final case class NoOp() extends Instruction

  final case class TurtleState(at: Vec, heading: Angle)

  object Turtle {
    def draw(instructions: List[Instruction]): Image = {

    }

//    def process(state: TurtleState, instruction: Instruction): (TurtleState, List[PathElement]) = {
//      instruction match {
//        case Forward(distance) =>
//          val newAt = state.at + Vec.polar(state.heading, distance)
//          val newState = TurtleState(newAt, state.heading)
//          val element = lineTo(newAt.toPoint)
//          (newState, List(element))
//        case Turn(angle) =>
//          val newHeading = state.heading + angle
//          val newState = TurtleState(state.at, newHeading)
//          (newState, Nil)
//        case NoOp() =>
//          (state, Nil)
//      }
//    }

    def iterate(state: TurtleState, instructions: List[Instruction]): List[PathElement] = {
      instructions match {
        case Nil => Nil
        case hd :: tl =>
          val (newState, newElements) = process(state, hd)
          newElements ::: iterate(newState, tl)
      }
    }

    def process(state: TurtleState, instruction: Instruction): (TurtleState, List[PathElement]) = {
      instruction match {
        case Forward(distance) =>
          val newAt = state.at + Vec.polar(state.heading, distance)
          val newState = TurtleState(newAt, state.heading)
          val element = lineTo(newAt.toPoint)
          (newState, List(element))
        case Turn(angle) =>
          val newHeading = state.heading + angle
          val newState = TurtleState(state.at, newHeading)
          (newState, Nil)
        case Branch(instructions) =>
          (state, moveTo(state.at.toPoint) :: iterate(state, instructions))
        case NoOp() =>
          (state, Nil)
      }
    }

  }

}
