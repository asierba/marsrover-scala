package mars.rover

import mars.rover.Direction.Direction

case class Position(x: Int, y: Int)

object Direction extends Enumeration {
  type Direction = Value
  val NORTH, EAST, WEST, SOUTH = Value
}

case class Rover(position: Position, direction: Direction) {
  def executeCommands(commands: String): Rover = commands.length match {
      case 0 => this
      case 1 => executeCommand(commands.head)
      case _ =>
        val rover = executeCommand(commands.head)
        rover executeCommands commands.tail
    }

  private def executeCommand(command: Char) = command match {
    case 'L' => moveLeft
    case 'R' => moveRight
    case 'F' => moveForward
    case 'B' => moveBackwards
  }

  private def moveForward = direction match {
    case Direction.NORTH => Rover(Position(position.x, position.y + 1), direction)
    case Direction.WEST => Rover(Position(position.x - 1, position.y), direction)
    case Direction.EAST => Rover(Position(position.x + 1, position.y), direction)
    case Direction.SOUTH => Rover(Position(position.x, position.y - 1), direction)
  }

  private def moveBackwards = direction match {
    case Direction.NORTH => Rover(Position(position.x, position.y + -1), direction)
    case Direction.WEST => Rover(Position(position.x - -1, position.y), direction)
    case Direction.EAST => Rover(Position(position.x + -1, position.y), direction)
    case Direction.SOUTH => Rover(Position(position.x, position.y - -1), direction)
  }

  private def moveRight = direction match {
    case Direction.NORTH => Rover(position, Direction.EAST)
    case Direction.WEST => Rover(position, Direction.NORTH)
    case Direction.EAST => Rover(position, Direction.SOUTH)
    case Direction.SOUTH => Rover(position, Direction.WEST)
  }

  private def moveLeft = direction match {
    case Direction.NORTH => Rover(position, Direction.WEST)
    case Direction.WEST => Rover(position, Direction.SOUTH)
    case Direction.EAST => Rover(position, Direction.NORTH)
    case Direction.SOUTH => Rover(position, Direction.EAST)
  }
}
