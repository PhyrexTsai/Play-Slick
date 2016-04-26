package controllers

import models.{Appendixs, Appendix}
import play.api.mvc._
import play.api.Play.current
import play.api.db._
import play.api.libs.json._

/**
  * Created by yjtsai on 2016/4/26.
  */
object Application extends Controller {
  def index = Action {
    Ok("Hello")
  }

  // use db connection
  def db = Action {
    var out = ""
    val conn = DB.getConnection()
    try {
      val stmt = conn.createStatement
      val rs = stmt.executeQuery("SELECT count(*) as testkey FROM \"Person\"; ")

      while (rs.next()) {
        out += rs.getString("testkey")
      }
    } finally {
      conn.close()
    }
    Ok(out)
  }

  // use slick framework
  def appendixall = Action {
    // use implicit to set json format
    implicit val appendixFormat = Json.format[Appendix]
    Ok(Json.obj("appendix" -> Appendixs.getAll))
  }

  // use slick framework
  def appendix(id:String) = Action {
    // use Appendixs.appendixWrites to set json format
    Ok(Json.toJson(Appendixs.getBy(id))(Appendixs.appendixWrites))
  }

}
