package mars.rover

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class RoverSpec extends AnyFlatSpec with should.Matchers {
  "Rover" should "reports initial coordinate and direction" in {
    val rover = Rover(Position(4,2), Direction.EAST)
    rover.direction shouldBe Direction.EAST
    rover.position shouldBe Position(4,2)
  }
}
