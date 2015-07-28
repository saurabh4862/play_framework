
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object signup_Scope0 {
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

class signup extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

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
<h2>SignUp Page</h2>
<div class="form-group" style="font-size:16px">

    <form style="background-color:silver" action=""""),_display_(/*13.52*/routes/*13.58*/.Application.addUsers()),format.raw/*13.81*/("""" method="post">
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
            <button style="font-size:16px" type="submit">SignUp</button>
        </div>
    </form>
</div>
</div>
""")))}))
      }
    }
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}


}

/**/
object signup extends signup_Scope0.signup
              /*
                  -- GENERATED --
                  DATE: Tue Jul 28 14:25:49 IST 2015
                  SOURCE: /home/zemoso07/activator-dist-1.3.5/play-java/app/views/signup.scala.html
                  HASH: 1bfd22344191b98165f0e797ce355b6e7c74469c
                  MATRIX: 747->1|859->18|887->21|918->44|957->46|984->47|1529->565|1544->571|1588->594
                  LINES: 27->1|32->1|34->3|34->3|34->3|35->4|44->13|44->13|44->13
                  -- GENERATED --
              */
          