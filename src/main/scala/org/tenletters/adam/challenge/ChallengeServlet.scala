package org.tenletters.adam.challenge

import org.scalatra._
import scalate.ScalateSupport
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import redis.clients.jedis.Jedis

object Redis {
	var jedis = new Jedis("localhost");
}


class ChallengeServlet extends ScalatraServlet with JacksonJsonSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  get("/users") {
  	Redis.jedis.smembers("users")
  }

  get("/user/:id") {
  	Redis.jedis.get("user:" + params("id"))
  }
}