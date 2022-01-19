//couldn't write злой in russian because of kotlin error. I am now sad
 fun lol(n: Int): String {
    val x = n.toString().split("").filter { e -> e != "" }.map { it.toInt() }
        val map = mutableMapOf(
            0 to "abcdef ", 1 to "ef ",
            2 to "abged ", 3 to "abgcd ",
            4 to "fgbc ", 5 to "afgcd ",
            6 to "fgedc ", 7 to "fabc ",
            8 to "abcdefg ", 9 to "abfgc ")

    var k = 0
    var list = ""
    while (k != x.size) {
        for ((i, e) in map) {
            if (i == x[k]) list += e
        }
        k += 1
    }
    println(list)
        return list
    }
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