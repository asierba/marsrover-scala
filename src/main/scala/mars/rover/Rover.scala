package mars.rover

import mars.rover.Direction.Direction

case class Rover(position: Position, direction: Direction) {
  def executeCommands(commands: String) = {
    val moves = commands.length
    Rover(Position(position.x + moves, position.y), Direction.EAST)
  }

}

case class Position(x: Int, y: Int)

object Direction extends Enumeration {
  type Direction = Value
  val EAST = Value
}