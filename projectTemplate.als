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
	  all d: Dealership | #d.stock >= 5
}

fact allCarsInSoldBrands {
	//This fact accounts for the rule "A dealership may only sell cars from the brands it carries"
	all d: Dealership, c: Car | c.brand in d.brands implies c in d.stock
}

fact allCarsInStockNotSold {
	//This fact accounts for the rule "If a car is in stock, then it can not appear as having been sold"
	all d: Dealership, c: Car, v: Person | c in d.stock implies c not in d.sold[v]
}

pred preconditionForSell[d : Dealership, c : Car, v : Person]{
	//This predicate leaves space in case some preconditions are
	//required for method "sell".
	c in d.stock and v in d.vendors
}

pred sell[d, d_1 : Dealership, c : Car, v : d.vendors]{
	//This predicate describes the action of selling a car.
	//Parameter d represents the initial state.
	//Parameter d' represents the new (final) state obtained after executing the action.
	preconditionForSell[d, c, v]
    d_1.vendors = d.vendors
    d_1.brands = d.brands
    d_1.stock = d.stock - c
    d_1.sold = d.sold + (v -> c)
}


pred preconditionForBuy[d : Dealership, c : Car]{
	//This predicate leaves space in case some preconditions are
	//required for action "buy".
    c.brand in d.brands
}

pred buy[d, d_1 : Dealership, c : Car]{
	//This predicate describes the action of buying a car.
	//Parameter d represents the initial state.
	//Parameter d' represents the new (final) state obtained after executing the action.
	d_1.vendors = d.vendors
    d_1.brands = d.brands
    d_1.stock = d.stock + c
    d_1.sold = d.sold
}

// Assertions to test the model

assert InvariantBrandsAndVendors {
    all d, d_1: Dealership, c: Car, v: Person |
        sell[d, d_1, c, v] or buy[d, d_1, c] implies
        (d.vendors = d_1.vendors and d.brands = d_1.brands)
}

assert StockConsistency {
    all d, d_1: Dealership, c: Car, v: Person |
        (sell[d, d_1, c, v] or buy[d, d_1, c]) implies
        (all c_1: Car | (c_1 in d.stock and c_1 != c) implies c_1 in d_1.stock) and
        (all c_1: Car | (c_1 in d_1.stock and c_1 != c) implies c_1 in d.stock)
}


assert SoldCarsBelongToBrands {
    all d, d_1: Dealership, c: Car, v: Person |
    sell[d, d_1, c, v] implies c.brand in d_1.brands
}

assert CarNotInStockWhenSold {
    all d, d_1: Dealership, c: Car, v: Person |
        sell[d, d_1, c, v] implies c not in d_1.stock
}

// Run commands to check the assertions

check InvariantBrandsAndVendors for 10 but 4 int
check StockConsistency for 10 but 4 int
check SoldCarsBelongToBrands for 10 but 4 int
check CarNotInStockWhenSold for 10 but 4 int


run {
    some d: Dealership, c: Car, d_1: Dealership |
        buy[d, d_1, c]
} for 10 but 4 int

run {
    some d: Dealership, c: Car, v: Person, d_1: Dealership |
        sell[d, d_1, c, v]
} for 10 but 4 int

run {} for 10 but 4 int