
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object index_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._

class index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.19*/("""

"""),_display_(/*3.2*/main("Welcome to Play")/*3.25*/ {_display_(Seq[Any](format.raw/*3.27*/("""
"""),format.raw/*4.1*/("""<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<div class="container">
    <h2>Log In Page</h2>
    <div class="form-group" style="font-size:16px">

        <form style="background-color:silver" action=""""),_display_(/*13.56*/routes/*13.62*/.Application.findUsers()),format.raw/*13.86*/("""" method="get">
            <div class="jumbotron">
                FirstName:
                </br>
                <input style="font-size:16px" type="text" class="form-control" name="firstName"/>
                </br>
                LastName:
                </br>
                <input style="font-size:16px" type="text"  class="form-control" name="lastName"/>
                </br>
                Password:
                </br>
                <input style="font-size:16px" type="password" class="form-control" name="password"/>
                </br>
                <button style="font-size:16px" type="submit">LogIn</button>
            </div>
        </form>
    </div>
    <details >
        <summary style="background-color:gold" > New User</summary>
        <p>Please SignUp here <a href=""""),_display_(/*33.41*/routes/*33.47*/.Application.signup()),format.raw/*33.68*/("""">SignUp</a></p>
    </details>
</div>


</br>
</br>




""")))}),format.raw/*44.2*/("""
"""))
      }
    }
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}


}

/**/
object index extends index_Scope0.index
              /*
                  -- GENERATED --
                  DATE: Tue Jul 28 14:37:25 IST 2015
                  SOURCE: /home/zemoso07/activator-dist-1.3.5/play-java/app/views/index.scala.html
                  HASH: 47e7cd045aca0f26f0c1b09a70304f2d15c3d3b1
                  MATRIX: 745->1|857->18|885->21|916->44|955->46|982->47|1539->577|1554->583|1599->607|2431->1412|2446->1418|2488->1439|2576->1497
                  LINES: 27->1|32->1|34->3|34->3|34->3|35->4|44->13|44->13|44->13|64->33|64->33|64->33|75->44
                  -- GENERATED --
              */
          