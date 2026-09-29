package io.github.kakaocup.compose.node.element.lazylist

import kotlin.reflect.KClass

class LazyListItemProvisionException(
    itemNodeClazz: KClass<*>
) : Exception(
    """${itemNodeClazz.java.simpleName} did not provided to KLazyListNode. 
    You should provide KLazyListItemNode through itemTypeBuilder function
    """.trimIndent()
)
