package StudentManagerTestSimulation;


import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class StudentManagerTestSimulation extends Simulation {

    val httpProtocol = http
        .baseUrl("http://localhost:8081")
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        .doNotTrackHeader("1")
        .acceptLanguageHeader("zh-CN,zh;q=0.5")
        .acceptEncodingHeader("gzip, deflate")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")
    
    val scn = scenario("Scenario Name")
    .exec( 
        http("index_request").get("/")
    )
    .exec(
        http("list_request").get("/students")
    )
    .exec(
        http("detail_request").get("/students/181860058")
    )


    setUp(scn.inject(atOnceUsers(500)).protocols(httpProtocol))
}