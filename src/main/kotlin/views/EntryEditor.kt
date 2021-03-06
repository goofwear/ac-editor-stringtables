package views

import controllers.StringTableController
import javafx.scene.layout.Priority
import models.StringTableEntryModel
import tornadofx.*

class EntryEditor: View() {

    private val controller: StringTableController by inject(DefaultScope)
    private val model: StringTableEntryModel by inject()

    override val root = vbox {
        hboxConstraints {
            hGrow = Priority.ALWAYS
        }
        vboxConstraints {
            vGrow = Priority.ALWAYS
        }

        form {
            fieldset("String table entry") {
                field("ID") {
                    label().bind(model.id)
                }

                field("Text") {
                    textarea(model.content) {
                        style {
                            fontSize = 20.px
                        }
                    }
                }

                hbox(4.0) {
                    button("Save") {
                        enableWhen(model.dirty)
                        action {
                            controller.tableChanged = true
                            model.commit()
                        }
                    }

                    button("Reset") {
                        enableWhen(model.dirty)
                        action {
                            model.rollback()
                        }
                    }
                }
            }
        }
    }
}