import java.util.function.BinaryOperator

fun main() {
    var text = "Hello World"
    var lol = Integer.toBinaryString('0'.toInt())
    var key = Integer.toBinaryString('B'.toInt())
    var solve = Integer.toBinaryString('k'.toInt())

    while (lol.length != 8 || key.length != 8 || solve.length != 8) {
      if (lol.length != 8) lol = "0$lol"
      if (key.length != 8) key = "0$key"
      if (solve.length != 8) solve = "0$solve"
    }

    var x = 0
    var string = ""
    for (i in lol) {
        string += if (i != solve[x]) "1" else "0"

        x+=1
    }

    var converter = string.toInt(2).toChar()
    println(string)
    println(converter)

// AZ = 33-58 , 33 = A
}