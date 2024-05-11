module dealership

sig Dealership {
	vendors : set Person,
	brands : set Brand,
	stock : set Car,
	sold : vendors -> set Car
}


sig Person {
	id : Int
}

abstract sig boolean {}
one sig true extends boolean {}
one sig false extends boolean {}

sig Car {
	brand : Brand,
}

abstract sig Brand {}
one sig Honda extends Brand {}
one sig Mitsubishi extends Brand {}
one sig Nissan extends Brand {}
one sig Hyundai extends Brand {}
one sig Subaru extends Brand {}

fact minimumStockSatisfied {
      //This fact accounts for the rule "A dealership may never have fewer than 5 cars available for sale"
}

fact allCarsInSoldBrands {
	//This fact accounts for the rule "A dealership may only sell cars from the brands it carries"
}

fact allCarsInStockNotSold {
	//This fact accounts for the rule "If a car is in stock, then it can not appear as having been sold"


pred preconditionForSell[d : Dealership, c : Car, v : Person]{
	//This predicate leaves space in case some preconditions are
	//required for method "sell".
}

pred sell[d, d' : Dealership, c : Car, v : d.vendors]{
	//This predicate describes the action of selling a car.
	//Parameter d represents the initial state.
	//Parameter d' represents the new (final) state obtained after executing the action.
}


pred preconditionForBuy[d : Dealership, c : Car]{
	//This predicate leaves space in case some preconditions are
	//required for action "buy".
}

pred buy[d, d' : Dealership, c : Car]{
	//This predicate describes the action of buying a car.
	//Parameter d represents the initial state.
	//Parameter d' represents the new (final) state obtained after executing the action.
}


run { 
//Add here the property that you want to check in order
//to test your model.
} for 10 but 4 int
