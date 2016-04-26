package models

import play.api.Play.current
import play.api.db.DB
import play.api.libs.json.{Json, Writes}
import slick.driver.PostgresDriver.simple._

/**
  * Created by yjtsai on 2016/4/26.
  */
case class Appendix(
  a_id : String,
  a_name : String
)

class Appendixs(tag : Tag) extends Table[(Appendix)](tag, "appendix") {
  def a_id = column[String]("a_id")
  def a_name = column[String]("a_name")
  def * = (a_id, a_name) <> (Appendix.tupled, Appendix.unapply)
}

object Appendixs {
  val appendixs = TableQuery[Appendixs]

  def getAll : List[Appendix] = {
    Database.forDataSource(DB.getDataSource()) withSession {
      implicit session => appendixs.list
    }
  }

  def getBy(a_id : String) : Appendix = {
    Database.forDataSource(DB.getDataSource()) withSession {
      implicit session => appendixs.list.filter(_.a_id == a_id).head
    }
  }

  implicit val appendixWrites = new Writes[Appendix] {
    def writes(appendix: Appendix) = Json.obj(
      "a_id" -> appendix.a_id,
      "a_name" -> appendix.a_name)
  }

}