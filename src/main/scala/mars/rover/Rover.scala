package mars.rover

import mars.rover.Direction.Direction

case class Rover(position: Position, direction: Direction) {
  def executeCommands(commands: String) = {
    val moves = commands.length
    direction match {
      case Direction.NORTH => Rover(Position(position.x, position.y + moves), direction)
      case Direction.WEST => Rover(Position(position.x - moves, position.y), direction)
      case Direction.EAST => Rover(Position(position.x + moves, position.y), direction)
      case Direction.SOUTH => Rover(Position(position.x, position.y - moves), direction)
    }
  }

}

case class Position(x: Int, y: Int)

object Direction extends Enumeration {
  type Direction = Value
  val NORTH, EAST, WEST, SOUTH = Value
}