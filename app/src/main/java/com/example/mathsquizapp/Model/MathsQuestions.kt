package com.example.mathsquizapp.Model

object MathsQuestions {

    fun numberPlaceValue(): QuestionAnswer {
        val questionAnswer = QuestionAnswer ("Number and Place Value",
                                            "1 more than 19 ?", "20", "18", "22", "21", "17")
        return questionAnswer
    }

    fun questionsAnswersList(): ArrayList<QuestionAnswer> {

        /***** numberPlaceValue *****/
        val numberPlaceValue1 = QuestionAnswer("Number and Place Value",
            "1 more than 19 ?", "20", "18", "22", "21", "17")

        val numberPlaceValue2 = QuestionAnswer("Number and Place Value",
            "1 more than 10 ?", "11", "9", "12", "20", "0")

        val numberPlaceValue3 = QuestionAnswer("Number and Place Value",
            "What number is after 99 ?", "100", "98", "101", "21", "17")

        val numberPlaceValue4 = QuestionAnswer("Number and Place Value",
            "What number is after 14 ?", "15", "18", "22", "21", "17")

        val numberPlaceValue5 = QuestionAnswer("Number and Place Value",
            "What number is before 4 ?", "3", "1", "2", "5", "8")

        val numberPlaceValue6 = QuestionAnswer("Number and Place Value",
            "What number is before 31 ?", "30", "32", "20", "10", "29")


        /***** AdditionSubtraction *****/
        val additionSubtraction1 = QuestionAnswer ("Addition and Subtraction",
            "5 + 5", "10", "55", "15", "50", "20")

        val additionSubtraction2 = QuestionAnswer ("Addition and Subtraction",
            "18 + 2", "20", "10", "16", "22", "24")

        val additionSubtraction3 = QuestionAnswer ("Addition and Subtraction",
            "25 + 5", "30", "55", "20", "35", "15")

        val additionSubtraction4 = QuestionAnswer ("Addition and Subtraction",
            "5 - 4", "1", "2", "7", "3", "0")

        val additionSubtraction5 = QuestionAnswer ("Addition and Subtraction",
            "6 - 3", "3", "6", "9", "2", "4")

        val additionSubtraction6 = QuestionAnswer ("Addition and Subtraction",
            "10 - 2", "8", "6", "12", "20", "5")


        /***** MultiplicationDivision *****/
        val multiplicationDivision1 = QuestionAnswer("Multiplication and Division",
            "2 x 2", "4", "8", "2", "1", "10")

        val multiplicationDivision2 = QuestionAnswer("Multiplication and Division",
            "4 x 3", "12", "8", "20", "2", "7")

        val multiplicationDivision3 = QuestionAnswer("Multiplication and Division",
            "10 x 4", "40", "14", "24", "18", "10")

        val multiplicationDivision4 = QuestionAnswer("Multiplication and Division",
            "10 / 2", "5", "15", "8", "20", "22")

        val multiplicationDivision5 = QuestionAnswer("Multiplication and Division",
            "4 / 2", "2", "6", "8", "10", "18")

        val multiplicationDivision6 = QuestionAnswer("Multiplication and Division",
            "6 / 3", "2", "3", "9", "12", "6")


        /***** Fractions *****/
        val fraction1 = QuestionAnswer("Fraction",
            "What fraction is a half?", "1/2", "1/5", "2/1", "10/5", "1/10")

        val fraction2 = QuestionAnswer("Fraction",
            "What fraction is a third?", "1/3", "1/30", "3/1", "30/10", "1/6")

        val fraction3 = QuestionAnswer("Fraction",
            "What fraction is a quarter?", "1/4", "4/10", "4/8", "4/10", "1/2")

        val fraction4 = QuestionAnswer("Fraction",
            "What fraction is a fifth?", "1/5", "5/10", "1/50", "50/10", "50/100")

        val fraction5 = QuestionAnswer("Fraction",
            "What fraction is a tenth?", "1/10", "1/100", "1/50", "1/2", "1/5")

        val fraction6 = QuestionAnswer("Fraction",
            "What fraction is a hundredth?", "1/100", "1/10", "1/50", "1/2", "10/1")

        /***** Measurement *****/
        val measurement1 = QuestionAnswer("Measurement",
            "Which measurement is 10 meters?", "10m", "10mm", "10cm", "10km", "10l")

        val measurement2 = QuestionAnswer("Measurement",
            "Which measurement is 5 litres?", "5l", "5ml", "5cm", "5km", "5m")

        val measurement3 = QuestionAnswer("Measurement",
            "Which measurement is 8 centre metres?", "8cm", "8mm", "8m", "8km", "8l")

        val measurement4 = QuestionAnswer("Measurement",
            "How many centre metres is 10mm?", "1cm", "10cm", "100cm", "100cm", "2cm")

        val measurement5 = QuestionAnswer("Measurement",
            "How many seconds in a minute?", "60 seconds", "600 seconds", "10 seconds", "30 seconds", "100 seconds")

        val measurement6 = QuestionAnswer("Measurement",
            "How many hours in a day?", "24 hours", "12 hours", "18 hours", "6 hours", "60 hours")


        /***** Geometry - Properties of Shapes *****/
        val shapes1 = QuestionAnswer("Geometry - Properties of Shapes",
            "How many sides in a triangle?", "3", "4", "5", "6", "8")

        val shapes2 = QuestionAnswer("Geometry - Properties of Shapes",
            "How many sides in a square?", "4", "5", "6", "7", "8")

        val shapes3 = QuestionAnswer("Geometry - Properties of Shapes",
            "How many sides in a pentagon?", "5", "4", "8", "3", "10")

        val shapes4 = QuestionAnswer("Geometry - Properties of Shapes",
            "How many sides in a hexagon?", "6", "7", "5", "4", "3")

        val shapes5 = QuestionAnswer("Geometry - Properties of Shapes",
            "How many sides in an octagon?", "8", "5", "7", "6", "10")

        val shapes6 = QuestionAnswer("Geometry - Properties of Shapes",
            "How many sides in a rectangle?", "4", "8", "6", "3", "5")


        /***** Geometry - Positions and directions *****/
        val positions1 = QuestionAnswer("Geometry - Position and Directiom",
            "Which direction is up?", "North", "East", "South", "West", "NorthEast")

        val positions2 = QuestionAnswer("Geometry - Position and Directiom",
            "Which direction is right?", "East", "South", "North", "West", "SouthEast")

        val positions3 = QuestionAnswer("Geometry - Position and Directiom",
            "Which direction is down?", "South", "West", "North", "East", "SouthWest")

        val positions4 = QuestionAnswer("Geometry - Position and Directiom",
            "Which direction is left?", "West", "East", "South", "North", "NorthWest")

        val positions5 = QuestionAnswer("Geometry - Position and Directiom",
            "Which direction is between North and East?", "NorthEast", "East", "South", "West", "North")

        val positions6 = QuestionAnswer("Geometry - Position and Directiom",
            "Which direction is between South and West?", "SouthWest", "East", "South", "West", "North")


        val qaList = ArrayList<QuestionAnswer>()
        qaList.add(numberPlaceValue1)
        qaList.add(numberPlaceValue2)
        qaList.add(numberPlaceValue3)
        qaList.add(numberPlaceValue4)
        qaList.add(numberPlaceValue5)
        qaList.add(numberPlaceValue6)
        qaList.add(additionSubtraction1)
        qaList.add(additionSubtraction2)
        qaList.add(additionSubtraction3)
        qaList.add(additionSubtraction4)
        qaList.add(additionSubtraction5)
        qaList.add(additionSubtraction6)
        qaList.add(multiplicationDivision1)
        qaList.add(multiplicationDivision2)
        qaList.add(multiplicationDivision3)
        qaList.add(multiplicationDivision4)
        qaList.add(multiplicationDivision5)
        qaList.add(multiplicationDivision6)
        qaList.add(fraction1)
        qaList.add(fraction2)
        qaList.add(fraction3)
        qaList.add(fraction4)
        qaList.add(fraction5)
        qaList.add(fraction6)
        qaList.add(measurement1)
        qaList.add(measurement2)
        qaList.add(measurement3)
        qaList.add(measurement4)
        qaList.add(measurement5)
        qaList.add(measurement6)
        qaList.add(shapes1)
        qaList.add(shapes2)
        qaList.add(shapes3)
        qaList.add(shapes4)
        qaList.add(shapes5)
        qaList.add(shapes6)
        qaList.add(positions1)
        qaList.add(positions2)
        qaList.add(positions3)
        qaList.add(positions4)
        qaList.add(positions5)
        qaList.add(positions6)
        return qaList
    }



}