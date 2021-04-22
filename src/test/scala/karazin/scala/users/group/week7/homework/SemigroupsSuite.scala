package karazin.scala.users.group.week7.homework

/*
  Write test for semigroups

  Make sure that the following cases are tested:
    • Map[String, Int] works
    • Map[String, Boolean] works
    • Map[String, String] works
    • Map[String, List[Int]], Map[String, List[Boolean]], Map[String, List[String]] works

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
    • https://scalameta.org/munit/docs/assertions.html#compileerrors
    • https://scalameta.org/munit/docs/integrations/scalacheck.html

 */

import munit.ScalaCheckSuite
import org.scalacheck.Prop._

class SemigroupsSuite extends ScalaCheckSuite:

  property("successful test example") {
    forAll { (int: Int) =>
      int == int
    }
  }

  /*
    Test example:
      generate Map[String, Int]
      generate Map[String, Int]
      prove that generated maps are combined:
        in case of the same key the values should be combined in the result map
        in case of different keys the values should be added to the result map

    property("monoids for Map[String, Int]") {
      forAll { (map1: Map[String, Int], map2: Map[String, Int]) =>

        (map1 combine map2) == ???

      }
    }
  */