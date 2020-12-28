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
    if (command == 'L') {
      direction match {
        case Direction.NORTH => Rover(position, Direction.WEST)
        case Direction.WEST => Rover(position, Direction.SOUTH)
        case Direction.EAST => Rover(position, Direction.NORTH)
        case Direction.SOUTH => Rover(position, Direction.EAST)
      }
    } else if (command == 'R') {
      direction match {
        case Direction.NORTH => Rover(position, Direction.EAST)
        case Direction.WEST => Rover(position, Direction.NORTH)
        case Direction.EAST => Rover(position, Direction.SOUTH)
        case Direction.SOUTH => Rover(position, Direction.WEST)
      }
    } else {
      val realMoves = if (command == 'B') -1 else 1
      direction match {
        case Direction.NORTH => Rover(Position(position.x, position.y + realMoves), direction)
        case Direction.WEST => Rover(Position(position.x - realMoves, position.y), direction)
        case Direction.EAST => Rover(Position(position.x + realMoves, position.y), direction)
        case Direction.SOUTH => Rover(Position(position.x, position.y - realMoves), direction)
      }
    }
  }
}

case class Position(x: Int, y: Int)

object Direction extends Enumeration {
  type Direction = Value
  val NORTH, EAST, WEST, SOUTH = Value
}