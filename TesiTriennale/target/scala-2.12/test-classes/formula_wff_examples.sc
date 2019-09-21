import formulaWff._

val formula1 = Implies(Implies(Not(Var("A")), Or(Var("B"), Var("C"))), Or(Implies(Not(Var("A")), Var("B")), Implies(Not(Var("A")), Var("C"))))
formula1.size
formula1.depth
formula1.leaves

val formula2 = Implies(Implies(Var("P"), Implies(Var("Q"),Var("R"))), Implies(Implies(Var("P"),Var("Q")), Implies(Var("P"),Var("R"))))
formula2.size
formula2.depth
formula2.leaves

val formula3 = Not(Not(Not(Not(Var("A")))))
formula3.size
formula3.depth
formula3.leaves

val formula4 = And(Var("A"), Not(Var("A")))
formula4.size
formula4.depth
formula4.leaves

val formula5 = Or(Var("A"), Not(Var("A")))
formula5.size
formula5.depth
formula5.leaves



