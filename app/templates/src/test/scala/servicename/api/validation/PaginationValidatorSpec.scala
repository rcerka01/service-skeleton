package <%= packageName %>.api.validation

import <%= packageName %>.api.directives.custom.PaginateDirectives
import org.scalatest.wordspec.AnyWordSpec

class PaginationValidatorSpec extends AnyWordSpec with PaginateDirectives {
  val maxPageLimit = 10
  "The isInvalidPageLimit method" should {
    "return true for a page size of -1" in {
      val invalidPageLimit = isInvalidPageLimit(maxPageLimit)(-1)
      assert(invalidPageLimit)
    }

    "return false for page size of 0" in {
      val invalidPageLimit = isInvalidPageLimit(maxPageLimit)(0)
      assert(!invalidPageLimit)
    }

    "return false page size of the maximum" in {
      val invalidPageLimit = isInvalidPageLimit(maxPageLimit)(maxPageLimit)
      assert(!invalidPageLimit)
    }

    "return true a page size of more than the maximum" in {
      val invalidPageLimit = isInvalidPageLimit(maxPageLimit)(maxPageLimit + 1)
      assert(invalidPageLimit)
    }
  }

  "The isInvalidOffset method" should {
    "return true for offsets less than 0" in {
      val invalidPageOffset = isInvalidOffset(-1)
      assert(invalidPageOffset)
    }

    "return false for an offset of 0" in {
      val invalidPageOffset = isInvalidOffset(0)
      assert(!invalidPageOffset)
    }

    "return false for any positive integer" in  {
      val invalidPageOffset = Seq(1, 7, 3000, Integer.MAX_VALUE).exists(isInvalidOffset)
      assert(!invalidPageOffset)
    }
  }
}
