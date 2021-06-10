package com.epicusprogramming.veganfordays.db

import androidx.room.TypeConverter
import com.epicusprogramming.veganfordays.models.*

class Converters {
    //    converters from/to Step>Ingredient
//    @TypeConverter
    fun fromIngredient(ingredient: Ingredient): String {
        return ingredient.name
    }

//    @TypeConverter
    fun toIngredient(ingredientString: String): Ingredient {
        return Ingredient(0, ingredientString, ingredientString, ingredientString)
    }

    //    converters from/to Step>Equipment
//    @TypeConverter
    fun fromEquipment(equipment: Equipment): String {
        return equipment.name
    }

//    @TypeConverter
    fun toEquipment(equipmentString: String): Equipment {
        return Equipment(0, equipmentString, equipmentString, equipmentString)
    }

//    @TypeConverter
    fun fromLength(length: Length): String {
//        lengthString = "${length.number} ${length.unit}"
        return "${length.number} ${length.unit}"
    }

//    @TypeConverter
    fun toLength(lengthString: String): Length {
        var number: Int? = null
        var unit: String? = null
        for (letter in lengthString) {
            if (letter == ' ') {
                number = lengthString.substring(0 until lengthString.indexOf(letter)).toInt()
                unit =
                    lengthString.substring(lengthString.indexOf(letter) + 1 until lengthString.length)
            }
        }
        return number?.let { unit?.let { it1 -> Length(it, it1) } }!!
    }

//    @TypeConverter
    fun fromStep(step: Step): String {
        val equipmentsString = step.equipment.joinToString(",")
        val ingredientsString = step.ingredients.joinToString(",")

        val length = "${step.length.number} ${step.length.unit}"

// if needed to add ingredients and equipment by step add this -> /$stringOfIngredients/$stringOfEquipment
        return "$equipmentsString/$ingredientsString/$length/${step.number}/${step.step}"
    }

//    @TypeConverter
    fun toStep(stepsString: String): Step {
        val stepStringsContentList = stepsString.split("/")
        val length = toLength(stepStringsContentList[0])
        val equipmentsList = mutableListOf<Equipment>()
        val ingredientsList = mutableListOf<Ingredient>()

        stepStringsContentList[0].split(",")
            .forEach { equipment -> toEquipment(equipment).also { equipmentsList.add(it) } }

        stepStringsContentList[1].split(",")
            .forEach { ingredient -> toIngredient(ingredient).also { ingredientsList.add(it) } }
        return Step(
            equipmentsList,
            ingredientsList,
            length,
            stepStringsContentList[1].toInt(),
            stepStringsContentList[2]
        )
    }

    //    converters from/to AnalyzedInstructions > Step
//    @TypeConverter
    fun fromAnalyzedInstructions(analyzedInstruction: AnalyzedInstruction): String {

        return "${analyzedInstruction.name}/${analyzedInstruction.steps.joinToString(";")}"
    }

//    @TypeConverter
    fun toAnalyzedInstructions(analyzedInstructionsString: String): AnalyzedInstruction {
        val analyzedInstructionsContentList = analyzedInstructionsString.split("/")
        val name = analyzedInstructionsContentList[0]
        val stepsList = mutableListOf<Step>()

        analyzedInstructionsContentList[1].split(";")
            .forEach { step -> toStep(step).also { stepsList.add(it) } }

        return AnalyzedInstruction(name, stepsList)
    }
// converts from/to List<AnalyzedInstructions> > AnalyzedInstructions
    @TypeConverter
    fun fromAnalyzedInstructionsList(analyzedInstructionList: List<AnalyzedInstruction>): String {
        var analyzedInstructionsListString = ""
        analyzedInstructionList.forEach analyzedInstructionsListLoop@{ instruction ->
            if (instruction == analyzedInstructionList.last()) {
                analyzedInstructionsListString += fromAnalyzedInstructions(instruction)
                return@analyzedInstructionsListLoop
            }
            analyzedInstructionsListString += "${fromAnalyzedInstructions(instruction)},"
        }
        return analyzedInstructionsListString
    }

    @TypeConverter
    fun toAnalyzedInstructionsList(analyzedInstructionsString: String): List<AnalyzedInstruction> {
        val analyzedInstructionContentList: List<String> = analyzedInstructionsString.split(",")
        val analyzedInstructionList = mutableListOf<AnalyzedInstruction>()
        for (instructionString in analyzedInstructionContentList) {
            toAnalyzedInstructions(instructionString).also { analyzedInstructionList.add(it) }
        }
        return analyzedInstructionList
    }

    //    converters from/to CaloricBreakdown>Nutrition
//    @TypeConverter
    fun fromCaloricBreakdown(caloricBreakdown: CaloricBreakdown): String {
        val breakdownWithName =
            "${caloricBreakdown.percentCarbs} carbs/${caloricBreakdown.percentFat} fats/${caloricBreakdown.percentProtein} proteins"
        return "${caloricBreakdown.percentCarbs}/${caloricBreakdown.percentFat}/${caloricBreakdown.percentProtein}"
    }

//    @TypeConverter
    fun toCaloricBreakdown(breakdownString: String): CaloricBreakdown {

        val caloricBreakdownContentList = breakdownString.split("/")
        val carb = caloricBreakdownContentList[2].toDouble()
        val fat = caloricBreakdownContentList[1].toDouble()
        val protein = caloricBreakdownContentList[0].toDouble()
        return CaloricBreakdown(carb, fat, protein)
    }

    //    converters from/to Flavanoid>Nutrition
//    @TypeConverter
    fun fromFlavanoid(flavanoid: Flavanoid): String {
        return "${flavanoid.amount}/${flavanoid.name}/${flavanoid.unit}"
    }

//    @TypeConverter
    fun toFlavanoid(flavanoidString: String): Flavanoid {
        val flavanoidContentList = flavanoidString.split("/")
        val amount = flavanoidContentList[0].toDouble()
        val name = flavanoidContentList[1]
        val title = flavanoidContentList[1]
        val unit = flavanoidContentList[2]
        return Flavanoid(amount, name, title, unit)
    }

    //    converts from/to Nutrient>Nutrition
//    @TypeConverter
    fun fromNutrient(nutrient: Nutrient): String {
        return "${nutrient.amount}/${nutrient.name}/${nutrient.title}/${nutrient.unit}"
    }

//    @TypeConverter
    fun toNutrient(nutrientString: String): Nutrient {
        val nutrientContentList = nutrientString.split("/")
        return Nutrient(
            nutrientContentList[0].toDouble(),
            nutrientContentList[1],
            nutrientContentList[2],
            nutrientContentList[3]
        )
    }

//    @TypeConverter
    fun fromNutrientX(nutrientX: NutrientX): String {
        return "${nutrientX.amount}/${nutrientX.name}/${nutrientX.percentOfDailyNeeds}/${nutrientX.title}/${nutrientX.unit}"
    }

//    @TypeConverter
    fun toNutrientX(nutrientXString: String): NutrientX {
        val nutrientXContentList = nutrientXString.split("/")
        val amount = nutrientXContentList[0].toDouble()
        val name = nutrientXContentList[1]
        val percentOfDailyNeeds = nutrientXContentList[2].toDouble()
        val title = nutrientXContentList[3]
        val unit = nutrientXContentList[4]
        return NutrientX(amount, name, percentOfDailyNeeds, title, unit)
    }

    //    converters from/to IngredientX>Nutrition
//    @TypeConverter
    fun fromIngredientX(ingredientX: IngredientX): String {
        return "${ingredientX.amount}/${ingredientX.id}/${ingredientX.name}/${ingredientX.nutrients}/${ingredientX.unit}"
    }

//    @TypeConverter
    fun toIngredientX(ingredientXString: String): IngredientX {
        val ingredientXContentList = ingredientXString.split("/")
        val amount = ingredientXContentList[0].toDouble()
        val id = ingredientXContentList[1].toInt()
        val name = ingredientXContentList[2]
        val nutrients = toNutrient("0")
        val unit = ingredientXContentList.last()
        return IngredientX(amount, id, name, listOf(nutrients), unit)
    }

//    @TypeConverter
    fun fromProperty(property: Property): String {
        return "${property.amount}/${property.name}/${property.title}/${property.unit}"
    }

//    @TypeConverter
    fun toProperty(propertyString: String): Property {
        val propertyContentList = propertyString.split("/")
        val amount = propertyContentList[0].toDouble()
        val name = propertyContentList[1]
        val title = propertyContentList[2]
        val unit = propertyContentList[3]
        return Property(amount, name, title, unit)
    }

//    @TypeConverter
    fun fromWeightPerServing(weightPerServing: WeightPerServing): String {
        return "${weightPerServing.amount}/${weightPerServing.unit}"
    }

//    @TypeConverter
    fun toWeightPerServing(weightPerServingString: String): WeightPerServing {
        val weightPerServingContentList = weightPerServingString.split("/")
        val amount = weightPerServingContentList[0].toInt()
        val unit = weightPerServingContentList[1]
        return WeightPerServing(amount, unit)
    }

    @TypeConverter
    fun fromNutrition(nutrition: Nutrition): String {
        var caloricBreakdownString = fromCaloricBreakdown(nutrition.caloricBreakdown)
        var flavanoidsString = ""
        var ingredientsString = ""
        var nutrientsString = ""
        var propertiesString = ""
        val weightPerServing = fromWeightPerServing(nutrition.weightPerServing)

        nutrition.flavanoids.forEach flavanoidsLoop@{
            if (it == nutrition.flavanoids.last()) {
                flavanoidsString += it
                return@flavanoidsLoop
            }
            flavanoidsString += "$it,"
        }

        nutrition.flavanoids.forEach ingredientsLoop@{
            if (it == nutrition.flavanoids.last()) {
                ingredientsString += it
                return@ingredientsLoop
            }
            ingredientsString += "$it,"
        }

        nutrition.flavanoids.forEach nutrientsLoop@{
            if (it == nutrition.flavanoids.last()) {
                nutrientsString+= it
                return@nutrientsLoop
            }
            nutrientsString += "$it,"
        }

        nutrition.flavanoids.forEach propertiesLoop@{
            if (it == nutrition.flavanoids.last()) {
                propertiesString += it
                return@propertiesLoop
            }
            propertiesString+= "$it,"
        }
        return "$caloricBreakdownString/$flavanoidsString/$ingredientsString/$nutrientsString/$propertiesString/$weightPerServing"
    }

    @TypeConverter
    fun toNutrition(nutritionString: String): Nutrition {
        val nutritionContentList = nutritionString.split("/")
        val caloricBreakdown = toCaloricBreakdown(nutritionContentList[0])
        val flavanoidsList = mutableListOf<Flavanoid>()
        val ingredientsList = mutableListOf<IngredientX>()
        val nutrientsList = mutableListOf<NutrientX>()
        val propertiesList = mutableListOf<Property>()
        val weightPerServing = toWeightPerServing(nutritionContentList.last())

        val flavanoidsDetail = nutritionContentList[1].split(",")
            .forEach { flavanoid -> toFlavanoid(flavanoid).also { flavanoidsList.add(it) } }

        val ingredientsDetail = nutritionContentList[2].split(",")
            .forEach { ingredient -> toIngredientX(ingredient).also { ingredientsList.add(it) } }

        val nutrientsDetail = nutritionContentList[3].split(",")
            .forEach { nutrient -> toNutrientX(nutrient).also { nutrientsList.add(it) } }

        val propertiesDetail = nutritionContentList[4].split(",")
            .forEach { property -> toProperty(property).also { propertiesList.add(it) } }
        return Nutrition(
            caloricBreakdown,
            flavanoidsList,
            ingredientsList,
            nutrientsList,
            propertiesList,
            weightPerServing
        )
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toList(listString: String): List<String> {
        return listString.split(",")
    }
}