import formulaWffSpecification.{FormulaWffSpecification}

val tests = FormulaWffSpecification
tests.checkProp.foreach(el => {
   println("(" + el._1 + ")" + " => " + el._2.passed + " : " + el._2.status)
 })

