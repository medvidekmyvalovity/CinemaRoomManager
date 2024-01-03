package cinema

class Cinema(val rows:Int=7, val columns:Int=8, val seats: List<MutableList<Char>> =List(rows) {MutableList(columns) {'S'} }, var sold: Int =0,var currentIncome:Int=0)
{
    fun print(){
        println("Cinema:")
        println ("  ${(1..columns).joinToString(" " )}")
        seats.forEachIndexed {i,l -> println("${i+1} ${seats[i].joinToString(" " )}") }
    }
    fun mark(row:Int,column:Int)
    {
        seats[row-1][column-1]='B'
    }

    fun price(row:Int):Int
    {
        if (rows*columns > 60 && rows/2<row) return (8)
        else return(10)
    }
    fun totalIncome():Int{
        return(if (rows*columns <= 60) rows*columns*10
        else rows/2*columns*10+(rows-rows/2)*columns*8)
    }
    fun ticket(){
        var success=false
        while(!success) {
            println("Enter a row number:")
            val row = readln().toInt()
            println("Enter a seat number in that row:")
            val column = readln().toInt()
            if (!(row in 1..rows )||!(column in 1..columns )) println("Wrong input!")
            else if (seats[row-1][column-1]=='B') println("That ticket has already been purchased!")
            else {
                val ticketPrice = price(row)
                println("Ticket price: \$$ticketPrice")
                mark(row, column)
                sold++
                currentIncome += ticketPrice
                success=true
            }
        }
    }
    fun printStatistics(){
        println("Number of purchased tickets: $sold")
        println("Percentage: ${ "%.2f".format(sold.toDouble()/rows/columns*100)}%")
        println("Current income: \$$currentIncome")
        println("Total income: \$${totalIncome()}")
    }

}

fun calculateProfit(){
    println("Enter the number of rows:")
    val rows=readln().toInt()
    println("Enter the number of seats in each row:")
    val columns=readln().toInt()
    val c=Cinema(rows,columns)
    println("Total income: \$${c.totalIncome()}")

}
fun tickets(){
    println("Enter the number of rows:")
    val rows=readln().toInt()
    println("Enter the number of seats in each row:")
    val columns=readln().toInt()
    val c=Cinema(rows,columns)
    c.print()
    println("Enter a row number:")
    val row=readln().toInt()
    println("Enter a seat number in that row:")
    val column=readln().toInt()
    println("Ticket price: \$${c.price(row)}")
    c.mark(row,column)
    c.print()
}
fun menu(){
    println("Enter the number of rows:")
    val rows=readln().toInt()
    println("Enter the number of seats in each row:")
    val columns=readln().toInt()
    val c=Cinema(rows,columns)
    while(true){
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        val m=readln()
        when(m) {
            "0"-> return
            "1"-> c.print()
            "2"-> c.ticket()
            "3"->c.printStatistics()
        }
    }
}
fun main() {
    // write your code here
    //Cinema().print()
    //calculateProfit()
    //tickets()
    menu()
}