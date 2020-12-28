package mars.rover

import mars.rover.Direction.Direction

case class Rover(position: Position, direction: Direction) {
  def executeCommands(commands: String): Rover = {
    if (commands.length == 0) {
      this
    }
    else if (commands.length > 1) {
      val firstCommand = commands.head
      val restOfCommands = commands.tail
      val rover = executeCommand(firstCommand)
      rover executeCommands restOfCommands
    } else {
      val firstCommand = commands.head
      executeCommand(firstCommand)
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