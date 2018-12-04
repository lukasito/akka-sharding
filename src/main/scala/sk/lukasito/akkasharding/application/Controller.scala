package sk.lukasito.akkasharding.application

import akka.actor.ActorSystem
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.{HttpApp, Route}
import akka.stream.ActorMaterializer
import sk.lukasito.akkasharding.domain.{Merchant, Payment, PaymentProcessor}

object Controller extends HttpApp {

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher


  override protected def routes: Route =
    path("merchants" / IntNumber / "payments" / IntNumber) { (maid, pid) =>
      get {
        val merchant = Merchant(maid)
        val payment = Payment(pid)
        val processed = PaymentProcessor.processPayment(merchant, payment)
        complete(HttpEntity(ContentTypes.`application/json`, processed.toJson))
      }
    }

}
