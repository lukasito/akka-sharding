package sk.lukasito.akkasharding.domain

object PaymentProcessor {

  case class PaymentProcessed(mid: Int, pid: Int) {
    def toJson: String = "\"mid\"" + ": " + mid + ", \"pid\"" + ": " + pid
  }

  def processPayment(merchant: Merchant, payment: Payment) = {
    Thread.sleep(5)
    PaymentProcessed(merchant.id, payment.id)
  }
}
