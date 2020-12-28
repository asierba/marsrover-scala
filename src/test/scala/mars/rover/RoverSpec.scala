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
}
