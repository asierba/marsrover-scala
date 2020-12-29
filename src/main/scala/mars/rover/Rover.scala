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

  private def executeCommand(command: Char): Rover = if (stopped)
    this
  else
    command match {
      case 'L' => rotateLeft
      case 'R' => rotateRight
      case 'F' => moveForward
      case 'B' => moveBackwards
    }

  private def moveForward: Rover = moveOne(true)

  private def moveBackwards: Rover = moveOne(false)

  private def rotateRight: Rover = direction match {
    case Direction.NORTH => Rover(position, Direction.EAST, obstacles, stopped)
    case Direction.WEST => Rover(position, Direction.NORTH, obstacles, stopped)
    case Direction.EAST => Rover(position, Direction.SOUTH, obstacles, stopped)
    case Direction.SOUTH => Rover(position, Direction.WEST, obstacles, stopped)
  }

  private def rotateLeft: Rover = direction match {
    case Direction.NORTH => Rover(position, Direction.WEST, obstacles, stopped)
    case Direction.WEST => Rover(position, Direction.SOUTH, obstacles, stopped)
    case Direction.EAST => Rover(position, Direction.NORTH, obstacles, stopped)
    case Direction.SOUTH => Rover(position, Direction.EAST, obstacles, stopped)
  }

  private def moveOne(forward: Boolean): Rover = {
    val increment = if (forward) 1 else -1;
    val nextPosition = newPosition(direction, increment)
    if (obstacles.contains(nextPosition))
      Rover(position, direction, obstacles, true)
    else
      Rover(nextPosition, direction, obstacles)
  }

  private def newPosition(direction: Direction, increment: Int): Position = direction match {
    case Direction.NORTH => Position(position.x, position.y + increment)
    case Direction.WEST => Position(position.x - increment, position.y)
    case Direction.EAST => Position(position.x + increment, position.y)
    case Direction.SOUTH => Position(position.x, position.y - increment)
  }
}
