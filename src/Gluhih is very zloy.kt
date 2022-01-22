//Int version + single loop + 2 var + less lines = gucci code
fun lol(sev: Int): String {

    val map = mutableMapOf(
        0 to "abcdef ", 1 to "ef ",
        2 to "abged ", 3 to "abgcd ",
        4 to "fgbc ", 5 to "afgcd ",
        6 to "fgedc ", 7 to "fabc ",
        8 to "abcdefg ", 9 to "abfgc ")

    var divide = 1
    var gather = ""
    while (sev != sev % divide) {
        gather = map[(sev / divide) % 10] + gather
        divide *= 10
    }
    println(gather)
    return gather
}

////Int version single loop
// fun lol(sev: Int): String {
//
//    val map = mutableMapOf(
//        0 to "abcdef ", 1 to "ef ",
//        2 to "abged ", 3 to "abgcd ",
//        4 to "fgbc ", 5 to "afgcd ",
//        6 to "fgedc ", 7 to "fabc ",
//        8 to "abcdefg ", 9 to "abfgc ")
//
//    var digit = 1
//    var divide = 1
//    var substract = 0
//    var collect = ""
//    var gather = ""
//            while (digit != 0) {
//                val r = ((sev + substract) / divide) % 10
//                collect += map[r]
//                gather = collect + gather
//
//                divide *= 10
//               val remainder = sev % divide
//                substract = -remainder
//
//                collect = ""
//                 digit = substract + sev
//            }
//    println(gather)
//    return gather
//    }
////Int version
//fun lol(sev: Int): String {
//
//    val map = mutableMapOf(
//        0 to "abcdef ", 1 to "ef ",
//        2 to "abged ", 3 to "abgcd ",
//        4 to "fgbc ", 5 to "afgcd ",
//        6 to "fgedc ", 7 to "fabc ",
//        8 to "abcdefg ", 9 to "abfgc ")
//
//    var old = sev
//    var new = 0
//    var digit = 0
//    while (old != 0) {
//        digit += 1
//        val num = old % 10
//        new = new * 10 + num
//        old /= 10
//    }
//
//    var z = 1
//    var u = 0
//    var c = 0
//    var sus = ""
//    while (digit != 0) {
//        val t = (new + c)
//        val r = (t / z) % 10
//
//
//        z *= 10
//        u += new % z
//        c -= c + u
//        sus += map[r]
//        u -= u
//        digit -= 1
//    }
//    println(sus)
//    return sus
//}
//q != 0||
//q != 1|| q != 2 || q != 3 || q != 4 || q != 5 || q != 6 || q != 7 || q != 8 || q != 9 ||
//q != 1|| q != -2 || q != -3 || q != -4 || q != -5 || q != -6 || q != -7 || q != -8 || q != -9

////couldn't write злой in russian because of kotlin error. I am now sad
//
// fun lol(n: Int): String {
//    val x = n.toString().split("").filter { e -> e != "" }.map { it.toInt() }
//        val map = mutableMapOf(
//            0 to "abcdef ", 1 to "ef ",
//            2 to "abged ", 3 to "abgcd ",
//            4 to "fgbc ", 5 to "afgcd ",
//            6 to "fgedc ", 7 to "fabc ",
//            8 to "abcdefg ", 9 to "abfgc ")
//
//    var k = 0
//    var list = ""
//    while (k != x.size) {
//        for ((i, e) in map) {
//            if (i == x[k]) list += e
//        }
//        k += 1
//    }
//    println(list)
//        return list
//    }

// fun lol(n: Int): String {
//        val x = n.toString().split("").filter { e -> e != "" }.map { it.toInt() }
//        var list = ""
//        for (i in x) {
//            when {
//                i == 0 -> list += "abcdef "
//                i == 1 -> list += "ef "
//                i == 2 -> list += "abged "
//                i == 3 -> list += "abgcd "
//                i == 4 -> list += "fgbc "
//                i == 5 -> list += "afgcd "
//                i == 6 -> list += "fgedc "
//                i == 7 -> list += "fabc "
//                i == 8 -> list += "abcdefg "
//                i == 9 -> list += "abfgc "
//            }
//        }
//        return list
//    }