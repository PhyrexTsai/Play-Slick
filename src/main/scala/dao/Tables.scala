package dao
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.PostgresDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema = Users.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Users
   *  @param userid Database column userid SqlType(serial), AutoInc, PrimaryKey
   *  @param email Database column email SqlType(varchar), Length(500,true)
   *  @param password Database column password SqlType(varchar), Length(500,true)
   *  @param memo Database column memo SqlType(text), Default(None)
   *  @param deletetime Database column deletetime SqlType(timestamp), Default(None) */
  case class UsersRow(userid: Int, email: String, password: String, memo: Option[String] = None, deletetime: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[java.sql.Timestamp]]): GR[UsersRow] = GR{
    prs => import prs._
    UsersRow.tupled((<<[Int], <<[String], <<[String], <<?[String], <<?[java.sql.Timestamp]))
  }
  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends Table[UsersRow](_tableTag, "users") {
    def * = (userid, email, password, memo, deletetime) <> (UsersRow.tupled, UsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(userid), Rep.Some(email), Rep.Some(password), memo, deletetime).shaped.<>({r=>import r._; _1.map(_=> UsersRow.tupled((_1.get, _2.get, _3.get, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column userid SqlType(serial), AutoInc, PrimaryKey */
    val userid: Rep[Int] = column[Int]("userid", O.AutoInc, O.PrimaryKey)
    /** Database column email SqlType(varchar), Length(500,true) */
    val email: Rep[String] = column[String]("email", O.Length(500,varying=true))
    /** Database column password SqlType(varchar), Length(500,true) */
    val password: Rep[String] = column[String]("password", O.Length(500,varying=true))
    /** Database column memo SqlType(text), Default(None) */
    val memo: Rep[Option[String]] = column[Option[String]]("memo", O.Default(None))
    /** Database column deletetime SqlType(timestamp), Default(None) */
    val deletetime: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("deletetime", O.Default(None))
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))
}
