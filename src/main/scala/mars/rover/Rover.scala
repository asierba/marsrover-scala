package mars.rover

import mars.rover.Direction.Direction

case class Rover(position: Position, direction: Direction) {
  def executeCommands(commands: String): Rover = {
    if (commands.length > 1) {
      val command = commands.head
      val restOfCommand = commands.tail
      val rover = executeCommand(command)
      rover executeCommands restOfCommand
    } else {
      val command = commands.head
      executeCommand(command)
    }
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