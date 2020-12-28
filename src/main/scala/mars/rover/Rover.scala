package mars.rover

import mars.rover.Direction.Direction

case class Rover(position: Position, direction: Direction) {
  def executeCommands(commands: String): Rover =
    commands.length match {
      case 0 => this
      case 1 => executeCommand(commands.head)
      case _ =>
        val rover = executeCommand(commands.head)
        rover executeCommands commands.tail
    }

  private def executeCommand(command: Char) = {
    val realMoves = if (command == 'B') -1 else 1
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