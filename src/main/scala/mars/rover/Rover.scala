package mars.rover

import mars.rover.Direction.Direction

case class Rover(position: Position, direction: Direction) {
  def executeCommands(commands: String) = {
    val moves = commands.length
    if (direction == Direction.WEST) {
      Rover(Position(position.x - moves, position.y), direction)
    }
    else if (direction == Direction.NORTH) {
      Rover(Position(position.x, position.y + moves), direction)
    } else {
      Rover(Position(position.x + moves, position.y), direction)
    }
  }

}

case class Position(x: Int, y: Int)

object Direction extends Enumeration {
  type Direction = Value
  val NORTH, EAST, WEST = Value
}