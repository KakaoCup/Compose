package io.github.kakaocup.compose.node.assertion

/**
 * Marker interface for resource-id based assertions. The actual methods are declared
 * on the Android-specific `actual interface` (since iOS lacks `@StringRes` resource ids).
 *
 * On iOS this is an empty marker — instantiate screens directly and call the
 * string-based [NodeAssertions] methods instead.
 */
expect interface TextResourcesNodeAssertions : NodeAssertions
