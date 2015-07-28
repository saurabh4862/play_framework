
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/zemoso07/activator-dist-1.3.5/play-java/conf/routes
// @DATE:Tue Jul 28 15:07:47 IST 2015


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
