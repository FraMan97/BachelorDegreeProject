import signedFormula._
import formulaWff._

val signedformula  = SF(F,Implies(Implies(Not(Var("A")), Or(Var("B"), Var("C"))), Or(Implies(Not(Var("A")), Var("B")), Implies(Not(Var("A")), Var("C")))))

val signedformula2 = SF(F,Implies(Implies(Var("P"), Implies(Var("Q"),Var("R"))), Implies(Implies(Var("P"),Var("Q")), Implies(Var("P"),Var("R")))))

val signedformula3 = SF(F, Not(Not(Not(Not(Var("A"))))))

val signedformula4 = SF(F, And(Var("A"), Not(Var("A"))))

val signedformula5 = SF(F, Or(Var("A"), Not(Var("A"))))



