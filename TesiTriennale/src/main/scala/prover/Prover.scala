package prover

import formulaWff._
import signedFormula._
import tableaux.{AlphaNode, BetaNode, Leaf, Tableaux}

import scala.collection.mutable

/**
  * Definisce un prover che, data una formula, costruisce una formula segnata (con segno F) e il suo tableaux.
  * @param f Definisce la formula da cui ricava il tableaux.
  */
class Prover(f: FormulaWff) {

  /**
    * Definisce la formula segnata costruita dalla formula passata come parametro al costruttore.
    */
  private val sf = SF(F, f)

  /**
    * Definisce un metodo che costruisce e restituisce il tableaux della formula sottoforma di albero binario.
    * @return Ritorna il tableaux costruito.
    */
  def prove: Tableaux = {

    def iter(nodeToExpand: SF,
             actualEls: mutable.Map[EnumFormula.Value, List[SF]]):
    Tableaux = {
      if (nodeToExpand == null)
        Leaf(actualEls(EnumFormula.Var))
      else {
        val actualExceptThis = popSF(actualEls, nodeToExpand)
        nodeToExpand match {
          case SF(T, And(l, r)) =>
            AlphaNode(nodeToExpand, iter(nextNodeToExpand(
              pushSF(actualExceptThis,SF(T, l), SF(T, r))),
              pushSF(actualExceptThis,SF(T, l), SF(T, r))), actualExceptThis)
          case SF(F, Or(l, r)) =>
            AlphaNode(nodeToExpand, iter(nextNodeToExpand(
              pushSF(actualExceptThis,SF(F, l), SF(F, r))),
              pushSF(actualExceptThis,SF(F, l), SF(F, r))), actualExceptThis)
          case SF(F, Implies(l, r)) =>
            AlphaNode(nodeToExpand, iter(nextNodeToExpand(
              pushSF(actualExceptThis,SF(T, l), SF(F, r))),
              pushSF(actualExceptThis,SF(T, l), SF(F, r))), actualExceptThis)
          case SF(T, Not(s)) =>
            AlphaNode(nodeToExpand, iter(nextNodeToExpand(
              pushSF(actualExceptThis, SF(F, s))),
              pushSF(actualExceptThis, SF(F, s))), actualExceptThis)
          case SF(F, Not(s)) =>
            AlphaNode(nodeToExpand, iter(nextNodeToExpand(
              pushSF(actualExceptThis, SF(T, s))),
              pushSF(actualExceptThis, SF(T, s))), actualExceptThis)
          case SF(F, And(l, r)) =>
            BetaNode(nodeToExpand, iter(nextNodeToExpand(
              pushSF(actualExceptThis, SF(F, l))),
              pushSF(actualExceptThis, SF(F, l))),
              iter(nextNodeToExpand(
                pushSF(actualExceptThis, SF(F, r))),
                pushSF(actualExceptThis,
                SF(F, r))), actualExceptThis)
          case SF(T, Or(l, r)) =>
            BetaNode(nodeToExpand, iter(nextNodeToExpand(
              pushSF(actualExceptThis, SF(T, l))),
              pushSF(actualExceptThis, SF(T, l))),
              iter(nextNodeToExpand(
                pushSF(actualExceptThis, SF(T, r))),
                pushSF(actualExceptThis, SF(T, r))), actualExceptThis)
          case SF(T, Implies(l, r)) =>
            BetaNode(nodeToExpand, iter(nextNodeToExpand(
              pushSF(actualExceptThis, SF(F, l))),
              pushSF(actualExceptThis, SF(F, l))),
              iter(nextNodeToExpand
              (pushSF(actualExceptThis, SF(T, r))),
                pushSF(actualExceptThis, SF(T, r))), actualExceptThis)
          case SF(_, Var(_)) => Leaf(pushSF(actualExceptThis, nodeToExpand)
            (EnumFormula.Var))
        }
      }
    }

    def popSF(map: mutable.Map[EnumFormula.Value, List[SF]], el: SF) = {
      map(el.getType) = map(el.getType).tail
      map
    }

    def nextNodeToExpand(map: mutable.Map[EnumFormula.Value, List[SF]]):
      SF = {
      if ((map(EnumFormula.Alpha) == Nil) &&
         (map(EnumFormula.Beta) == Nil))
        null
      else
      if (map(EnumFormula.Alpha) != Nil)
        map(EnumFormula.Alpha).head
      else
        map(EnumFormula.Beta).head
    }

    def pushSF(map: mutable.Map[EnumFormula.Value, List[SF]], newEls: SF*):
      mutable.Map[EnumFormula.Value, List[SF]] ={
      val temp = mutable.Map(EnumFormula.Alpha -> map(EnumFormula.Alpha),
        EnumFormula.Beta -> map(EnumFormula.Beta),
        EnumFormula.Var -> map(EnumFormula.Var))
      newEls.foreach(p => temp(p.getType) = p :: temp(p.getType))
      temp
    }

    sf.getType match {
      case EnumFormula.Alpha =>
        iter(sf, mutable.Map(EnumFormula.Alpha -> List(sf),
          EnumFormula.Beta -> Nil, EnumFormula.Var -> Nil))
      case EnumFormula.Beta =>
        iter(sf, mutable.Map(EnumFormula.Alpha -> Nil,
          EnumFormula.Beta -> List(sf), EnumFormula.Var -> Nil))
      case EnumFormula.Var =>
        iter(sf, mutable.Map(EnumFormula.Alpha -> Nil,
          EnumFormula.Beta -> Nil, EnumFormula.Var -> List(sf)))
    }
  }

  /**
    * Rappresenta il prover in una stringa.
    * @return Ritorna la rappresentazione del prover in stringa.
    */
  override def toString: String = "Prover(" + f + ")"

}
