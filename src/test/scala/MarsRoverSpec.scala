import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class MarsRoverSpec extends AnyFlatSpec with should.Matchers {
  "MarsRover" should "reports initial coordinate and direction" in {
    val rover = Rover(Position(4,2), Direction.EAST)
    rover.direction shouldBe Direction.EAST
    rover.position shouldBe Position(4,2)
  }
}

case class Position(x: Int, y: Int)
object Direction extends Enumeration {
  type Direction = Value
  val EAST = Value
}
import Direction._
case class Rover(position: Position, direction: Direction)
