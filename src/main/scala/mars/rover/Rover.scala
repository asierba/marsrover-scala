package mars.rover

import mars.rover.Direction.Direction

case class Rover(position: Position, direction: Direction) {
  def executeCommands(commands: String) = {
    val moves = commands.length
    val realMoves = if (commands(0) == 'B') -moves else moves
    direction match {
      case Direction.NORTH => Rover(Position(position.x, position.y + realMoves), direction)
      case Direction.WEST => Rover(Position(position.x - realMoves, position.y), direction)
      case Direction.EAST => Rover(Position(position.x + realMoves, position.y), direction)
      case Direction.SOUTH => Rover(Position(position.x, position.y - realMoves), direction)
    }
  }

}

case class Position(x: Int, y: Int)

object Direction extends Enumeration {
  type Direction = Value
  val NORTH, EAST, WEST, SOUTH = Value
}