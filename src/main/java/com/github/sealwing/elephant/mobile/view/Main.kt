package com.github.sealwing.elephant.mobile.view

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.github.sealwing.elephant.mobile.core.Construction
import com.github.sealwing.elephant.mobile.core.ConstructionType
import com.github.sealwing.elephant.mobile.core.Deal
import fromStructToString
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick


class Main : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUi().setContentView(this)
    }

}

class MainActivityUi : AnkoComponent<Main> {

    private val deal: Deal = Deal(Construction(ConstructionType.QUARTET, 2400, 1470))

    override fun createView(ui: AnkoContext<Main>): View = with(ui) {
        verticalLayout {
            padding = dip(20)

            val dealInfo = textView("Информация о заказе")

            val width = editText {
                hint = "Ширина"
            }

            val height = editText {
                hint = "Высота"
            }

            val types = spinner {
                adapter = ArrayAdapter<String>(ui.owner, android.R.layout.simple_spinner_item, "2-х 3-х 4-х Гл.".split(" ").toTypedArray())
            }

            button {

                text = "Посчитать"

                onClick {
                    deal.deleteLast()
                    deal.addConstruction(Construction(detectType(types.selectedItem.toString()), width = detectWidth(width), height = detectHeight(height)))
                    val infoText = fromStructToString(deal)
                    dealInfo.text = infoText
                }
            }

        }
    }

    private fun detectType(type: String): ConstructionType =
            when (type) {
                "2-х" -> ConstructionType.DOUBLE
                "3-х" -> ConstructionType.TRIPLE
                "4-х" -> ConstructionType.QUARTET
                "Гл." -> ConstructionType.SOLID
                else -> ConstructionType.QUARTET
            }

    private fun detectWidth(field: EditText): Int = field.text.toString().toInt()

    private fun detectHeight(field: EditText): Int = field.text.toString().toInt()
}