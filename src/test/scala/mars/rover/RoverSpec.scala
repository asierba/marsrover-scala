package mars.rover

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class RoverSpec extends AnyFlatSpec with should.Matchers {
  "Rover" should "reports initial coordinate and direction" in {
    val rover = Rover(Position(4,2), Direction.EAST)
    rover.direction shouldBe Direction.EAST
    rover.position shouldBe Position(4,2)
  }

  "Rover" should "move forward east" in {
    val initialRover = Rover(Position(4,2), Direction.EAST)

    val movedRover = initialRover.executeCommands("F")

    movedRover.direction shouldBe Direction.EAST
    movedRover.position shouldBe Position(5,2)
  }

  "Rover" should "move forward east multiple times" in {
    val initialRover = Rover(Position(0,0), Direction.EAST)

    val movedRover = initialRover.executeCommands("FFF")

    movedRover.direction shouldBe Direction.EAST
    movedRover.position shouldBe Position(3,0)
  }

  "Rover" should "move forward north" in {
    val initialRover = Rover(Position(0,0), Direction.NORTH)

    val movedRover = initialRover.executeCommands("FFFFF")

    movedRover.direction shouldBe Direction.NORTH
    movedRover.position shouldBe Position(0,5)
  }

  "Rover" should "move forward west" in {
    val initialRover = Rover(Position(3,1), Direction.WEST)

    val movedRover = initialRover.executeCommands("FF")

    movedRover.direction shouldBe Direction.WEST
    movedRover.position shouldBe Position(1,1)
  }

  "Rover" should "move forward south" in {
    val initialRover = Rover(Position(3,1), Direction.SOUTH)

    val movedRover = initialRover.executeCommands("F")

    movedRover.direction shouldBe Direction.SOUTH
    movedRover.position shouldBe Position(3,0)
  }

  "Rover" should "move backward" in {
    Rover(Position(3, 1), Direction.NORTH).executeCommands("BB") shouldBe
      Rover(Position(3, -1), Direction.NORTH)
    Rover(Position(3, 1), Direction.EAST).executeCommands("B") shouldBe
      Rover(Position(2, 1), Direction.EAST)
    Rover(Position(3, 1), Direction.SOUTH).executeCommands("B") shouldBe
      Rover(Position(3, 2), Direction.SOUTH)
    Rover(Position(3, 1), Direction.WEST).executeCommands("BBBBBB") shouldBe
      Rover(Position(9, 1), Direction.WEST)
  }

  "Rover" should "move backward and forward at the same time" in {
    Rover(Position(3, 1), Direction.NORTH).executeCommands("BFFF") shouldBe
      Rover(Position(3, 3), Direction.NORTH)
  }

  "Rover" should "stay still when no commands" in {
    Rover(Position(3, 1), Direction.NORTH).executeCommands("") shouldBe
      Rover(Position(3, 1), Direction.NORTH)
  }

  "Rover" should "rotates left" in {
    val position = Position(3, 1)
    Rover(position, Direction.NORTH).executeCommands("L") shouldBe
      Rover(position, Direction.WEST)
    Rover(position, Direction.WEST).executeCommands("L") shouldBe
      Rover(position, Direction.SOUTH)
    Rover(position, Direction.SOUTH).executeCommands("L") shouldBe
      Rover(position, Direction.EAST)
    Rover(position, Direction.EAST).executeCommands("L") shouldBe
      Rover(position, Direction.NORTH)
  }

  "Rover" should "rotates right" in {
    val position = Position(3, 1)
    Rover(position, Direction.NORTH).executeCommands("R") shouldBe
      Rover(position, Direction.EAST)
    Rover(position, Direction.EAST).executeCommands("R") shouldBe
      Rover(position, Direction.SOUTH)
    Rover(position, Direction.SOUTH).executeCommands("R") shouldBe
      Rover(position, Direction.WEST)
    Rover(position, Direction.WEST).executeCommands("R") shouldBe
      Rover(position, Direction.NORTH)
  }

  "Rover" should "work with multiple commands" in {
    Rover(Position(0, 0), Direction.NORTH).executeCommands("FLFFFRFLB") shouldBe
      Rover(Position(-2, 2), Direction.WEST)
  }

  "Rover" should "stop at obstacles going forward" in {
    val obstacles = List(Position(0,3))
    val rover = Rover(Position(0,0), Direction.NORTH, obstacles)
    val movedRover = rover.executeCommands("FFFF")
    movedRover.position shouldBe Position(0,2)
    movedRover.stopped shouldBe true
  }

  "Rover" should "stop at obstacles going forward after rotation" in {
    val obstacles = List(Position(0,3))
    val rover = Rover(Position(0,0), Direction.NORTH, obstacles)
    val movedRover = rover.executeCommands("FFFFLF")
    movedRover.position shouldBe Position(0,2)
    movedRover.stopped shouldBe true
  }

  "Rover" should "stop at obstacles going backwards" in {
    val obstacles = List(Position(1,0))
    val rover = Rover(Position(0,0), Direction.WEST, obstacles)
    val movedRover = rover.executeCommands("BB")
    movedRover.position shouldBe Position(0,0)
    movedRover.stopped shouldBe true
  }

  "Rover" should "not rotate after stopped " in {
    val obstacles = List(Position(1,0))
    val rover = Rover(Position(0,0), Direction.WEST, obstacles)
    val movedRover = rover.executeCommands("BBR")
    movedRover.position shouldBe Position(0,0)
    movedRover.stopped shouldBe true
    movedRover.direction shouldBe Direction.WEST
  }
}
