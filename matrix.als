// Cell in a matrix includes location 
// and value stored within
sig Cell{
    row: Int,
    col: Int,
    value: Int
  }

// All cells are in not out of 
// range for a 3x3 matrix
fact cellInBounds {
    all c: Cell | 1 <= c.row && c.row <= 3 
                  && 1 <= c.col && c.col <= 3
  }

// All cells have a row and coordinate 
// value that can be either
// 1 or 2 or 3
//
// Defines the possible values 
// that each cell coordinate could have
fact cellInBounds {
    Matrix.cells.row = 1 + 2 + 3 and
    Matrix.cells.col = 1 + 2 + 3
  }

// Defines a matrix as a set of cells
one sig Matrix {
    cells: set Cell
  }

// Ensures matrix does have 9 cells
fact matrixIsThreeByThree {#Matrix.cells = 9}

// All 9 cells are in a different coordinate within the matrix 
fact cellsDontOverlap {
    all disj c1, c2: Matrix.cells | c1.row != c2.row
                                    or c1.col != c2.col
  }

// All cells have a unique value
fact cellValueIsUnique {
    all disj c1, c2: Matrix.cells | c1.value != c2.value
  }

// Possible values for the Cell.value of the Matrix
fact cellValueRange {
    Matrix.cells.value in 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9
  }

run {} for 15 but 5 int
