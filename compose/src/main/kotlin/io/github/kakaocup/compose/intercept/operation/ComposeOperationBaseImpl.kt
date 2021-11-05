package io.github.kakaocup.compose.intercept.operation

class ComposeOperationBaseImpl<View>(
    override val type: ComposeOperationType,
    override val description: String?,
    private val action: View.() -> Unit
) : ComposeOperation<View> {

    override fun execute(innerView: View) = action.invoke(innerView)

    override fun toString(): String {
        return "UiOperationBaseImpl(type=$type, description=$description, action=$action)"
    }
}