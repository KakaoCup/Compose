package io.github.kakaocup.compose.node.element.list

import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.SemanticsMatcher
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.core.BaseNode

/**
 * Base class for all child nodes within [KListNode].
 *
 * The constructor is declared as `protected` so that only inheritors have the right to call this constructor.
 *
 * Warning! Manually creating list items is **not necessary**.
 */
open class KListItemNode<out T : KListItemNode<T>> protected constructor() : BaseNode<T>() {

    companion object {

        /**
         * Method for correctly initializing the necessary parameters of [BaseNode].
         * This method allows us to keep the main constructor of the element empty, which greatly
         * simplifies the description of subclass elements.
         *
         * @param listNode The root node of the list within which we need to interact with the list item.
         * @param semanticsNode A list of key/value pairs associated with the layout node or its subtree.
         * @param useUnmergedTree If true, the unmerged semantic tree will be used to work with the node.
         */
        inline fun <reified T : KListItemNode<*>> newInstance(
            listNode: KListNode,
            semanticsNode: SemanticsNode,
            useUnmergedTree: Boolean,
        ): T {
            val instance = T::class.java.getDeclaredConstructor().newInstance()

            instance.initSemantics(
                semanticsProvider = listNode.requireSemanticsProvider(),
                nodeMatcher = NodeMatcher(
                    matcher = SemanticsMatcher(
                        description = "Semantics node id = ${semanticsNode.id}",
                        matcher = { it.id == semanticsNode.id },
                    ),
                    useUnmergedTree = useUnmergedTree,
                ),
                parentNode = listNode,
            )

            return instance
        }

    }

}
