@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.*

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String = when {


    age == 11 || age == 111 -> "$age лет"
    age in 5..20 -> "$age лет"
    age % 10 == 1 && age != 11 && age != 111 -> "$age год"
    age % 10 == 2 -> "$age года"
    age % 10 == 3 -> "$age года"
    age % 10 == 4 -> "$age года"
    age % 10 == 5 -> "$age лет"
    age % 10 == 6 -> "$age лет"
    age % 10 == 7 -> "$age лет"
    age % 10 == 8 -> "$age лет"
    age % 10 == 9 -> "$age лет"
    age % 10 == 0 -> "$age лет"
    else -> "$age не существует"

}


/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s1 = v1 * t1
    val s2 = v2 * t2
    val s3 = v3 * t3
    val halfWay = (s1 + s2 + s3) / 2
    return when {
        halfWay <= s1 -> halfWay / v1
        halfWay > s1 && halfWay <= s1 + s2 -> t1 + (halfWay - s1) / v2
        halfWay > s2 -> t1 + t2 + (halfWay - s1 - s2) / v3
        else -> 0.0
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int = when {
    (rookX1 == kingX || rookY1 == kingY) && (rookX2 == kingX || rookY2 == kingY) -> 3
    rookX1 == kingX || rookY1 == kingY -> 1
    rookX2 == kingX || rookY2 == kingY -> 2
    else -> 0
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
):
        Int = when {
    (rookX == kingX || rookY == kingY) && (kingX - bishopX == kingY - bishopY) -> 3
    rookX == kingX || rookY == kingY -> 1
    abs(kingX - bishopX) == abs(kingY - bishopY) -> 2
    else -> 0
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val cosinusA = (sqr(c) + sqr(b) - sqr(a)) / (2 * b * c)
    val cosinusB = (sqr(a) + sqr(c) - sqr(b)) / (2 * a * c)
    val cosinusC = (sqr(a) + sqr(b) - sqr(c)) / (2 * a * b)
    return when {
        a >= b + c || b >= a + c || c >= a + b -> -1
        cosinusA == 0.0 || cosinusB == 0.0 || cosinusC == 0.0 -> 1
        cosinusA in -1.0..0.0 || cosinusB in -1.0..0.0 || cosinusC in -1.0..0.0 -> 2
        else -> 0
    }
}


/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if (b > c && d > a && b - c < d - a && d > b) return (b - c)
    if (d > a && b > c && d - a < b - c && b > d) return (d - a)
    if (b > d && c > a && d - c < b - a && b > c) return (d - c)
    if (d > b && a > c && b - a < d - c && d > a) return (b - a)
    return if (b == c || a == d) 0
    else -1
}
