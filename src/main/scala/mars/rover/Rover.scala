package mars.rover

import mars.rover.Direction.Direction

case class Rover(position: Position, direction: Direction)

case class Position(x: Int, y: Int)

object Direction extends Enumeration {
  type Direction = Value
  val EAST = Value
}