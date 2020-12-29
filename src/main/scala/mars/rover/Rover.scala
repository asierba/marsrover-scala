package mars.rover

import mars.rover.Direction.Direction

case class Position(x: Int, y: Int)

object Direction extends Enumeration {
  type Direction = Value
  val NORTH, EAST, WEST, SOUTH = Value
}

case class Rover(position: Position, direction: Direction, obstacles: List[Position] = List(), stopped: Boolean = false) {
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

  private def newPosition = direction match {
    case Direction.NORTH => Position(position.x, position.y + 1)
    case Direction.WEST => Position(position.x - 1, position.y)
    case Direction.EAST => Position(position.x + 1, position.y)
    case Direction.SOUTH => Position(position.x, position.y - 1)
  }

  private def moveForward = {
    val nextPosition = newPosition
    if (obstacles.contains(nextPosition))
      Rover(position, direction, obstacles, true)
    else
      Rover(nextPosition, direction, obstacles)
  }

  private def moveBackwards = direction match {
    case Direction.NORTH => Rover(Position(position.x, position.y - 1), direction, obstacles)
    case Direction.WEST => Rover(Position(position.x + 1, position.y), direction, obstacles)
    case Direction.EAST => Rover(Position(position.x - 1, position.y), direction, obstacles)
    case Direction.SOUTH => Rover(Position(position.x, position.y + 1), direction, obstacles)
  }

  private def moveRight = direction match {
    case Direction.NORTH => Rover(position, Direction.EAST, obstacles)
    case Direction.WEST => Rover(position, Direction.NORTH, obstacles)
    case Direction.EAST => Rover(position, Direction.SOUTH, obstacles)
    case Direction.SOUTH => Rover(position, Direction.WEST, obstacles)
  }

  private def moveLeft = direction match {
    case Direction.NORTH => Rover(position, Direction.WEST, obstacles)
    case Direction.WEST => Rover(position, Direction.SOUTH, obstacles)
    case Direction.EAST => Rover(position, Direction.NORTH, obstacles)
    case Direction.SOUTH => Rover(position, Direction.EAST, obstacles)
  }
}
