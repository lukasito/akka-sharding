package sk.lukasito

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.Random

class PerformanceTest extends Simulation {

  val scn = scenario("PerformanceTest")
    .exec {
      http("process payments")
        .get(s => {
          s"http://localhost:9090/merchants/${Math.abs(new Random().nextInt())}/payments/${Math.abs(new Random().nextInt())}"
        })
        .check(status.is(200))
    }

  setUp(
    scn.inject(constantUsersPerSec(400) during 15)
  )
}
