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

  private def rotateRight: Rover = Rover(position, nextDirectionRight(direction), obstacles, stopped)

  private def rotateLeft: Rover = Rover(position, nextDirectionLeft(direction), obstacles, stopped)

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

  private def nextDirectionRight(direction: Direction): Direction = direction match {
    case Direction.NORTH => Direction.EAST
    case Direction.WEST => Direction.NORTH
    case Direction.EAST => Direction.SOUTH
    case Direction.SOUTH => Direction.WEST
  }

  private def nextDirectionLeft(direction: Direction): Direction = direction match {
    case Direction.NORTH => Direction.WEST
    case Direction.WEST => Direction.SOUTH
    case Direction.EAST => Direction.NORTH
    case Direction.SOUTH => Direction.EAST
  }
}
